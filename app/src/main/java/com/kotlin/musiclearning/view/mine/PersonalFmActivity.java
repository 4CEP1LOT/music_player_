package com.kotlin.musiclearning.view.mine;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gcssloop.widget.ArcSeekBar;
import com.kotlin.lib_audio.MediaPlayer.core.AudioController;
import com.kotlin.lib_audio.MediaPlayer.core.CustomMediaPlayer;
import com.kotlin.lib_audio.MediaPlayer.event.AudioProgressEvent;
import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongViewModel;
import com.kotlin.lib_audio.MediaPlayer.utils.Utils;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.event.SongEvent;
import com.kotlin.musiclearning.adapter.CardStackAdapter;
import com.kotlin.lib_base.model.audio.SongUrl;
import com.kotlin.musiclearning.event.SongDetailsEvent;
import com.kotlin.musiclearning.event.mine.MinePersonalFmEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.MinePersonalFm;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class PersonalFmActivity extends AppCompatActivity implements DisposeDataListener {
    private Context context;
    private List<SongDetails.Songs> mSongDetail;
    private List<SongUrl.Data> mSongUrl;
    private  List<SongUrl.Data> mNewSong;
    private List<MinePersonalFm.Data> mPersonalFm = new ArrayList<>();
    private ImageView mPersonalFmPlay,mPersonalFmNext,mPersonalFmLiked;
    private  CardStackView cardStackView;
    private SongViewModel songViewModel;
   private ArcSeekBar mArcSeekBar;
    private   String section_1;
    private CardStackAdapter adapter;
    private TextView mSongName, mSongArtist,mCurrentTimeView,mTotalTimeView;
    public   int index = 0,overflowcounter = 0;
    private boolean mActivityStateFalse = false;
    private MutableLiveData<SongUrl.Data> mData = new MutableLiveData<>();
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private boolean isSeekbarChaning = false;
    private ObjectAnimator animator = ObjectAnimator.ofFloat(cardStackView, View.ROTATION.getName(), 360);


    public PersonalFmActivity() {
    }

    public int getIndex() {
        return index;
    }

    public void PersonalFmActivity(List<MinePersonalFm.Data> mPersonalFm) {
        this.mPersonalFm = mPersonalFm;

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
      setContentView( R.layout.activity_personal_fm);
        onSongUrl(new SongEvent());
        onSongDetails(new SongDetailsEvent());
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mPersonalFm =  (List<MinePersonalFm.Data>) getIntent().getSerializableExtra("bundle");
        onWindowFocusChanged(true);
        initView(context);
        initData(mSongUrl,mSongDetail);


    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSongUrl(SongEvent event){
        try {
            mSongUrl = SongManager.getInstance().getmSongUrl().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSongDetails(SongDetailsEvent event){
        try {
            mSongDetail = SongManager.getInstance().getmSongDetails().getSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPersonal(MinePersonalFmEvent event){
        mPersonalFm = HomeManager.getInstance().getmMinePersonalFm().getData();

    }


    private  void initView(Context context){
         cardStackView = findViewById(R.id.card_stack_view);
        CardStackLayoutManager layoutManager = new CardStackLayoutManager(getApplicationContext());
         adapter = new CardStackAdapter(mPersonalFm,mSongUrl);
        cardStackView.setAdapter(adapter);
        mArcSeekBar = findViewById(R.id.arc_seek_bar);
        mCurrentTimeView = findViewById(R.id.current_time);
        cardStackView.setLayoutManager(layoutManager);
        mSongName = findViewById(R.id.personal_name);
        mSongArtist= findViewById(R.id.personal_artist);
        mPersonalFmPlay = findViewById(R.id.personal_fm_play);
        mPersonalFmNext = findViewById(R.id.personal_fm_next);
        mPersonalFmLiked = findViewById(R.id.personal_fm_like);
        mTotalTimeView = findViewById(R.id.total_time);
        animator = ObjectAnimator.ofFloat(cardStackView, View.ROTATION.getName(), 360);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());                             //动画拦截器
        animator.setRepeatCount(-1);                                                    //-1为无限循环
        animator.start();

    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initData(List<SongUrl.Data> mSongUrl,List<SongDetails.Songs> mSongDetail) {
        mPersonalFmPlay.setImageResource(R.drawable.ic_baseline_pause_24);
        Log.d("index", String.valueOf(index));
        mSongArtist.setText(mPersonalFm.get(0).getArtists().get(0).getName());
        mSongName.setText(mPersonalFm.get(0).getName());
        onAudioProgessEvent(new AudioProgressEvent(CustomMediaPlayer.Status.STARTED,mMediaPlayer.getCurrentPosition(),mPersonalFm.get(index).getDuration()));



        mPersonalFmPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (AudioController.getInstance().isStartState()) {
                    AudioController.getInstance().pause();
                    animator.pause();
                    mPersonalFmPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);

                } else {
                    AudioController.getInstance().resume();
                    animator.resume();
                    mPersonalFmPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                }
            }


        });
        mPersonalFmNext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    index = index + 1;
                    RequestCenter.SongUrl(PersonalFmActivity.this, mPersonalFm.get(index).getId());
                    RequestCenter.SongDetails(PersonalFmActivity.this, mPersonalFm.get(index).getId());
                    Log.d("OnTouch", String.valueOf(index));
                    Glide.with(getApplicationContext()).asBitmap().load(mPersonalFm.get(index).getAlbum().getPicUrl()).apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10))).into(new SimpleTarget<Bitmap>(){

                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Drawable drawable = new BitmapDrawable(getApplication().getResources(), resource);
                            RelativeLayout mRelativeLayout = findViewById(R.id.card_relative);
                            mRelativeLayout.setBackground(drawable);

                        }


                    });

                }
                return false;
            }

        });

        Glide.with(getApplicationContext()).asBitmap().load(mPersonalFm.get(index).getAlbum().getPicUrl()).apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10))).into(new SimpleTarget<Bitmap>(){

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Drawable drawable = new BitmapDrawable(getApplication().getResources(), resource);
                RelativeLayout mRelativeLayout = findViewById(R.id.card_relative);
                mRelativeLayout.setBackground(drawable);

            }


        });



     //更新时间

        mPersonalFmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    cardStackView.swipe();
                    mPersonalFmPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                    mSongArtist.setText(mPersonalFm.get(index).getArtists().get(0).getName());
                    mSongName.setText(mPersonalFm.get(index).getName());
                    AudioController.getInstance().next();
                    Log.d("OnTouchNext", String.valueOf(AudioController.getInstance().getAudioQueueIndex()));
                if (index==mPersonalFm.size()-1) {
                    RequestCenter.MinePersonalizedFm(PersonalFmActivity.this);
                    Toast.makeText(v.getContext(), "已经到达最后了，请稍后再试", Toast.LENGTH_SHORT).show();
                    mPersonalFmNext.setEnabled(false);
                }else{
                        mPersonalFmNext.setEnabled(true);
                    }

                }

        });

//        mStartTimeView.setText(Utils.formatTime(currentTime));
//        mTotalTimeView.setText(Utils.formatTime(totalTime));



    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioProgessEvent(AudioProgressEvent event) {
        int totalTime = event.maxLength;
        int currentTime = event.progress;
        //更新时间
        mCurrentTimeView.setText(Utils.formatTime(currentTime));
        mTotalTimeView.setText(Utils.formatTime(totalTime));
        mArcSeekBar.setProgress(currentTime);
        mArcSeekBar.setMaxValue(totalTime);
        mArcSeekBar.setOnProgressChangeListener(new ArcSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(ArcSeekBar seekBar, int progress, boolean isUser) {

            }

            @Override
            public void onStartTrackingTouch(ArcSeekBar seekBar) {
                isSeekbarChaning = true;
            }

            @Override
            public void onStopTrackingTouch(ArcSeekBar seekBar) {
                isSeekbarChaning = false;
                mMediaPlayer.seekTo(seekBar.getProgress());//在当前位置播放
            }
        });

    }



        @Override
    public void onSuccess(Object responseObj) {
            SongUrl mNewSong = new SongUrl();
            MinePersonalFm minePersonalFm = new MinePersonalFm();
            SongDetails mSongDetail = new SongDetails();
            if (responseObj.getClass()==mNewSong.getClass()) {

                mNewSong = (SongUrl) responseObj;
                AudioController.getInstance().addAudio(mNewSong.getData().get(0),mSongDetail.getSongs().get(0));
            }else  if (responseObj.getClass()==mSongDetail.getClass()) {
                mSongDetail = (SongDetails)responseObj;
                SongManager.getInstance().setmSongDetails(mSongDetail);
                EventBus.getDefault().post(new SongDetailsEvent());

            }

            else if(responseObj.getClass()==minePersonalFm.getClass()&&minePersonalFm.getData().get(0).getId()!=mPersonalFm.get(0).getId()) {

                    minePersonalFm = (MinePersonalFm) responseObj;
                    mPersonalFm.addAll(minePersonalFm.getData());
                    adapter.notifyItemInserted(mPersonalFm.size()-1);
                    adapter.notifyItemRangeChanged(1, mPersonalFm.size() + 1);

            }


        }



    @Override
    public void onFailure(Object responseObj) {

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
}



