package com.kotlin.lib_audio.MediaPlayer.event;

import com.kotlin.lib_audio.MediaPlayer.core.AudioController;

public class AudioPlayModeEvent {

    public AudioPlayModeEvent() {
    }

    public AudioController.PlayMode mPlayMode;

    public AudioPlayModeEvent(AudioController.PlayMode mPlayMode) {
        this.mPlayMode = mPlayMode;
    }
}
