package com.kotlin.lib_audio.MediaPlayer.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kotlin.lib_base.model.audio.SongUrl;

@Dao
public interface SongUrlDao {
    @Query("SELECT * FROM songdata")
    LiveData<SongUrl> getAll();

    @Insert
    void insertAll(SongUrl... data);

    @Delete
    void delete(SongUrl data);



}
