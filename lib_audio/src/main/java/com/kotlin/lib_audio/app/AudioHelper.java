package com.kotlin.lib_audio.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.kotlin.lib_audio.MediaPlayer.core.AudioController;
import com.kotlin.lib_audio.MediaPlayer.core.MusicService;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongUrl;

import java.util.List;

/**
 * sdk全局Context，功子模块调用
 *
 */
public final class AudioHelper extends Application{

    private static Context mContext;

    private static  SongUrl.Data mSongUrl;
    private static SongDetails.Songs mSongDetail;

    public static void init(Context context){
        mContext = context;
//        GreenDaoHelper.initDatabase();q
    }

    public static void addAudio(Activity activity, SongUrl.Data bean, SongDetails.Songs data) {
        AudioController.getInstance().addAudio(bean,data);
    }

    public static void pauseAudio() {
        AudioController.getInstance().pause();
    }

    public static void resumeAudio() {
        AudioController.getInstance().resume();
    }


    public static SongUrl.Data getSongUrl() {
        return mSongUrl;
    }



    public static void startMusicService(SongUrl.Data audios, SongDetails.Songs songDetail){
        MusicService.startMusicService(audios,songDetail);
    }




    public static Context getContext(){
        return mContext;
    }

}
