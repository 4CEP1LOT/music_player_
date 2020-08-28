package com.kotlin.lib_video.videoplayer.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kotlin.lib_video.R;

import java.io.IOException;

public class CustomVideoView  extends RelativeLayout implements View.OnClickListener, MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener,TextureView.SurfaceTextureListener {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PLAYING = 1;
    private static final int STATE_PAUSING = 2;
    private static final int LOAD_TOTAL_COUNT = 3;
    /**
     * UI数据
     */
    private MediaPlayer mediaPlayer;
    private RelativeLayout mPlayerView;
    private TextureView mVideoView;
    private Button mMiniPlayBtn;
    private ImageView mFullBtn;
    private ImageView mLoadingBar;
    private AudioManager audioManager;
    private Surface videoSurface;

    /**
     * Data数据
     *
     */

    private String mUrl;
    private boolean isMute;                                                               //是否静音，小屏窗口静音，大屏窗口打开
    private int mScreenWidth, mDestationHeight;                                          //

    /**
     * Status状态保护
     *
     */
    private boolean isRealPause;                                                       //是否是真正暂停,用户主动按住了暂停按钮就是暂停，如果没有是来电或者
    private boolean IsComplete;                                                        //是否播放完毕
    private int mCurrentCount;                                                          //重试的次数
    private int playerState = STATE_IDLE;                                               //播放器状态
    private ADVideoPlayerListener mlistener;                                                    //通知上一层的回调
    private ScreenEventReceiver mScreenReceiver;                                            //监听屏幕亮屏熄屏的广播






    public CustomVideoView(Context context) {
        super(context);
        audioManager =(AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);          //初始化音频管理器
        initData();
        initView();
        registerBroadCastReceiver();                                                                //注册broadcastReceiver
        

    }

    public boolean isRealPause() {
        return isRealPause;
    }

    public void setRealPause(boolean misRealPause) {
        this.isRealPause = misRealPause;
    }

    public boolean isComplete() {
        return isComplete();
    }

    public void setIsComplete(boolean mIsComplete) {
        this.IsComplete = mIsComplete;
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        mPlayerView = (RelativeLayout) inflater.inflate(R.layout.video_player_layout, this);
        mVideoView = mPlayerView.findViewById(R.id.xadsdk_player_video_textureView);                    //初始化
        mVideoView.setOnClickListener(this);

    }

    private void initData() {
        DisplayMetrics dm = new DisplayMetrics();                                                  //
    }

    private void registerBroadCastReceiver() {
          if (mScreenReceiver == null){
              mScreenReceiver = new ScreenEventReceiver();
              IntentFilter filter = new IntentFilter();
              filter.addAction(Intent.ACTION_SCREEN_OFF);                              //监听屏幕是亮屏还是暗屏
              filter.addAction(Intent.ACTION_USER_PRESENT);                            //监听手机是否解锁
              getContext().registerReceiver(mScreenReceiver,filter);                    //
          }
    }
    void isShowFullBtn(Boolean isShow){
        mFullBtn.setImageResource(isShow?R.drawable.xadsdk_ad_mini : R.drawable.xadsdk_ad_mini_null);
        mFullBtn.setVisibility(isShow? View.VISIBLE:    View.GONE);
    }

    public void mute(boolean mute){
        isMute = mute;                                                  //判断是否静音
        if(mediaPlayer != null && this.audioManager != null){
            float volume = isMute ? 0.0f : 1.0f;                        //是的话，调音量大小为0.0：1.0f
            mediaPlayer.setVolume(volume,volume);
        }
    }

    public boolean isPlaying(){                                              //判断播放器是否不为空，且正在播放中
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }


    private void  checkMediaPlayer(){
        if (mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);                  //播放音乐
        }
    }

    //除了播放和loading外其余任何状态都显示pause
    private void showPlayOrPauseView(boolean show){                                           //设置暂停界面
        mFullBtn.setVisibility(show ? View.VISIBLE:View.GONE);                          //全屏按钮出现,否则消失
        mMiniPlayBtn.setVisibility(show ? View.GONE: View.VISIBLE);                     //缩略屏按钮消失，否则出现
        mLoadingBar.clearAnimation();                                                   //清除动画
        mLoadingBar.setVisibility(View.GONE);
    }


    private void showLoadingView(boolean show){                                                     //Loading状态下的View
        mFullBtn.setVisibility(View.GONE);                                              //全屏播放按钮
        mMiniPlayBtn.setVisibility(show ? View.GONE: View.VISIBLE);                     //缩略图屏幕播放按钮
        AnimationDrawable anim = (AnimationDrawable) mLoadingBar.getBackground();         //
        anim.start();
        mLoadingBar.setVisibility(View.GONE);
    }


    private void unRegisterBroadcastReceiver(){
        if (mScreenReceiver != null){
            getContext().unregisterReceiver(mScreenReceiver);                               //反注册broadcastReceiver
        }
    }
    //小模式的状态
    private void initSmallLayoutMode(){
        LayoutParams params = new LayoutParams(mScreenWidth,mDestationHeight);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mPlayerView.setLayoutParams(params);

        mMiniPlayBtn = mPlayerView.findViewById(R.id.xadsdk_small_play_btn);
        mFullBtn = mPlayerView.findViewById(R.id.xadsdk_to_full_view);                              //全屏按钮
        mLoadingBar =mPlayerView.findViewById(R.id.loading_bar);                                    //正在加载的三个点
        mMiniPlayBtn.setOnClickListener(this);
        mFullBtn.setOnClickListener(this);

    }
    //进入播放状态需要更新的状态
    private void entryResumeState(){                                                                //设置播放状态
        setCurrentPlayState(STATE_PLAYING);
        setIsRealPause(false);
        setIsComplete(false);
    }

    private void setIsRealPause(boolean b) {

    }




    private void setCurrentPlayState(int state) {
        playerState = state;
    }

    /**
     * 完成视频加载
     */
    public void load(){
        if (this.playerState != STATE_IDLE){                                    //如果播放器状态不为空
            return;
        }
        showLoadingView(false);
        try {
            setCurrentPlayState(STATE_IDLE);                                  //如果播放器状态为空，
            checkMediaPlayer();
            mute(true);
            mediaPlayer.setDataSource(this.mUrl);
            mediaPlayer.prepareAsync();                                     //开始异步加载
        } catch (IOException e) {
            stop();                                                         //error之后重新调用stop加载
        }
    }

    public void resume(){
        if (this.playerState != STATE_PAUSING){
            return;
        }
        if (!isPlaying()){
            entryResumeState();
            mediaPlayer.setOnSeekCompleteListener(null);
            mediaPlayer.start();
            showPlayOrPauseView(true);
        }else {
            showPlayOrPauseView(false);
        }

    }
    //跳到指定点播放视频
    public void seekAndResume(int position){
        if (mediaPlayer!=null){
            showPlayOrPauseView(true);
            entryResumeState();
            mediaPlayer.seekTo(position);                                                                   //设定对应位置
            mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {                //设置进度条监听
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    mediaPlayer.start();                                                                    //播放器继续播放，无需从头加载
                }
            });

        }
    }

    public int getCurrentPosition(){
        if(mediaPlayer!=null){
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void pause(){                                                                                     //视频暂停方法
        if(this.playerState != STATE_PAUSING){
            return;
        }
        setCurrentPlayState(STATE_PAUSING);
            if (isPlaying()) {
                mediaPlayer.pause();
            }
            this.showPlayOrPauseView(false);
            }


        //跳转到定点并暂停
    public void seekAndPause(int position){
        if (this.playerState != STATE_PAUSING){
            return;
        }
        showPlayOrPauseView(false);
        setCurrentPlayState(STATE_PAUSING);
        if (isPlaying()){
            mediaPlayer.seekTo(position);                                                                           //找到进度点
            mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {                        //设定进度条监听
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    mediaPlayer.pause();                                                                            //暂停
                }
            });

        }

    }


    //回到最初始的状态
        public void playBack(){
            setCurrentPlayState(STATE_PAUSING);
            if (mediaPlayer != null){
                mediaPlayer.setOnSeekCompleteListener(null);
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();
            }
            this.showPlayOrPauseView(false);
        }

    public void setDataSource(String url) { this.mUrl = url;
    }


    public void pauseForFullScreenpa() {
    }



    private class ScreenEventReceiver extends BroadcastReceiver {

            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()){
                    case Intent.ACTION_USER_PRESENT:                                          //亮屏事件处理
                        if (isRealPause){                                                    //如果是暂停状态继续保持暂停
                            pause();
                        }else{
                            resume();                                                       //否则回调状态
                        }
                        break;
                    case Intent.ACTION_SCREEN_OFF:                                          //息屏状态
                        if (playerState == STATE_PLAYING){                                  //保持暂停
                            pause();
                        }
                        break;
                }

            }
        }


    /**
     * 供slot层即customView的具体实现层来进行通信
     */
    public interface ADVideoPlayerListener {

       void onVideoLoadSuccess();
       void onVideoCompleted();
       void onVideoFailed();
       void onFullScreenButton();
       void onClickVideo();
    }






    public void stop() {
        if(this.mediaPlayer != null){                                       //如果播放器不为空
            this.mediaPlayer.reset();                                       //重置播放器
            this.mediaPlayer.setOnSeekCompleteListener(null);              //进度条监听为空
            this.mediaPlayer.stop();                                        //播放器停止
            this.mediaPlayer.release();                                     //释放播放器
            this.mediaPlayer=null;                                          //播放器为空
        }setCurrentPlayState(STATE_IDLE);
        if (mCurrentCount < LOAD_TOTAL_COUNT){                             //如果当前加载小于总加载
            mCurrentCount += 1;
            load();                                                         //重新加载

        }else {
            showPlayOrPauseView(false);                                               //显示暂停页面
        }
    }

    //彻底销毁播放器并把所有状态设为初始状态，反注册所有广播
    public void destroy(){
        if (this.mediaPlayer != null){
            this.mediaPlayer.setOnSeekCompleteListener(null);
            this.mediaPlayer.release();
            this.mediaPlayer.stop();
            this.mediaPlayer = null;
        }
        setCurrentPlayState(STATE_IDLE);
        mCurrentCount = 0;
        setIsComplete(false);
        setIsRealPause(false);
        unRegisterBroadcastReceiver();
        showPlayOrPauseView(false);                                   //除了播放和loading外其余任何状态都显示pause


    }
    public void setListener(ADVideoPlayerListener listener){
        this.mlistener = listener;
    }
    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        //播放完毕
        playBack();                                                 //播放器回到初始状态
        setIsComplete(true);
        setIsRealPause(true);
        if (mlistener != null)mlistener.onVideoCompleted();         //设定视频监听接口为结束

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        //真正出错
        setCurrentPlayState(STATE_ERROR);
        if (mCurrentCount >= LOAD_TOTAL_COUNT){
            showPlayOrPauseView(false);
            if (mlistener != null)mlistener.onVideoFailed();
        }
        //出错重试，因为stop中本身就是带有出错重试的所以直接调用即可
        stop();



        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
            //加载成功
        mCurrentCount = 0;                                                            //重试的次数归0
        showPlayViews();
        if (mediaPlayer!=null){
            setCurrentPlayState(STATE_PLAYING);                                     //如果播放器不为空，设置为播放状态
            resume();                                                               //完成播放
            if(mlistener!=null)mlistener.onVideoLoadSuccess();                              //告诉上一层视频加载成功
        }
    }

    private void showPlayViews() {
        mLoadingBar.clearAnimation();
        mLoadingBar.setVisibility(View.GONE);
        mMiniPlayBtn.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if(v == mMiniPlayBtn){
            if (playerState == STATE_PAUSING){                                      //如果播放状态为暂停状态，调用resume
                resume();
            }else{
                load();                                                                        //播放器不在暂停状态，我们应该加载播放器
            }
        }else if(v==mFullBtn){                                                                 //全屏按钮被触发时,因为全屏的时候与播放逻辑没有关系直接回调到上一层监听去处理
            if(mlistener!=null){
                if (mlistener != null)mlistener.onFullScreenButton();

            }else if(v==mVideoView){
                if (mlistener != null)mlistener.onClickVideo();

            }

        }
    }

    @Override       //当此方法加载完毕的时候才能播放视频
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            videoSurface = new Surface(surface);
            checkMediaPlayer();                                                               //如果没有播放器的话直接创建一个，有就不创建
            mediaPlayer.setSurface(videoSurface);                                             // 为播放器设置surface
            load();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE){
                if (isRealPause() || isComplete()){                                         //如果是真正的暂停或者是播放完成
                    //主动暂停不恢复
                     pause();                                                               //调用暂停
                }else {
                    //被动暂停，恢复播放
                    resume();                                                               //如果是被打断的话调用重启
                }
        }else {

            pause();
        }

    }

}
