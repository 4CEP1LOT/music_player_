package com.kotlin.musiclearning.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kotlin.lib_audio.app.AudioHelper;


public class MusicLearningApplication extends Application {
    private Context context;

    public Context getContext() {
        return context;
    }

    private static MusicLearningApplication application = null;

    public void onCreate() {
        super.onCreate();
        application = this;
        //音频SDK初始化否则Context为空
         AudioHelper.init(this);
        MultiDex.install(this);
//         ARouter的初始化
//         ARouter.init(this);
       

    }
    public static MusicLearningApplication getInstance(){
        return application;}
}
