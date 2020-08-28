package com.kotlin.lib_search.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kotlin.lib_base.model.search.SearchAlbum;
import com.kotlin.lib_base.model.search.SearchAll;
import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchPlayList;
import com.kotlin.lib_base.model.search.SearchSinger;
import com.kotlin.lib_base.model.search.SearchSong;
import com.kotlin.lib_base.model.search.SearchVideo;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.adapter.SearchAlbumAdapter;
import com.kotlin.lib_search.adapter.SearchPlayListAdapter;
import com.kotlin.lib_search.adapter.SearchAllSongPicAdapter;
import com.kotlin.lib_search.adapter.SearchSingerAdapter;
import com.kotlin.lib_search.adapter.SearchSongAdapter;
import com.kotlin.lib_search.adapter.SearchVideoAdapter;
import com.kotlin.lib_search.event.SearchAlbumEvent;
import com.kotlin.lib_search.event.SearchAllEvent;
import com.kotlin.lib_search.event.SearchPlayListEvent;
import com.kotlin.lib_search.event.SearchSingerEvent;
import com.kotlin.lib_search.event.SearchSongEvent;
import com.kotlin.lib_search.event.SearchVideoEvent;

import com.kotlin.lib_search.view.LazyFragment;
import com.kotlin.lib_search.view.ResultSearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class All_Fragment extends LazyFragment {
    private RecyclerView SongPicRecyclerView;
    private RecyclerView PlayListRecycler;
    private RecyclerView VideoRecyclerView;
    private RecyclerView SongRecyclerView;
    private RecyclerView AlbumRecycler;
    private RecyclerView SingerRecycler;
    private All_Fragment mFragment;

    private SearchPlayListAdapter mPlayListAdapter;
    private SearchAllSongPicAdapter mSongPicAdapter;
    private SearchSongAdapter mSongAdapter;
    private SearchVideoAdapter mVideoAdapter;

    private ImageView allVideoView;
    private TextView allVideoName;
    private TextView allVideoCreator;
    private TextView allVideoTime;
    private int itemCount = 4;
    private SearchAll mSearchAllList;
    private List<SearchAlbum.Result.Albums> mAlbumList;
    private List<SearchSinger.Result.Artists> mSingerList;
    private List<SearchSong.Result.Songs> mSongList;
    private List<SearchVideo.Result.Videos> mVideoList;
    private List<SearchPlayList.Result.Playlists> mPlayList;
    private SearchAlbumAdapter mAlbumAdapter;
    private SearchSingerAdapter mSingerAdapter;


    public All_Fragment() {
        // Required empty public constructor
    }

    public static All_Fragment newInstance() {

        Bundle args = new Bundle();

        All_Fragment fragment = new All_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        onSearchAllEvent(new SearchAllEvent());



    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSearchAllEvent( SearchAllEvent event) {
         mSearchAllList = SearchManager.getInstance().getmSearchAll();
        Log.d("SearchSongAdapter","给点力啊老哥");
    }













    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return super.onCreateView(inflater,container,savedInstanceState);


    }



    @Override
    protected int getContentViewId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        VideoRecyclerView = view.findViewById(R.id.all_video_recycler);
        SongPicRecyclerView = view.findViewById(R.id.all_song_pic_recycler);
        SongRecyclerView = view.findViewById(R.id.all_song_recycler);
        PlayListRecycler = view.findViewById(R.id.all_playlist_recycler);
        allVideoView = view.findViewById(R.id.video_image);
        allVideoName = view.findViewById(R.id.all_video_name);
        allVideoCreator = view.findViewById(R.id.all_video_creator);
        allVideoTime = view.findViewById(R.id.all_video_time);
        Glide.with(view).load(mSearchAllList.getResult().getVideo().getVideos().get(0).getCoverUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25, 3)))
                .into(allVideoView);
        allVideoName.setText(mSearchAllList.getResult().getVideo().getVideos().get(0).getTitle());
        allVideoCreator.setText(mSearchAllList.getResult().getVideo().getVideos().get(0).getCreator().get(0).getUserName());
        allVideoTime.setText((timeParse(mSearchAllList.getResult().getVideo().getVideos().get(0).getDurationms())));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        VideoRecyclerView.setLayoutManager(linearLayoutManager);



        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        PlayListRecycler.setLayoutManager(linearLayoutManager2);



        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        SongRecyclerView.setLayoutManager(linearLayoutManager3);



        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        SongPicRecyclerView.setLayoutManager(linearLayoutManager4);

    }


    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        super.initData();

        showProgressDialog("正在加载中");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mVideoAdapter = new SearchVideoAdapter();
                        VideoRecyclerView.setAdapter(mVideoAdapter);
                        mPlayListAdapter = new SearchPlayListAdapter();
                        PlayListRecycler.setAdapter(mPlayListAdapter);
                        mSongAdapter = new SearchSongAdapter();
                        SongRecyclerView.setAdapter(mSongAdapter);
                        mSongPicAdapter = new SearchAllSongPicAdapter(mSearchAllList);
                        SongPicRecyclerView.setAdapter(mSongPicAdapter);
                        hideProgressDialog();
                    }
                });



    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public static String timeParse(long duration) {
        String time = "" ;
        long minute = duration / 60000 ;
        long seconds = duration % 60000 ;
        long second = Math.round((float)seconds/1000) ;
        if( minute < 10 ){
            time += "0" ;
        }
        time += minute+":" ;
        if( second < 10 ){
            time += "0" ;
        }
        time += second ;
        return time ;
    }

    @Override
    protected void hideProgressDialog() {
        super.hideProgressDialog();
    }
}