package com.kotlin.musiclearning.view.mine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kotlin.lib_audio.MediaPlayer.view.FullscreenActivity;
import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongUrl;
import com.kotlin.lib_common_ui.base.BaseRecyclerView;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.musiclearning.R;
import com.kotlin.musiclearning.Utils.RequestSuccessCollection;
import com.kotlin.musiclearning.Utils.Rxjava;
import com.kotlin.musiclearning.adapter.LikedAdapter;
import com.kotlin.musiclearning.event.SongDetailsEvent;
import com.kotlin.lib_base.model.MineLikedList;
import com.kotlin.lib_base.model.SongDetailPlayListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class LikedActivity extends AppCompatActivity implements DisposeDataListener {

    private static String DATA_AUDIOS = "AUDIOS";
    private static String DATA_DETAILS = "DETAILS";
    private static String ACTION_START = "ACTION_START";
    private ImageView coverImage,toolbarBackPress;
    private TextView toolbarText;
    private Context context;
    private  RelativeLayout mRelativeLayout ;
    private  String  mPicUrl;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private LinearLayout mLinearLayout;
    private int position;



    private MineLikedList.Playlist mLikedPlayList;
     private List<SongDetails.Songs> mList = new ArrayList<>();
    private List<SongUrl.Data> mUrl;

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSongDetail(SongDetailPlayListEvent event){

        mList = SongManager.getInstance().getmSongDetails().getSongs();

        EventBus.getDefault().post( new SongDetailsEvent());
    }

//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onSongUrl(SongEvent event){
//        try {
//            mUrl = SongManager.getInstance().getmSongUrl().getData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_play_list);
        View decorView = getWindow().getDecorView();
        onSongDetail(new SongDetailPlayListEvent() );

        // Hide the status bar.
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏

        mLikedPlayList = (MineLikedList.Playlist) getIntent().getSerializableExtra("bundle");
        mPicUrl = (String) getIntent().getSerializableExtra("String");
//        likedBg = findViewById(R.id.liked_bg);
        coverImage = findViewById(R.id.liked_avatr);
        collapsingToolbarLayout =findViewById(R.id.toolbar_layout);
        toolbarText = findViewById(R.id.toolbar_text);

        mLinearLayout = findViewById(R.id.liked_tool_linear);
        appBarLayout =findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    //  Collapsed
                    toolbarText.setText("我喜欢的音乐");
                    mLinearLayout.setVisibility(View.INVISIBLE);

                }else{
                    toolbarText.setText("歌单");
                    mLinearLayout.setVisibility(View.VISIBLE);


                }
            }
        });
        toolbar =findViewById(R.id.toolbar);
        pickColor(coverImage,appBarLayout);
        initView();
    }


    public void initView(){
        BaseRecyclerView recyclerView = findViewById(R.id.liked_recycler);

        recyclerView.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View item, int adapterPosition, RecyclerView.Adapter adapter) {
                position = adapterPosition;
                RequestCenter.SongUrl(LikedActivity.this,mList.get(adapterPosition).getId());
                Toast.makeText(getApplicationContext(),String.valueOf(mList.get(adapterPosition).getId()), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LikedActivity.this, FullscreenActivity.class);
                startActivity(intent);


            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        LikedAdapter adapter = new LikedAdapter(mList);
        recyclerView.setAdapter(adapter);

//        Glide.with(getApplicationContext()).load(mLikedPlayList.getTracks().get(0).getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0))).into(coverImage);
        TextView mUserID =findViewById(R.id.liked_userId);
        ImageView mAvatar = findViewById(R.id.liked_userAvatar);
        mUserID.setText(mLikedPlayList.getCreator().getNickname());
        ImageLoaderManager.getInstance().displayImageForCircleView(mAvatar,mLikedPlayList
        .getCreator().getAvatarUrl());
        toolbarBackPress = findViewById(R.id.toolbar_backpress);
        toolbarBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

    }



    private void pickColor(final ImageView imageView, final AppBarLayout relativeLayout) {
        Glide.with(this).asBitmap()
                .load(mPicUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                if (palette == null) return;

                                if (palette.getDarkVibrantColor(Color.TRANSPARENT) != Color.TRANSPARENT) {
                                    createLinearGradientBitmap(palette.getDarkVibrantColor(Color.TRANSPARENT), palette.getLightVibrantColor(Color.TRANSPARENT),relativeLayout);
                                } else if (palette.getDarkMutedColor(Color.TRANSPARENT) != Color.TRANSPARENT) {
                                    createLinearGradientBitmap(palette.getDarkMutedColor(Color.TRANSPARENT), palette.getLightMutedColor(Color.TRANSPARENT),relativeLayout);
                                } else {
                                    createLinearGradientBitmap(palette.getVibrantColor(Color.TRANSPARENT), palette.getMutedColor(Color.TRANSPARENT),relativeLayout);
                                }
                            }
                        });

                           imageView .setImageBitmap(handleBimap(resource));

                        }



                });
    }


    //创建线性渐变背景色
    private void createLinearGradientBitmap(int darkColor, int color,AppBarLayout relativeLayout) {
        int[] bgColors = new int[2];
        bgColors[0] = darkColor;
        bgColors[1] = color;

        Bitmap bgBitmap = Bitmap.createBitmap(relativeLayout.getWidth(), relativeLayout.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        canvas.setBitmap(bgBitmap);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        LinearGradient gradient = new LinearGradient(0, 0, 0, bgBitmap.getHeight(), bgColors[0], bgColors[1], Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0, 0, bgBitmap.getWidth(), bgBitmap.getHeight());
        canvas.drawRoundRect(rectF, 20, 20, paint);
        canvas.drawRect(rectF, paint);
        relativeLayout.setBackground(new BitmapDrawable(bgBitmap));
        toolbar.setBackground(new BitmapDrawable(bgBitmap));
        appBarLayout.setBackground(new BitmapDrawable(bgBitmap));


    }


    //修改透明度
    private Bitmap getImageToChange(Bitmap mBitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_4444);
        int mWidth = mBitmap.getWidth();
        int mHeight = mBitmap.getHeight();
        for (int i = 0; i < mHeight; i++) {
            for (int j = 0; j < mWidth; j++) {
                int color = mBitmap.getPixel(j, i);
                int g = Color.green(color);
                int r = Color.red(color);
                int b = Color.blue(color);
                int a = Color.alpha(color);
                //从中间部分开始透明渐变
                float index = i * 1.0f / mHeight;
                if (index > 0.5f) {
                    a = 255 - (int) (i / (mHeight / 2f) * 255);
                }
                color = Color.argb(a, r, g, b);
                createBitmap.setPixel(j, i, color);
            }
        }

        return createBitmap;
    }

    /**
     * 通过位移运算来做透渐变，相比之前的方法提高90倍左右
     * @param bitmap
     * @return
     */
    private Bitmap handleBimap(Bitmap bitmap) {
        //透明渐变
        int[] argb = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(argb, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        //循环开始的下标，设置从什么时候开始改变
        int start = argb.length / 2;
        int end=argb.length;

//        int mid = argb.length;
//        int row = ((mid - start) / bitmap.getHeight()) + 2;


        int width = bitmap.getWidth();
        for (int i = 0; i < bitmap.getHeight()/2+1; i++) {
            for (int j = 0; j < width; j++) {
                int index = start - width + i * width + j;
                if (argb[index] != 0) {
                    argb[index] = ((int) ((1-i/(bitmap.getHeight()/2f)) * 255) << 24) | (argb[index] & 0x00FFFFFF);
                }
            }
        }
//        for (int i = mid; i < argb.length; i++) {
//            argb[i] = (argb[i] & 0x00FFFFFF);
//        }

        return Bitmap.createBitmap(argb, bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

    }


    @Override
    public void onSuccess(Object responseObj) {
        SongUrl mSongUrl = new SongUrl();

        Rxjava rxjava = new Rxjava();
        rxjava.initRxjava(responseObj);
//        if (responseObj.getClass().equals(SongUrl.class)) {
//            mSongUrl = (SongUrl) responseObj;
//
//            AudioHelper.init(this);
//            Intent intent = new Intent(this, MusicService.class);
//            intent.setAction(ACTION_START);
//            //将mList的数据传进来
//            intent.putExtra(DATA_AUDIOS, (Serializable) mSongUrl.getData().get(0));
//            intent.putExtra(DATA_DETAILS, (Serializable) mList.get(position));
//            startService(intent);
//            AudioController.getInstance().setmQueue(mSongUrl.getData(),mList,0);
//            AudioController.getInstance().play();
//            AllRequest allRequest = new AllRequest(mList.get(0),mSongUrl.getData().get(0));
        }







    @Override
    public void onFailure(Object responseObj) {

    }
}








