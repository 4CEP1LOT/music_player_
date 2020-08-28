package com.kotlin.lib_audio.MediaPlayer.exception;

public class AudioQueueEmptyException extends RuntimeException{

    public  AudioQueueEmptyException(String error) {
        super(error); 
    }
}
