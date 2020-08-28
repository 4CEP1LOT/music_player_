package com.kotlin.lib_audio.MediaPlayer.event;


import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongUrl;

public class AudioLoadEvent {
    public SongDetails.Songs mSongDetails;
    public SongUrl.Data mSongUrl;

    public AudioLoadEvent(){}

    public AudioLoadEvent(SongDetails.Songs mSongDetails, SongUrl.Data mSongUrl) {
        this.mSongDetails = mSongDetails;
        this.mSongUrl = mSongUrl;
    }
}