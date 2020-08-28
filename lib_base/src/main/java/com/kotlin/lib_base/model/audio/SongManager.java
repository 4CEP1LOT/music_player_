package com.kotlin.lib_base.model.audio;

public class SongManager {

    private static SongManager mInstance;
    private SongUrl mSongUrl;

    private SongDetails mSongDetails;

    public SongUrl getmSongUrl() {
        return mSongUrl;
    }


    public void setmSongUrl(SongUrl mSongUrl) {
        this.mSongUrl = mSongUrl;
    }

    public static SongManager getInstance(){
        if(mInstance == null){
            synchronized (SongManager.class){                       //字节码锁，因为我们这个类的字节码锁是唯一的
                if(mInstance == null){
                    mInstance = new SongManager();
                }

            }
        }
        return mInstance;
    }

    public SongDetails getmSongDetails() {
        return mSongDetails;
    }

    public void setmSongDetails(SongDetails mSongDetails) {
        this.mSongDetails = mSongDetails;
    }

}
