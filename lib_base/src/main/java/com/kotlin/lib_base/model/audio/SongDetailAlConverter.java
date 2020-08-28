package com.kotlin.lib_base.model.audio;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SongDetailAlConverter {
    @TypeConverter
    public SongDetails.Songs.Al getListToString(String value) {
        Type listType = new TypeToken<SongDetails.Songs.Al>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String storeListToString(SongDetails.Songs.Al list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
