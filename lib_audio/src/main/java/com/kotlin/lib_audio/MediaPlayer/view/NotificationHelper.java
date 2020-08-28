package com.kotlin.lib_audio.MediaPlayer.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.kotlin.lib_audio.MediaPlayer.core.AudioController;
import com.kotlin.lib_audio.MediaPlayer.core.MusicService;
import com.kotlin.lib_audio.R;
import com.kotlin.lib_audio.app.AudioHelper;
import com.kotlin.lib_base.event.SongDetailsEvent;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongUrl;
import com.kotlin.lib_image_loader.ImageLoaderManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * notification的帮助类
 *
 *完成notification的创建和初始化
 *
 *对外提供更新notification的方法
 *
 * notification无法直接调用本地代码，因为是在系统进程中
 */

public class NotificationHelper {

    public static final String CHANNEL_ID ="channel_id_audio";
    public static final String CHANNEL_NAME = "channel_name_audio";
    public static final int NOTIFICATION_ID = 0x111;                                //提醒id
    /**
     * Ui的部分
     */
    //最终的notification的显示类
    private Notification mNotification;
    private RemoteViews mRemoteViews;                      //大布局
    private RemoteViews mSmallRemotesViews;                 //小布局
    private NotificationManager mNotificationManager;           //
    private ImageView imageView;

    /**
     * data部分
     */
    private NotificationHelperListener mListener;           //与Service的回调通信
    private String packageName;
    private List<SongDetails.Songs> mList;
    private SongDetails mSongs;
    private List<SongUrl.Data> mData;
    private SongUrl.Data mDatas;

    /**
     *
     * PendingIntent 是 Android 提供的一种用于外部程序调起自身程序的能力，生命周期不与主程序相关。
     * 外部程序通过 PendingIntent 只能调用起三种组件：
     *     Activity
     *     Service
     *     Broadcast
     *
     * PendingIntent 的使用场景有三个：
     *
     *     使用 AlarmManager 设定闹钟
     *     在系统状态栏显示 Notification
     *     在桌面显示 Widget
     *
     */
    private PendingIntent pendingIntent;
    private SongDetails.Songs mSongUrl;


    /**
     * 当前要播放歌曲的资料
     */



    public static NotificationHelper getInstance(){return SingletonHolder.instance;}

    private static class SingletonHolder{
        private static NotificationHelper instance = new NotificationHelper();
    }


    /**
     * 初始化所有变量
     * @param listener      返回一个listener和Service通信
     */
    public void init(NotificationHelperListener listener){
        mNotificationManager = (NotificationManager) AudioHelper.getContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);                                    //获取Notification
        packageName = AudioHelper.getContext().getPackageName();
        mSongUrl = AudioController.getInstance().getNowDetails();
        initNotification();
        mListener = listener;
        if (mListener != null)mListener.onNotificationInit();


    }

    private void initNotification() {
        if (mNotification == null){
            initRemoteViews(mSongUrl);                              //创建Notification布局
            Intent intent = new Intent(AudioHelper.getContext(),FullscreenActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(AudioHelper.getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);                  //设定重要性为高
                channel.enableLights(false);
                channel.enableVibration(false);
                mNotificationManager.createNotificationChannel(channel);

            }
            NotificationCompat.Builder builder =                                                        //设置各个控件的具体功能
                    new NotificationCompat.Builder(AudioHelper.getContext(),CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)                 //设置通知栏小图标
                    .setCustomBigContentView(mRemoteViews)              //设置大布局
                    .setContent(mSmallRemotesViews);                    //设置小布局
            mNotification = builder.build();

            showLoadStatus(mSongUrl);

        }
    }

    private void initRemoteViews(SongDetails.Songs mDetailSong) {
        /**
         * 为大视图设置歌曲名称，歌手名称
         */
        int layoutId = R.layout.notification_big_layout;
        int smallLayoutId = R.layout.notification_small_layout;
        mRemoteViews = new RemoteViews(packageName, layoutId);
        mSmallRemotesViews = new RemoteViews(packageName, smallLayoutId);

            mRemoteViews.setTextViewText(R.id.nf_song_name, mDetailSong.getName());
            mRemoteViews.setTextViewText(R.id.nf_singer_name, mDetailSong.getAr().get(0).getName());


//        if (GreenDaoHelper.QueryFavourite(mSongs) != null) {
//            //收藏，如果收藏过的话变成红色实心
//            mRemoteViews.setImageViewResource(R.id.nf_favorite_buttom, R.mipmap.note_btn_loved);
//        } else {
//            //没有的话是空心
//            mRemoteViews.setImageViewResource(R.id.nf_favorite_buttom, R.mipmap.note_btn_love_white);
//        }

        /**
         * 为小视图设置歌曲名称，歌手名称
         */
        mRemoteViews.setTextViewText(R.id.nfs_song, mDetailSong.getName());
        mRemoteViews.setTextViewText(R.id.nfs_singer, mDetailSong.getAr().get(0).getName());



        //点击播放按钮要发送的广播
        Intent playIntent =  new Intent(MusicService.NotificationReceiver.ACTION_STATUS_BAR);
        playIntent.putExtra(MusicService.NotificationReceiver.EXTRA,MusicService.NotificationReceiver.EXTRA_PLAY);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(AudioHelper.getContext(),1,
                playIntent,PendingIntent.FLAG_UPDATE_CURRENT);                                  //

        mRemoteViews.setOnClickPendingIntent(R.id.nf_play,playPendingIntent);                 //发送到大布局
        mRemoteViews.setImageViewResource(R.id.nf_play,R.mipmap.note_btn_play_white);
        mSmallRemotesViews.setOnClickPendingIntent(R.id.nfs_play,playPendingIntent);            //发送到小布局
        mSmallRemotesViews.setImageViewResource(R.id.nfs_play,R.mipmap.note_btn_play_white);





        //上一首按钮要发送的广播

        Intent previousIntent =  new Intent(MusicService.NotificationReceiver.ACTION_STATUS_BAR);
        previousIntent.putExtra(MusicService.NotificationReceiver.EXTRA,MusicService.NotificationReceiver.EXTRA_PRE);
        PendingIntent previousPendingIntent = PendingIntent.getBroadcast(AudioHelper.getContext(),2,
                previousIntent,PendingIntent.FLAG_UPDATE_CURRENT);                                  //

        mRemoteViews.setOnClickPendingIntent(R.id.nf_previous,previousPendingIntent);                 //发送到大布局
        mRemoteViews.setImageViewResource(R.id.nf_previous,R.mipmap.note_btn_pre_white);



        //下一首要发送的广播
        Intent nextIntent =  new Intent(MusicService.NotificationReceiver.ACTION_STATUS_BAR);
        nextIntent.putExtra(MusicService.NotificationReceiver.EXTRA,MusicService.NotificationReceiver.EXTRA_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(AudioHelper.getContext(),3,
                nextIntent,PendingIntent.FLAG_UPDATE_CURRENT);                                  //

        mRemoteViews.setOnClickPendingIntent(R.id.nf_next,nextPendingIntent);                 //发送到大布局
        mSmallRemotesViews.setOnClickPendingIntent(R.id.nfs_next,nextPendingIntent);                 //发送到大布局
        mRemoteViews.setImageViewResource(R.id.nf_next,R.mipmap.note_btn_next_white);
        mSmallRemotesViews.setImageViewResource(R.id.nfs_next,R.mipmap.note_btn_next_white);


        //收藏要发送的广播
        Intent favIntent =  new Intent(MusicService.NotificationReceiver.ACTION_STATUS_BAR);
        favIntent.putExtra(MusicService.NotificationReceiver.EXTRA,MusicService.NotificationReceiver.EXTRA_FAV);
        PendingIntent favPendingIntent = PendingIntent.getBroadcast(AudioHelper.getContext(),4,
                favIntent,PendingIntent.FLAG_UPDATE_CURRENT);                                  //

        mRemoteViews.setOnClickPendingIntent(R.id.nf_favorite_buttom,favPendingIntent);                 //发送到大布局


    }

    public void onChangedFavouriteStatus(boolean isFavourite){
        if (mRemoteViews != null){
            mRemoteViews.setImageViewResource(R.id.nf_favorite_buttom,
                    isFavourite ? R.mipmap.note_btn_loved : R.mipmap.note_btn_love_white);
            mNotificationManager.notify(NOTIFICATION_ID,mNotification);
        }
    }
    /*8
    更新为加载状态
     */
    public void showLoadStatus( SongDetails.Songs mDetailSong){
            //更新大布局

                mRemoteViews.setImageViewResource(R.id.nf_play, R.mipmap.note_btn_play_white);
                mRemoteViews.setTextViewText(R.id.nf_song_name, mDetailSong.getName());
//        mRemoteViews.setTextViewText(R.id.nf_singer_name,bean.get(i).name);

                //为Notification中的专辑图片加载图片
                ImageLoaderManager.getInstance().displayImageForNotification(
                        AudioHelper.getContext(),
                        mRemoteViews, R.id.nf_album_image,
                        mNotification, NOTIFICATION_ID, mDetailSong.getAl().getPicUrl()
                );

//                //更新收藏状态
//                if (GreenDaoHelper.QueryFavourite(mSongs) != null) {
//                    //收藏，如果收藏过的话变成红色实心
//                    mRemoteViews.setImageViewResource(R.id.nf_favorite_buttom, R.mipmap.note_btn_loved);
//                } else {
//                    //没有的话是空心
//                    mRemoteViews.setImageViewResource(R.id.nf_favorite_buttom, R.mipmap.note_btn_love_white);
//                }
                //更新小布局
                mSmallRemotesViews.setImageViewResource(R.id.nfs_play, R.mipmap.note_btn_play_white);
                mSmallRemotesViews.setTextViewText(R.id.nfs_song, mDetailSong.getName());
                mSmallRemotesViews.setTextViewText(R.id.nfs_singer, mDetailSong.getAr().get(0).getName());
                //为Notification中的专辑图片加载图片
                ImageLoaderManager.getInstance().displayImageForNotification(
                        AudioHelper.getContext(),
                        mSmallRemotesViews, R.id.nfs_album,
                        mNotification, NOTIFICATION_ID,mDetailSong.getAl().getPicUrl()
                );
                mNotificationManager.notify(NOTIFICATION_ID, mNotification);
            }



    public void showPlayStatus(){
        if(mRemoteViews != null){
            mRemoteViews.setImageViewResource(R.id.nf_play,R.mipmap.note_btn_pause_white);
            mSmallRemotesViews.setImageViewResource(R.id.nfs_play,R.mipmap.note_btn_pause_white);
            mNotificationManager.notify(NOTIFICATION_ID,mNotification);                 //通知系统进场更改状态

        }


    }

    public void showPauseStatus(){

        if(mRemoteViews != null){
            mRemoteViews.setImageViewResource(R.id.nf_play,R.mipmap.note_btn_play_white);
            mSmallRemotesViews.setImageViewResource(R.id.nfs_play,R.mipmap.note_btn_play_white);
            mNotificationManager.notify(NOTIFICATION_ID,mNotification);
    }
}
    public Notification getNotification(){
        return mNotification;
    }


    public interface NotificationHelperListener {
        void onNotificationInit();
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSongDetails(SongDetailsEvent event){
        try {
            mList = SongManager.getInstance().getmSongDetails().getSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }







}

