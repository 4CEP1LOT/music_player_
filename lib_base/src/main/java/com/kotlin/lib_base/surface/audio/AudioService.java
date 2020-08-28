package com.kotlin.lib_base.surface.audio;

import android.content.Context;


/**
 音频基础库对外接口
 */

public interface AudioService {
    void pauseAudio();
    void resumeAudio();

    void init(Context context);
}
