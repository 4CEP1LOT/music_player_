package com.kotlin.lib_audio.app.service;

import android.content.Context;

import com.kotlin.lib_audio.MediaPlayer.core.AudioController;
import com.kotlin.lib_audio.MediaPlayer.core.MusicService;
import com.kotlin.lib_base.surface.audio.AudioService;

/**
 * AudioService实现类，Arouter之后开放给外部的接口
 */
public class AudioServiceIImpl implements AudioService {


    @Override
    public void pauseAudio() {
        AudioController.getInstance().pause();
    }

    @Override
    public void resumeAudio() {
        AudioController.getInstance().resume();
    }

    @Override
    public void init(Context context) {

    }
}
