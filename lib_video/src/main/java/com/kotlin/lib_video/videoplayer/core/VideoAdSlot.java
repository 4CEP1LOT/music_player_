//package com.kotlin.lib_video.videoplayer.core;
//
//import android.content.Context;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//
//import com.alibaba.android.arouter.facade.annotation.Autowired;
//import com.alibaba.android.arouter.launcher.ARouter;
//import com.kotlin.lib_audio.app.AudioHelper;
//import com.kotlin.lib_audio.app.service.AudioServiceIImpl;
//import com.kotlin.lib_base.surface.audio.AudioService;
//
///**
// * 视频业务逻辑层
// */
//
//public class VideoAdSlot implements CustomVideoView.ADVideoPlayerListener {
//
//    private Context mContext;
//
//    /**
//     * Ui界面
//     */
//    private CustomVideoView mVideoView;
//    private ViewGroup mParentView;
//    private String mXAdInstance;
//
//    /**
//     * data数据
//     */
//    @Autowired(name = "/audio/audio_service")
//    protected AudioService mAudioService;
//    private String mVideoUrl;
//    //context层的事件回调
//    private SDKSlotListener mSlotListener;
//
//
//    public VideoAdSlot(String adInstance, SDKSlotListener slotListener) {
//        ARouter.getInstance().inject(this);                                                             //初始化Arouter确保AutiWire能正常运行
//        mContext =  mParentView.getContext();                                                       //视频播放地址
//        mXAdInstance = adInstance;
//        mSlotListener = slotListener;                                                               //Context层通信的slotListener
//        mParentView = slotListener.getAdParent();                                                   // CustomVideoView要添加到的容器
//        initVideoView();
//    }
//
//
//    private void initVideoView() {
//        mVideoView = new CustomVideoView(mContext);
//        if (mVideoUrl != null){
//            mVideoView.setDataSource(mVideoUrl);
//            mVideoView.setListener(this);
//        }
//        RelativeLayout padding = new RelativeLayout(mContext);
//        padding.setBackgroundColor(mContext.getResources().getColor(android.R.color.black));
//        padding.setLayoutParams(mVideoView.getLayoutParams());
//        mParentView.addView(padding);
//        mParentView.addView(mVideoView);                                    //将视频播放器添加到容器中
//    }
//
//    /*****************************************以下是对CustomVideoView的调用***************************************/
//
//    private boolean isRealPaused(){                                                 //是否是用户暂停
//        if (mVideoView!=null){                                                      //videoView不为空的话
//            return mVideoView.isRealPause();
//        }
//        return false;
//    }
//
//    private boolean isComplete(){                                                   //是否完成
//        if (mVideoView !=null){
//            return mVideoView.isComplete();
//        }
//        return false;
//    }
//
//    private void pauseVideo(){                                                      //是否暂停视频
//        if (mVideoView != null){
//            mVideoView.seekAndPause(0);                                     //跳到指定的进度暂停
//        }
//    }
//
//    private void resumeVideo(){                                                     //继续播放视频
//        if (mVideoView != null){
//            mVideoView.resume();
//        }
//    }
//
//    public void destroy(){                                                          //销毁播放器
//        mVideoView.destroy();
//        mVideoView = null;
//        mContext = null;
//        mVideoUrl = null;
//    }
//
//    /*****************************************以上是对CustomVideoView的调用***************************************/
//
//
//    /*****************************************以下是对play层接口的回调***************************************/
//
//    @Override
//    public void onVideoLoadSuccess() {
//        if (mSlotListener != null){
//            mSlotListener.onVideoLoadSuccess();                         //底层事件抛到最终的context上面
//        }
//
//    }
//
//
//    @Override
//    public void onVideoCompleted() {
//
//    }
//
//    @Override
//    public void onVideoFailed() {
//        if (mSlotListener != null){
//            mSlotListener.onVideoLoadSuccess();                             //
//        }
//
//    }
//
//    /**
//     * 小葵花妈妈课堂之安卓小知识
//     * 一个view里面只能有一个父容器
//     * 如果需要复用videoView就是从小屏videoView里面拿出来放到大屏
//     */
//    @Override
//    public void onFullScreenButton() {
//        mParentView.removeView(mVideoView);                                     //把videoView从父容器中移除
//        VideoFullDialog dialog =  new VideoFullDialog(mContext,mVideoView,mXAdInstance,mVideoView.getCurrentPosition());   //videoView获取当前的地址
//        dialog.setListener(new VideoFullDialog.FullToSmallListener() {              //大屏和小屏之间的回调
//            @Override
//            public void getCurrentPlayPosition(int position) {
//                 //回到小屏继续显示
//                backToSmallMode(position);
//            }
//
//            @Override
//            public void playComplete() {
//                bigPlayComplete(0);
//            }
//        });
//        dialog.setSlotListener(mSlotListener);                              //即便是大屏也要回调事件到最后的Context中
//        dialog.show();
//    }
//    //大屏直到播放结束回到小屏事件
//    private void bigPlayComplete(int position) {
//        if(mVideoView.getParent() == null){                                     //如果父容器为空
//            mParentView.addView(mVideoView);                                    //回到大屏的时候添加进来
//        }
//        mVideoView.isShowFullBtn(true);                                         //显示全屏按钮
//        mVideoView.mute(true);
//        mVideoView.setListener(this);                                           //设置监听
//        mVideoView.seekAndPause(position);                                      //根据position的位置决定在哪里暂停
//        mAudioService.pauseAudio();                                              //进入全屏停止播放外部音乐
//    }
//    //全屏返回小屏继续播放事件
//    private void backToSmallMode(int position) {
//        if(mVideoView.getParent() == null){                                     //如果父容器为空
//            mParentView.addView(mVideoView);                                    //回到小屏的时候添加进来
//        }
//        mVideoView.isShowFullBtn(true);                                         //显示全屏按钮
//        mVideoView.mute(true);                                                  //设置静音
//        mVideoView.setListener(this);                                           //设置监听
//        mVideoView.seekAndPause(0);                                      //跳转到最初位置，因为已经播放完毕
//        mAudioService.resumeAudio();                                              //小屏重启音乐播放器
//    }
//
//
//
//    @Override
//    public void onClickVideo() {
//
//    }
//    //传递消息到appContext层
//
//    public interface SDKSlotListener {
//        ViewGroup getAdParent();
//
//        void onVideoLoadFailed();
//
//        void onVideoLoadSuccess();
//
//        void onVideoLoadComplete();
//    }
//
//
//
//}
