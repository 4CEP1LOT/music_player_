package com.kotlin.lib_audio.MediaPlayer.view.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.kotlin.lib_audio.MediaPlayer.utils.DisplayUtils;


/**
 * 作者：leavesC
 * 时间：2019/4/25 10:36
 * 描述：
 */
public class BaseView extends View {

    protected final String TAG = getClass().getSimpleName();

    public BaseView(Context context) {
        super(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int getSize(int measureSpec, int defaultSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = 0;
        switch (mode) {
            case MeasureSpec.AT_MOST: {
                size = Math.min(MeasureSpec.getSize(measureSpec), defaultSize);
                break;
            }
            case MeasureSpec.EXACTLY: {
                size = MeasureSpec.getSize(measureSpec);
                break;
            }
            case MeasureSpec.UNSPECIFIED: {
                size = defaultSize;
                break;
            }
        }
        return size;
    }

    protected int dp2px(float dpValue) {
        return DisplayUtils.dp2px(getContext(), dpValue);
    }

    protected int sp2px(float spValue) {
        return DisplayUtils.sp2px(getContext(), spValue);
    }

}
