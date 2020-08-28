package com.kotlin.lib_audio.MediaPlayer.core;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;

/**
 * 争夺音频焦点
 * 比如说和其他音乐app之类的争夺焦点
 */
public class AudioFocusManager implements AudioManager.OnAudioFocusChangeListener {

    private AudioFocusListener mAudioFocusListener;
    private AudioManager audioManager;

    public AudioFocusManager(Context context, AudioFocusListener listener) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioFocusListener = listener;
    }

    //请求音频焦点
    public Boolean requestAudioFocus(){
        return audioManager.requestAudioFocus(this,AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    //放弃音频焦点
    public void abandonAudioFocus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioManager.abandonAudioFocus(this);
        }
    }


    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange){
            //重新获得焦点
            case AudioManager.AUDIOFOCUS_GAIN:
                if (mAudioFocusListener != null) mAudioFocusListener.audioFocusGrant();
                break;
            //永久丢失焦点，比如被其他播放器抢占
            case AudioManager.AUDIOFOCUS_LOSS:
                if(mAudioFocusListener != null ) mAudioFocusListener.audioFocusLoss();
                break;
            //短暂丢失焦点，比如说来电
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                if (mAudioFocusListener != null) mAudioFocusListener.audioFocusLoseTransient();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
            //瞬间丢失焦点，比如说通知
                if (mAudioFocusListener != null) mAudioFocusListener.audioFocusLoseDuck();
                break;
        }
    }

    public interface AudioFocusListener {

        //获得焦点回调处理
        void audioFocusGrant();

        //永久失去焦点回调处理
        void audioFocusLoss();

        //短暂失去焦点回调处理
        void audioFocusLoseTransient();

        //瞬间失去焦点回调
        void audioFocusLoseDuck();
    }
}
