package com.kotlin.lib_audio.MediaPlayer.event;

import com.kotlin.lib_audio.MediaPlayer.core.AudioPlayer;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongUrl;


public class AudioErrorEvent {
    public SongUrl.Data mSongUrl;
    public SongDetails.Songs mSongDetails;

    public AudioErrorEvent(int code) {
        AudioPlayer mAudioPlayer = new AudioPlayer();
        if (code == 1){
            mAudioPlayer.onError(null,0,1);
        }else{
            mAudioPlayer.load(mSongUrl,mSongDetails);

        }
    }
}