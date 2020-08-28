package com.kotlin.lib_base.model.audio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kotlin.lib_audio.MediaPlayer.db.AppDatabase;

public class SongViewModel extends AndroidViewModel {


        private AppDatabase myDatabase;
        private LiveData<SongUrl> liveDataSong;


    public SongViewModel(@NonNull Application application) {
        super(application);
        this.myDatabase = AppDatabase.getInstance(application);
        this.liveDataSong = AppDatabase.getInstance(application).songUrlDao().getAll();
    }

    public LiveData<SongUrl> getLiveDataSong()
        {
            return liveDataSong;
        }
    }


