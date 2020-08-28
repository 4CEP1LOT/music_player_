package com.kotlin.lib_audio.MediaPlayer.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.kotlin.lib_audio.MediaPlayer.core.AudioController;
import com.kotlin.lib_audio.MediaPlayer.core.CustomMediaPlayer;
import com.kotlin.lib_audio.MediaPlayer.event.AudioLoadEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioPauseEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioPlayModeEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioProgressEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioStartEvent;
import com.kotlin.lib_audio.MediaPlayer.view.adapter.MusicPlayerAdapter;
import com.kotlin.lib_audio.MediaPlayer.view.adapter.WaveView;
import com.kotlin.lib_audio.R;
import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongUrl;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnPageChangeListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements DisposeDataListener {
    public List<SongDetails.Songs> mSongs;
    private List<SongUrl.Data> mUrl;
    SongUrl.Data mData = new SongUrl.Data();

    public int position;

    private Banner banner;
    private RelativeLayout mRelativeLayout;
    private ImageView mPlayMode, mPlayButton, mPreButton, mNextButton;
    private AudioController.PlayMode playMode;
    private SeekBar mProgressView;
    private AudioController.PlayMode mAudioPlayMode;
    private WaveView waveView1,waveView2;

    public static void start(Activity context) {
        Intent intent = new Intent(context, FullscreenActivity.class);
        ActivityCompat.startActivity(context, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(context).toBundle());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSongDetail(SongDetails event) {

        mSongs = SongManager.getInstance().getmSongDetails().getSongs();
    }

//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onSongUrl(SongUrl event){
//
//        mUrl = SongManager.getInstance().getmSongUrl().getData();
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        onSongDetail(new SongDetails());
//        onSongUrl(new SongUrl());
        onWindowFocusChanged(true);
        hide();

        setContentView(R.layout.activity_fullscreen);
//        RecyclerView mRecyclerView = findViewById(R.id.recycler_music);
//        mRecyclerView.setLayoutManager(new OverFlyingLayoutManager(0.75f,385, OverFlyingLayoutManager.HORIZONTAL));
//        MusicAdapter mAdapter = new MusicAdapter(mSongs);
//        mRecyclerView.setAdapter(mAdapter);
        MusicPlayerAdapter mAdapter = new MusicPlayerAdapter(mSongs);
        banner = findViewById(R.id.music_banner);
        mRelativeLayout = findViewById(R.id.music_relative);
//       setBackGround(banner.getCurrentItem());
        initView();
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者

                .isAutoLoop(false)
                .setBannerGalleryMZ(30)
                .setAdapter(mAdapter)
                .addOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
        initData();



    }

    private void initView() {
        mPlayButton = findViewById(R.id.music_button_play);
        mNextButton = findViewById(R.id.music_button_next);
        mPreButton = findViewById(R.id.music_button_pre);
        mPlayMode = findViewById(R.id.music_button_playmode);
        playMode = AudioController.getInstance().getmPlayMode();
        mProgressView = findViewById(R.id.music_progress);
        waveView1= findViewById(R.id.wave_view);
    }

    private void initData() {

        mNextButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                RequestCenter.SongUrl(FullscreenActivity.this, (long) position);
                AudioController.getInstance().next();

                return false;
            }
        });
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioController.getInstance().previous();

            }
        });

        waveView1.setBgColor(Color.parseColor("#FF6262"));
        waveView1.setWaveScaleHeight(0.08f);


        updatePlayModeView();

        onAudioPauseEvent(new AudioPauseEvent());
        onAudioPlayModeEvent(new AudioPlayModeEvent());
        onAudioStartEvent(new AudioStartEvent());
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioPauseEvent(AudioPauseEvent event) {
        //更新activity为暂停状态
        showPauseView();
    }

    private void showPauseView() {

        mPlayButton.setImageResource(R.drawable.ic_play);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioStartEvent(AudioStartEvent event) {
        //更新activity为播放状态
        showPlayView();
    }

    private void showPlayView() {
        mPlayButton.setImageResource(R.drawable.ic_baseline_pause_24);

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onAudioFavouriteEvent(AudioFavouriteEvent event) {
//        //更新activity收藏状态
//        changeFavouriteStatus(true);
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioPlayModeEvent(AudioPlayModeEvent event) {
        mAudioPlayMode = event.mPlayMode;
        //更新播放模式
        updatePlayModeView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioProgessEvent(AudioProgressEvent event) {
        int totalTime = event.maxLength;
        int currentTime = event.progress;
        //更新时间
        mProgressView.setProgress(currentTime);
        mProgressView.setMax(totalTime);
        if (event.mStatus == CustomMediaPlayer.Status.PAUSED) {
            showPauseView();
        } else {
            showPlayView();
        }
    }


    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void updatePlayModeView() {
        switch (playMode) {
            case LOOP:
                mPlayMode.setImageResource(R.mipmap.player_loop);
                break;
            case RANDOM:
                mPlayMode.setImageResource(R.mipmap.player_random);
                break;
            case REPEAT:
                mPlayMode.setImageResource(R.mipmap.player_once);
                break;
        }
    }

    @Override
    public void onSuccess(Object responseObj) {
//        SongUrl mSongUrl = new SongUrl();
//
//        if (responseObj.getClass().equals(SongUrl.class)) {
//            mSongUrl = (SongUrl) responseObj;
//            AudioHelper.startMusicService(mSongUrl.getData(),mSongs);
//
//        }
    }

    @Override
    public void onFailure(Object responseObj) {

    }
}