//package com.kotlin.lib_video.videoplayer.core;
//
//import android.view.ViewGroup;
//
///**
// * 与外界进行交互
// */
//public class VideoAdContex implements VideoAdSlot.SDKSlotListener {
//
//
//    //
//    private ViewGroup mParentView;                                          //父容器
//    private VideoAdSlot mAdSlot;                                            //此类需要初始化的slot
//    private String mInstance;                                               //视频地址
//    private VideoContextInterface mListener;                                //
//
//    @Override
//    public ViewGroup getAdParent() {
//        return mParentView;
//    }
//
//    @Override
//    public void onVideoLoadFailed() {
//        if(mListener != null) {
//            mListener.onVideoLoadFailed();
//        }
//
//    }
//
//    @Override
//    public void onVideoLoadSuccess() {
//        if(mListener != null) {
//            mListener.onVideoSuccess();
//        }
//
//    }
//
//    @Override
//    public void onVideoLoadComplete() {
//        if(mListener != null) {
//            mListener.onVideoLoadComplete();
//        }
//
//
//    }
//
//    public VideoAdContex(ViewGroup mParentView, String mInstance) {
//        this.mParentView = mParentView;
//        this.mInstance = mInstance;
//        init();
//    }
//
//    /**
//     * 创建videoSLOT
//     *
//     */
//
//    private void init() {
//        if (mInstance != null){
//            mAdSlot = new VideoAdSlot(mInstance,this);                      //如果地址不为空，新建一个videoSlot
//        }else {
//            mAdSlot = new VideoAdSlot(null,this);
//            if (mListener != null){
//                mListener.onVideoFailed();
//            }
//        }
//    }
//
//
//    private class VideoContextInterface {
//        public  void onVideoFailed() {
//        }
//
//        public void onVideoSuccess() {
//        }
//
//        public void onVideoLoadFailed() {
//        }
//
//        public void onVideoLoadComplete() {
//        }
//    }
//}
