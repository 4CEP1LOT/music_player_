//package com.kotlin.lib_video.videoplayer.core;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.KeyboardShortcutGroup;
//import android.view.Menu;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewTreeObserver;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.animation.LinearInterpolator;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.Nullable;
//
//import com.kotlin.lib_video.R;
//import com.kotlin.lib_video.videoplayer.utils.Utils;
//
//import java.util.List;
//
//
///**
// * @author: qndroid
// * @function: 全屏显示视频
// */
//public class VideoFullDialog extends Dialog implements CustomVideoView.ADVideoPlayerListener {
//
//    private static final String TAG = VideoFullDialog.class.getSimpleName();
//    private CustomVideoView mVideoView;
//
//    private RelativeLayout mRootView;
//    private ViewGroup mParentView;
//
//    private int mPosition;
//    private FullToSmallListener mListener;
//    private boolean isFirst = true;
//    //动画要执行的平移值
//    private int deltaY;
//    private VideoAdSlot.SDKSlotListener mSlotListener;
//    private Bundle mStartBundle;
//    private Bundle mEndBundle; //用于Dialog出入场动画
//
//    public VideoFullDialog(Context context, CustomVideoView mraidView, String instance,
//                           int position) {
//        super(context, R.style.dialog_full_screen);
//        mPosition = position;
//        mVideoView = mraidView;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.dialog_video_layout);
//        initVideoView();
//    }
//
//    public void setViewBundle(Bundle bundle) {
//        mStartBundle = bundle;
//    }
//
//    public void setListener(FullToSmallListener listener) {
//        this.mListener = listener;
//    }
//
//    public void setSlotListener(VideoAdSlot.SDKSlotListener slotListener) {
//        this.mSlotListener = slotListener;
//    }
//
//    private void initVideoView() {
//        mParentView = (RelativeLayout) findViewById(R.id.content_layout);           //初始化mParentView为根布局而不是外部传进来的
//        mRootView = findViewById(R.id.root_view);                                   //
//        mRootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickVideo();
//            }
//        });
//        mRootView.setVisibility(View.INVISIBLE);
//
//        mVideoView.setListener(this);
//        mVideoView.mute(false);
//        mParentView.addView(mVideoView);                                             //添加videoView到父容器(viewTree）中
//        mParentView.getViewTreeObserver()                                             //
//                .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                    public boolean onPreDraw() {
//                        mParentView.getViewTreeObserver().removeOnPreDrawListener(this);
//                        prepareScene();
//                        runEnterAnimation();
//                        return true;
//                    }
//                });
//    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        mVideoView.isShowFullBtn(false); //防止第一次，有些手机仍显示全屏按钮
//        if (!hasFocus) {
//            mPosition = mVideoView.getCurrentPosition();
//            mVideoView.pauseForFullScreenpa();
//        } else {
//            if (isFirst) { //为了适配某些手机不执行seekandresume中的播放方法
//                mVideoView.seekAndResume(mPosition);
//            } else {
//                mVideoView.resume();
//            }
//        }
//        isFirst = false;
//    }
//
//    @Override
//    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    @Override
//    public void dismiss() {
//        //一定要从全屏移除videoView，这样保证小屏能加入新的videoVIew到viewTree中
//        mParentView.removeView(mVideoView);
//        super.dismiss();
//    }
//
//
//    public void onBackPressed() {
//        onClickBackBtn();
//        //super.onBackPressed(); 禁止掉返回键本身的关闭功能,转为自己的关闭效果
//    }
//    public void onClickFullScreenBtn() {
//        onClickVideo();
//    }
//
//    @Override
//    public void onVideoLoadSuccess() {
//
//    }
//
//    @Override
//    public void onVideoCompleted() {
//
//    }
//
//    @Override
//    public void onVideoFailed() {
//
//    }
//
//    @Override
//    public void onFullScreenButton() {
//
//    }
//
//    public void onClickVideo() {
//    }
//
//
//    public void onClickBackBtn() {
//        //点击返回事件处理
//        runExitAnimator();
//    }
//
//    //准备动画所需数据
//    private void prepareScene() {
//        mEndBundle = Utils.getViewProperty(mVideoView);
//        /**
//         * 将desationview移到originalview位置处
//         */
//        deltaY = (mStartBundle.getInt(Utils.PROPNAME_SCREENLOCATION_TOP) - mEndBundle.getInt(
//                Utils.PROPNAME_SCREENLOCATION_TOP));
//        mVideoView.setTranslationY(deltaY);
//    }
//
//    //准备入场动画
//    private void runEnterAnimation() {
//        mVideoView.animate()
//                .setDuration(200)
//                .setInterpolator(new LinearInterpolator())
//                .translationY(0)
//                .withStartAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRootView.setVisibility(View.VISIBLE);
//                    }
//                })
//                .start();
//    }
//
//    //准备出场动画
//    private void runExitAnimator() {
//        mVideoView.animate()
//                .setDuration(200)
//                .setInterpolator(new LinearInterpolator())
//                .translationY(deltaY)
//                .withEndAction(new Runnable() {
//                    @Override
//                    public void run() {
//                        dismiss();
//                        if (mListener != null) {
//                            mListener.getCurrentPlayPosition(mVideoView.getCurrentPosition());
//                        }
//                    }
//                })
//                .start();
//    }
//
//    public void onAdVideoLoadSuccess() {
//        if (mVideoView != null) {
//            mVideoView.resume();
//        }
//    }
//
//
//
//    /**
//     * 与slot小屏播放进行交互
//     */
//    public interface FullToSmallListener {
//        void getCurrentPlayPosition(int position);                      //全屏播放中返回
//
//        void playComplete();                                            //全屏播放结束时回调
//    }
//}
