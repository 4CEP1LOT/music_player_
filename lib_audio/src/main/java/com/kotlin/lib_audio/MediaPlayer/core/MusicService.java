package com.kotlin.lib_audio.MediaPlayer.core;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.kotlin.lib_audio.MediaPlayer.event.AudioFavEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioLoadEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioPauseEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioStartEvent;
import com.kotlin.lib_audio.MediaPlayer.view.NotificationHelper;
import com.kotlin.lib_audio.app.AudioHelper;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.List;

import static com.kotlin.lib_audio.MediaPlayer.view.NotificationHelper.NOTIFICATION_ID;

/**
 * 音乐后台服务
 * 负责更新notification状态
 */
public class MusicService extends Service implements NotificationHelper.NotificationHelperListener {
    /**
     * 常量区
     */

    private static String DATA_AUDIOS = "AUDIOS";
    private static String DATA_DETAILS = "DETAILS";
    private static String ACTION_START = "ACTION_START";

    /**
     * data
     */
    private NotificationReceiver mReceiver;               //
    private SongUrl.Data mSongUrl;
    private SongDetails.Songs mSongDetail;

    /**
     * 外部启动Service方法
     */

    public static void startMusicService(SongUrl.Data mSongUrl,SongDetails.Songs mSongDetail) {
        Intent intent = new Intent(AudioHelper.getContext(), MusicService.class);
        intent.setAction(ACTION_START);
        //将mList的数据传进来
        intent.putExtra(DATA_AUDIOS, (Serializable) mSongUrl);
        intent.putExtra(DATA_DETAILS, (Serializable) mSongDetail);
        AudioHelper.getContext().startService(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        registerBroadcastReceiver();
    }

    /**
     * 启动服务
     * 取出AudioBean的文件
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mSongUrl = (SongUrl.Data) intent.getSerializableExtra(DATA_AUDIOS);
        mSongDetail = (SongDetails.Songs) intent.getSerializableExtra(DATA_DETAILS);
            //播放音乐
            playMusic();
            //初始化notification
            NotificationHelper.getInstance().init(this);               //初始化Notification。参数为借口

        return START_REDELIVER_INTENT;
    }




    private void playMusic() {
        AudioController.getInstance().addAudio(mSongUrl,mSongDetail);                //放入数据进入AudioController
        AudioController.getInstance().play();                               //播放歌曲
    }

    /**
     * 反注册所有的方法
     */


    /**
     * 注册BroadcastReceiver
     */

    private void registerBroadcastReceiver() {
        if (mReceiver == null) {
            mReceiver = new NotificationReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(NotificationReceiver.ACTION_STATUS_BAR);
            registerReceiver(mReceiver, filter);
        }
    }

    /**
     * 反注册BroadCastReceiver
     */
    private void unregisterBroadcastReceiver() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }


    /**
     * 回调Notification并绑定Service
     * 通过id得到要绑定的notification
     */
    @Override
    public void onNotificationInit() {
        //绑定notification与Service，并使服务成为前台服务
        startForeground(NOTIFICATION_ID, NotificationHelper.getInstance().getNotification());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioLoadEvents(AudioLoadEvent event) {
        //Service接收到播放器传入过来的Load事件后，更新Notification为加载状态
        NotificationHelper.getInstance().showLoadStatus(event.mSongDetails);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioStartEvents(AudioStartEvent event) {
        //更新Notification为播放状态
        NotificationHelper.getInstance().showPlayStatus();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioPauseEvents(AudioPauseEvent event) {
        //更新Notification为暂停 状态
        NotificationHelper.getInstance().showPauseStatus();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioFavEvents(AudioFavEvent event) {
        NotificationHelper.getInstance().onChangedFavouriteStatus(event.isFavourite);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterBroadcastReceiver();
    }


    /**
     * 接收Notification发送的广播
     */

    public static class NotificationReceiver extends BroadcastReceiver {
        public static final String ACTION_STATUS_BAR =
                AudioHelper.getContext().getPackageName() + ".NOTIFICATION";
        public static final String EXTRA = ".extra";
        public static final String EXTRA_PLAY = "play_pause";
        public static final String EXTRA_NEXT = "play_next";
        public static final String EXTRA_PRE = "play_previous";
        public static final String EXTRA_FAV = "play_favourite";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                return;
            }
            String extra = intent.getStringExtra(EXTRA);
            switch (extra) {
//                case EXTRA_FAV:
//                    AudioController.getInstance().onFavEvent();
//                    break;
                case EXTRA_NEXT:
                    AudioController.getInstance().next();

                    break;
                case EXTRA_PRE:
                    AudioController.getInstance().previous();

                    break;
                case EXTRA_PLAY:
                    AudioController.getInstance().playOrPause();

                    break;


            }



        }
    }
}