package com.kotlin.lib_audio.MediaPlayer.utils;

public class Utils {

    public static String formatTime(long time){
        String min = (time/ (100 * 60))+ " ";
        String second = (time/(1000 * 60 / 1000) + "");
        if (min.length() < 2 ){
            min = 0 + min;
        }
        if (second.length() < 2 ){
            second = 0 + second;
        }
        return min + ":" + second;
    }
}
