package com.kotlin.musiclearning.view.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kotlin.lib_audio.MediaPlayer.event.MineLikedListEvent;
import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.musiclearning.R;
import com.kotlin.musiclearning.Utils.Rxjava;
import com.kotlin.musiclearning.adapter.MineCollectedPlayListAdapter;
import com.kotlin.musiclearning.adapter.MineCreatedPlayListAdapter;
import com.kotlin.musiclearning.adapter.MinePersonalListAdapter;

import com.kotlin.musiclearning.event.discovery.DSPersonalizedPlayListEvent;
import com.kotlin.musiclearning.event.mine.MineDetailUserEvent;
import com.kotlin.musiclearning.event.mine.MinePersonalFmEvent;
import com.kotlin.musiclearning.event.mine.MinePlayListEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.MineDetailUser;
import com.kotlin.lib_base.model.MineLikedList;
import com.kotlin.lib_base.model.discoverymodel.DSPersonalizedPlayList;
import com.kotlin.lib_base.model.discoverymodel.MinePersonalFm;
import com.kotlin.lib_base.model.discoverymodel.MinePlayList;
import com.kotlin.lib_base.model.user.UserManager;
import com.kotlin.musiclearning.view.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment implements DisposeDataListener {
    private ImageView mLoginButton, mMineAvatar,mInsertButton;
    private TextView mMineCreateTitle, mMineUserId, mMineRecommendTitle,mMineCollectedTitle;
    public RecyclerView mCreatedPlayListRecycler,mCollectedPlayListRecycler,mPersonalFmRecycler;
    private RelativeLayout mRelativeLayout,mLocalRelativeLayout,mMineLayout,mFmRelativeLayout,mPlayList,mLikedList;
    private LinearLayout mLoginLayout;

    private ArrayList<MinePersonalFm.Data> mPersonalFm = new ArrayList<>();

    private List<MinePlayList.Playlist> mCreatedPlayList = new ArrayList<>();

    private List<DSPersonalizedPlayList.Result> mPersonalList = new ArrayList<>();

    private MineDetailUser mUser = new MineDetailUser();
    private ImageView mPersonalFmPic;
    private MineLikedList.Playlist mLikedPlayList;


    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        personalFm(new MinePersonalFmEvent());
        onMinePlayList(new MinePlayListEvent());
        onPersonalList(new DSPersonalizedPlayListEvent());

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void personalFm(MinePersonalFmEvent event) {
        try {
            mPersonalFm = HomeManager.getInstance().getmMinePersonalFm().getData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMinePlayList(MinePlayListEvent event) {
        try {
            mCreatedPlayList = HomeManager.getInstance().getmMinePlayList().getPlaylist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onDetailedUser(MineDetailUserEvent event) {
        mUser = HomeManager.getInstance().getmDetailUser();

        if (UserManager.getInstance().hasLogined()) {
            initData(requireView());

        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMineLikedList(MineLikedListEvent event) {

        try {
            mLikedPlayList = HomeManager.getInstance().getmMineLikedList().getPlaylist();


            List<String> mList = new ArrayList<>();
            for (int i = 0;i<mLikedPlayList.getTrackIds().size();i++) {
                mList.add(i,String.valueOf(mLikedPlayList.getTrackIds().get(i).getId()));

            }
            RequestCenter.SongPlayListDetails(this,mList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPersonalList(DSPersonalizedPlayListEvent event) {
        try {
            mPersonalList = HomeManager.getInstance().getPersonalizedPlayList().getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            mPersonalFm = (ArrayList<MinePersonalFm.Data>) savedInstanceState.getSerializable("mPersonalFm");
            mCreatedPlayList = (List<MinePlayList.Playlist>) savedInstanceState.getSerializable("mCreatedPlayList");
            mPersonalList = (List<DSPersonalizedPlayList.Result>) savedInstanceState.getSerializable("mPersonalList");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine,container,false);
        // Inflate the layout for this fragment
        initView(rootView);
        onMineLikedList(new MineLikedListEvent());


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(intent, 1);



            }
        });


        return rootView;
    }




    @Override
    public void onResume() {
        super.onResume();
        onDetailedUser(new MineDetailUserEvent());

    }




    protected void initView(final View view) {
        Glide.with(view.getContext()).asBitmap().load(mPersonalFm.get(0).getAlbum().getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(40, 0))).into(new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Drawable drawable = new BitmapDrawable(view.getResources(), resource);
                mFmRelativeLayout = view.findViewById(R.id.mine_user_fm_relative);
                mFmRelativeLayout.setBackground(drawable);


            }


        });


        mCreatedPlayListRecycler = view.findViewById(R.id.mine_created_recycler);
        mCollectedPlayListRecycler = view.findViewById(R.id.mine_collected_recycler);
        mPersonalFmRecycler = view.findViewById(R.id.mine_star_recycler);
        MineCreatedPlayListAdapter mineCreatedPlayListAdapter = new MineCreatedPlayListAdapter();
        MinePersonalListAdapter mRecommendAdapter = new MinePersonalListAdapter(mPersonalList);
        MineCollectedPlayListAdapter mineCollectedPlayListAdapter = new MineCollectedPlayListAdapter();
        mCreatedPlayListRecycler.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        mCollectedPlayListRecycler.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        mPersonalFmRecycler.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        mCreatedPlayListRecycler.setAdapter(mineCreatedPlayListAdapter);
        mCollectedPlayListRecycler.setAdapter(mineCollectedPlayListAdapter);
        mPersonalFmRecycler.setAdapter(mRecommendAdapter);
        mLoginLayout = view.findViewById(R.id.mine_login_linear);
        mMineCreateTitle = view.findViewById(R.id.mine_playlist);
        mMineRecommendTitle = view.findViewById(R.id.mine_star_title);
        mRelativeLayout = view.findViewById(R.id.mine_user_layout);
        mLocalRelativeLayout = view.findViewById(R.id.mine_local_music_layout);
        mLoginButton = view.findViewById(R.id.mine_login_button);
        mMineLayout = view.findViewById(R.id.mine_id_layout);
        mMineAvatar = view.findViewById(R.id.mine_avatar);
        mMineUserId = view.findViewById(R.id.mine_user_id);
        mInsertButton = view.findViewById(R.id.mine_insert_playlist);
        mMineCollectedTitle = view.findViewById(R.id.mine_collected_title);
        mPlayList = view.findViewById(R.id.mine_playlist_layout);
        mLikedList = view.findViewById(R.id.mine_user_fav_relative);
        mPersonalFmPic =view.findViewById(R.id.mine_recycler_item);


//


    }




    public void initData(final View view) {
        mPersonalFmRecycler.setVisibility(GONE);
        mPlayList.setVisibility(View.VISIBLE);
        mMineCollectedTitle.setTextColor(Color.GRAY);
        mMineCollectedTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMineCollectedTitle.setTextColor(Color.BLACK);
                mMineCreateTitle.setTextColor(Color.GRAY);
                mCollectedPlayListRecycler.setVisibility(View.VISIBLE);
                mCreatedPlayListRecycler.setVisibility(GONE);
            }
        });
        mMineCreateTitle.setTextColor(Color.BLACK);
        mMineCreateTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMineCollectedTitle.setTextColor(Color.GRAY);
                mMineCreateTitle.setTextColor(Color.BLACK);
                mCollectedPlayListRecycler.setVisibility(GONE);
                mCreatedPlayListRecycler.setVisibility(View.VISIBLE);
            }
        });

        mPersonalFmPic.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    RequestCenter.SongUrl(MineFragment.this, mPersonalFm.get(0).getId());
                    RequestCenter.SongDetails(MineFragment.this,mPersonalFm.get(0).getId());
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(view.getContext(), PersonalFmActivity.class);
                    intent.putExtra("bundle",mPersonalFm);
                    startActivity(intent);
                }
                return false;
                }
            });








        mCollectedPlayListRecycler.setVisibility(GONE);
        mLocalRelativeLayout.setVisibility(View.VISIBLE);
        mLoginLayout.setVisibility(View.INVISIBLE);
        mMineCreateTitle.setVisibility(View.VISIBLE);
        mCreatedPlayListRecycler.setVisibility(View.VISIBLE);
        mMineRecommendTitle.setVisibility(GONE);
        mCollectedPlayListRecycler.setVisibility(GONE);
        mMineLayout.setVisibility(View.VISIBLE);
        mLoginButton.setVisibility(GONE);
        mInsertButton.setVisibility(GONE);
        mLoginButton.setVisibility(GONE);

        Glide.with(view.getContext()).load(mUser.getProfile().getAvatarUrl()).circleCrop().into(mMineAvatar);

        Glide.with(view.getContext()).asBitmap().load(mUser.getProfile().getBackgroundUrl()).into(new SimpleTarget<Bitmap>(420,420) {

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Drawable drawable = new BitmapDrawable(view.getResources(), resource);
                mRelativeLayout.setBackground(drawable);

            }
        });

        Glide.with(view.getContext()).asBitmap().load(mCreatedPlayList.get(0).getCoverImgUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0))).into(new SimpleTarget<Bitmap>(420,420) {

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Drawable drawable = new BitmapDrawable(view.getResources(), resource);
                mLikedList.setBackground(drawable);

            }
        });

        mMineUserId.setText(mUser.getProfile().getNickname());

    }


    @Override
    public void onSuccess(Object responseObj) {
        Rxjava rxjava = new Rxjava();
        rxjava.initRxjava(responseObj);

        SongDetails mSongDetails = new SongDetails();
    mSongDetails = (SongDetails) responseObj;

    SongManager.getInstance().setmSongDetails(mSongDetails);

        mLikedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LikedActivity.class);
                intent.putExtra("bundle", mLikedPlayList);
                intent.putExtra("String",mCreatedPlayList.get(0).getCoverImgUrl());
                startActivity(intent);
            }
        });



    }

    @Override
    public void onFailure(Object responseObj) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mPersonalFm", mPersonalFm);
        outState.putSerializable("mCreatedPlayList", (Serializable) mCreatedPlayList);
        outState.putSerializable("mPersonalList", (Serializable) mPersonalList);
    }
}
