package com.kotlin.lib_base.model.audio;

import java.util.List;

public class SongCollection {

    List<SongDetails.Songs> mSongs;
    List<SongUrl.Data> mUrls;


    public List<SongDetails.Songs> getmSongs() {
        return mSongs;
    }

    public void setmSongs(List<SongDetails.Songs> mSongs) {
        this.mSongs = mSongs;
    }

    public List<SongUrl.Data> getmUrls() {
        return mUrls;
    }

    public void setmUrls(List<SongUrl.Data> mUrls) {
        this.mUrls = mUrls;
    }
}
