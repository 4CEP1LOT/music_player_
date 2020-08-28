package com.kotlin.lib_base.model.audio;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SongUrlDataConverter {
    @TypeConverter
    public List<SongUrl.Data> getListToString(String value) {
        Type listType = new TypeToken<List<SongUrl.Data>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String storeListToString(List<SongUrl.Data> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
