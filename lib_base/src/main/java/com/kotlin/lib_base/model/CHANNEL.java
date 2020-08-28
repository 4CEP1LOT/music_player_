package com.kotlin.lib_base.model;

public enum CHANNEL {

//    首页卡片的常量全部封装在枚举类里

    MY("我的", 0x01),
    DISCOVERY("发现", 0x02),
//    FRIEND("朋友", 0x03),
    MLOG("mlog", 0x04);

    public static final int MINE_ID = 0x01;
    public static final int DISCOVERY_ID = 0x02;
//    public static final int FRIEND_ID = 0x03;
    public static final int MLOG_ID = 0x04;



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










