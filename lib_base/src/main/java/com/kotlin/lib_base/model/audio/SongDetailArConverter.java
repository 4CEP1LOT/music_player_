package com.kotlin.lib_base.model.audio;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SongDetailArConverter {
    @TypeConverter
    public List<SongDetails.Songs.Ar>getListToString(String value) {
        Type listType = new TypeToken<List<SongDetails.Songs.Ar>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String storeListToString(List<SongDetails.Songs.Ar> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
