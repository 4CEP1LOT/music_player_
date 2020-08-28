package com.kotlin.lib_audio.MediaPlayer.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongUrl;

@Database(entities = {SongUrl.class, SongDetails.Songs.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SongUrlDao songUrlDao();
    private static final String DB_NAME = "database.db";
    private static AppDatabase instance;

    public AppDatabase () {}

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME).build();
                }
            }
        }
        return instance;
    }
}
