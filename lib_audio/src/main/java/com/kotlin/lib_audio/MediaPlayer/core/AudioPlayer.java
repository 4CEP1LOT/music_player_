package com.kotlin.lib_audio.MediaPlayer.core;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.kotlin.lib_audio.MediaPlayer.event.AudioCompletionEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioErrorEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioPauseEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioProgressEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioReleaseEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioStartEvent;
import com.kotlin.lib_audio.app.AudioHelper;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongUrl;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * 播放音频
 * 对外发送各种类型事件
 */
public class AudioPlayer implements MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener, AudioFocusManager.AudioFocusListener,MediaPlayer.OnCompletionListener {
    private static final String TAG = "AudioPlayer";
    private static final int TIME_MSG = 0x01;
    private static final int TIME_INVAL = 100;
    CustomMediaPlayer mMediaPlayer = new CustomMediaPlayer();
    //真正负责音频的播放
    private CustomMediaPlayer customMediaPlayer;                    //真正负责音频的播放
    private WifiManager.WifiLock MWifiLock;                         //增强程序的保活能力
    //音频焦点监听器
    private AudioFocusManager mAudioFocusManager;
    private WifiManager.WifiLock mWifiLock;
    private boolean isPausedByFocusChanged;                                 //是否因为暂停而停止播放

    private android.os.Handler mhandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TIME_MSG:
                    break;

            }
        }
    };
    public AudioPlayer(){
        init();

    }

    //初始化各个方法
    private void init() {
//       mMediaPlayer.setWakeMode(AudioHelper.getContext(), PowerManager.PARTIAL_WAKE_LOCK);          //设置唤醒模式为即便在低电量下也能播放音乐
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnErrorListener(this);

        //初始化WifiLock;
        mWifiLock = ((WifiManager) AudioHelper.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE)).createWifiLock(WifiManager.WIFI_MODE_FULL,TAG);
        //
        mAudioFocusManager = new AudioFocusManager(AudioHelper.getContext(),this);
    }


    //外部调用的接口
        public void load(SongUrl.Data songurl, SongDetails.Songs songdetails){
            try {
                mMediaPlayer.reset();                                                                   //清空上一次的播放数据
                mMediaPlayer.setDataSource(songurl.getUrl());

                mMediaPlayer.prepareAsync();                                                            //异步准备
                //对外发送load事件
//                EventBus.getDefault().post(new AudioLoadEvent(songdetails,songurl));
            } catch (IllegalStateException | IOException e) {
                //对外发送error事件
                EventBus.getDefault().post(new AudioErrorEvent(2));
            }
        }


        /**
         * 内部开始播放音乐
         *
         */
        private void start(){
        //判断是否获取了音频焦点，如果没有的话播放失败
        if(!mAudioFocusManager.requestAudioFocus()){
            Log.e(TAG,"获取焦点失败");
        }
        mMediaPlayer.start();                                                                       //音乐播放器开始运行
        mWifiLock.acquire();                                                                        //启动wifi锁，wifi锁就是保持息屏或者后台不会断网
         mHandler.sendEmptyMessage(TIME_MSG);
        EventBus.getDefault().post(new AudioStartEvent());
        }

    /**
     * 对外提供的暂停方法，如果播放器正在播放，暂停播放
     * 释放WifiLock
     *
     */
    public  CustomMediaPlayer.Status getStatus() {
        if (mMediaPlayer != null){
            return mMediaPlayer.getmState();
        }else {
            return CustomMediaPlayer.Status.STOPPED;
        }
    }

    /**
     * 设置音量
     * @param leftVol       左声道音量
     * @param RightVol      右声道音量
     */
    private void setVolumn(float leftVol, float RightVol) {
        if (mMediaPlayer!=null){
            mMediaPlayer.setVolume(leftVol, RightVol);
        }
    }

            public void pause(){
                    if (getStatus() == CustomMediaPlayer.Status.STARTED){
                        mMediaPlayer.pause();
                    }
                    if(mWifiLock.isHeld()){
                        mWifiLock.release();
                    }
                    if (mAudioFocusManager!= null){
                        mAudioFocusManager.abandonAudioFocus();
                    }
                    EventBus.getDefault().post(new AudioPauseEvent());

                }

            /**
             * 判断播放器当前状态
             * @return    如果播放器不为空，返回值为当前播放状态
             * 否则代表播放器停止
             */

            /**
             * 对外提供恢复播放器的方法
             */

            public void resume(){
                if (getStatus() == CustomMediaPlayer.Status.PAUSED){
                    //直接使用在本类中新建的start方法来播放
                    start();
                }

            }

            /**
             * 清空播放器占用资源
             */
            public void release(){
                if (mMediaPlayer == null){
                   return;
                }
                mMediaPlayer.release();
                if (mAudioFocusManager!=null){
                    mAudioFocusManager.abandonAudioFocus();
                }
                if (mWifiLock.isHeld()){
                    mWifiLock.release();
                }
                mWifiLock = null;                           //清空wifi锁 后把对象清空
                mMediaPlayer = null;                        //清空播放器或把对象清空
                //发送销毁事件
                EventBus.getDefault().post(new AudioReleaseEvent());
            }




            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {

                //缓存进度回调

            }





            /**
             * 对外提供错误
             * @param mp
             * @param what
             * @param extra
             * @return true  当返回为true的时候，代表不会再调用onCompletion方法
             */
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                //播放出错回调
                EventBus.getDefault().post(new AudioErrorEvent(1));
                return true;
            }

            @Override
            public void onPrepared(MediaPlayer mp) {
                //准备完毕，只需要直接调用start方法即可
                start();
            }



            @Override
            public void onCompletion(MediaPlayer mp) {
                //播放完毕回调
                EventBus.getDefault().post(new AudioCompletionEvent());
            }


            @Override
            public void audioFocusGrant() {
                //再次获取音频焦点
                setVolumn(1.0f,1.0f);
                if (isPausedByFocusChanged){
                    resume();
                }
                isPausedByFocusChanged = false;

            }


            @Override
            public void audioFocusLoss() {                                          //永久失去焦点
                pause();

            }

            @Override
            public void audioFocusLoseTransient() {                                     //
                    pause();
                isPausedByFocusChanged = true;
            }

            @Override
            public void audioFocusLoseDuck() {
                setVolumn(0.5f,0.5f);

            }

            public int getDuration() {
                if (getStatus() == CustomMediaPlayer.Status.STARTED
                        || getStatus() == CustomMediaPlayer.Status.PAUSED) {
                    return mMediaPlayer.getDuration();
                }
                return 0;
            }

            public int getCurrentPosition(){
                if (getStatus() == CustomMediaPlayer.Status.STARTED
                        || getStatus() == CustomMediaPlayer.Status.PAUSED) {
                    return mMediaPlayer.getCurrentPosition();
                }
                return 0;
            }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIME_MSG:
                    //暂停也要更新进度，防止UI不同步，只不过进度一直一样
                    if (getStatus() == CustomMediaPlayer.Status.STARTED
                            || getStatus() == CustomMediaPlayer.Status.PAUSED) {
                        //UI类型处理事件
                        EventBus.getDefault()
                                .post(new AudioProgressEvent(getStatus(), getCurrentPosition(), getDuration()));
                        sendEmptyMessageDelayed(TIME_MSG, TIME_INVAL);
                    }
                    break;
            }
        }
    };

}
