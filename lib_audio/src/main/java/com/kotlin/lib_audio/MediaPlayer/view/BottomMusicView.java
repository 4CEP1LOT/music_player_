package com.kotlin.lib_audio.MediaPlayer.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kotlin.lib_audio.MediaPlayer.core.AudioController;
import com.kotlin.lib_audio.MediaPlayer.event.AudioPauseEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioProgressEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioStartEvent;
import com.kotlin.lib_base.event.SongDetailEvent;

import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_audio.R;
import com.kotlin.lib_image_loader.ImageLoaderManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class BottomMusicView extends RelativeLayout {
    private Context mContext;
    private List<SongDetails.Songs> mSongUrl;

    private ImageView mPlay;
    private ImageView mPlayList;
    private TextView mSongName;
    private TextView mSinger;
    private ImageView mLeftView;
    private int index = 0;


    public BottomMusicView(Context context) {
        this(context, null);
    }

    public BottomMusicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        EventBus.getDefault().register(this);
        initView();



    }


    private void initView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.bottom_view, this);
        rootView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                 FullscreenActivity.start((Activity)mContext);
            }
        });
        mPlay = findViewById(R.id.bv_play_view);
        Log.d("TAG", "播放按钮");
        mPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioController.getInstance().playOrPause();                           //因为一开始是空的IDEL状态不会进入到playOrpause状态所以需要从play开
                Log.d("TAG", "mPlay");
            }
        });
        mPlayList = findViewById(R.id.bv_play_list);
        mPlayList.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSinger = findViewById(R.id.singer_name);
        mSongName = findViewById(R.id.song_name);
        mLeftView = rootView.findViewById(R.id.album_view);
        /**
         * 专辑转圈动画效果，mLeftView动画的目标控件，ROTATION表示动画为旋转动画，旋转总数为360度
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLeftView, View.ROTATION.getName(), 360);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());                             //动画拦截器
        animator.setRepeatCount(-1);                                                    //-1为无限循环
        animator.start();

        if (AudioController.getInstance().isPauseState()) {

            animator.pause();                                                               //动画开始

        }

        onAudioStartEvent(new AudioStartEvent());

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSongDetail(SongDetailEvent event) {
        mSongUrl = SongManager.getInstance().getmSongDetails().getSongs();

    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onAudioLoadEvent(AudioLoadEvent event){
//        //更新当前View为load状态
//        showLoadView();
//        Log.d("TAG","SHOWLOADVIEW");
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioPauseEvent(AudioPauseEvent event) {
        showPauseView();

    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onAudioStartEvent(AudioStartEvent event) {
        showPlayView();

    }


    public void onAudioProgressEvent(AudioProgressEvent event) {

    }


    private void showPlayView() {
        if (AudioController.getInstance().isStartState()) {
            mPlay.setImageResource(R.mipmap.note_btn_pause_white);                                           //因为播放状态下即将暂停所以是暂停键
           // Glide.with(mContext).load(mSongUrl.get(0).getAl().getPicUrl()).circleCrop().into(mLeftView);
            ImageLoaderManager.getInstance().displayImageForCircleView(mLeftView,mSongUrl.get(0).getAl().getPicUrl());
            mSongName.setText(mSongUrl.get(AudioController.getInstance().getPlayindex()).getName());
            mSinger.setText(mSongUrl.get(AudioController.getInstance().getPlayindex()).getAr().get(0).getName());
        }
    }


    private void showPauseView() {
        if (mSongUrl != null) {
            mPlay.setImageResource(R.mipmap.note_btn_play_white);               //因为暂停状态下即将播放所以是播放键

        }
    }





}
