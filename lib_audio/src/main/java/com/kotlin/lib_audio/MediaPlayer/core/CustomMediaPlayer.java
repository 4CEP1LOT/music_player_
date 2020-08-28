package com.kotlin.lib_audio.MediaPlayer.core;

import android.media.MediaPlayer;

import androidx.core.view.inputmethod.InputConnectionCompat;

import java.io.IOException;
import java.security.PublicKey;

/**
 * 带状态的MediaPlayer
 * 本身MediaPlayer就是带状态的
 * 为了更加精确控制状态而定义的类
 */

public class CustomMediaPlayer extends MediaPlayer implements MediaPlayer.OnCompletionListener {


    /**
     * 描述播放器状态
     * IDEL 空状态
     * INITIALIZED 初始化
     * STARTED  开始
     * PAUSE    暂停
     * STOPPED  结束
     * COMPLETE 完成
     */
    private OnCompletionListener mCompletionListener;

    public enum Status {

        IDEL, INITIALIZED, STARTED, PAUSED, STOPPED, COMPLETED
    }
    private Status mState ;            //播放器初始化为空状态


    public CustomMediaPlayer() {
        super();
        mState = Status.IDEL;
        super.setOnCompletionListener(this);         //
    }

    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, IllegalStateException, SecurityException {                 //有播放资源了之后
        super.setDataSource(path);
        mState = Status.INITIALIZED;
    }

    public Status getmState() {
        return mState;
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        mState = Status.STARTED;

    }

    @Override
    public void reset() {
        super.reset();
        mState = Status.IDEL;

    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        mState = Status.PAUSED;

    }

    @Override
    public void stop() throws IllegalStateException {
        super.stop();
        mState = Status.STOPPED;

    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        mState =Status.COMPLETED;

    }

    public boolean isCompleted(){
        return mState == Status.COMPLETED;
    }

    public void setOnCompletionListener(OnCompletionListener listener){
            this.mCompletionListener = listener;
    }

}

