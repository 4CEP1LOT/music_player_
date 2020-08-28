package com.kotlin.lib_search.model;

public enum CHANNEL {


    ALL("综合", 1),
    SONG("单曲", 2),
    SINGER("歌手", 3),
    ALBUM("专辑", 4),
    VIDEO_PLAYER("视频", 5);

    public static final int ALL_ID = 1;
    public static final int SONG_ID = 2;
    public static final int SINGER_ID = 3;

    public static final int ALBUM_ID = 4;
    public static final int VIDEO_PLAYER_ID = 5;








    private final String key;

    private final int value;

     CHANNEL(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
