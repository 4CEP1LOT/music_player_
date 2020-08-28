package com.kotlin.lib_search.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchAlbum;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.adapter.SearchAlbumAdapter;
import com.kotlin.lib_search.view.LazyFragment;
import com.kotlin.lib_search.view.ResultSearchActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Album_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Album_Fragment extends LazyFragment {
    private RecyclerView mRecyclerView;
    private SearchAlbumAdapter mAdapter;
    private List<SearchAlbum.Result.Albums> mList = new ArrayList<>();

    // private List<SearchAlbum.Result.Albums> mList = new ArrayList<>();



    public Album_Fragment() {
        // Required empty public constructor
    }



    public static Album_Fragment newInstance() {
        
        Bundle args = new Bundle();
        
        Album_Fragment fragment = new Album_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.album_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ResultSearchActivity.getContext()));

    }


    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        super.initData();

        showProgressDialog("正在加载中");

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mAdapter = new SearchAlbumAdapter();
                        mRecyclerView.setAdapter(mAdapter);
                        hideProgressDialog();
                        }
                });

    }




    @Override
    protected int getContentViewId() {
        return R.layout.fragment_album;
    }


    //                SearchSong mSearchSongs = (SearchSong) responseObj;
//                SearchManager.getInstance().setSearchSong(mSearchSongs);
//                EventBus.getDefault().post(new SearchSongEvent());
//
//                SearchSinger mSearchSinger = (SearchSinger) responseObj;
//                SearchManager.getInstance().setSearchSinger(mSearchSinger);
//                EventBus.getDefault().post(new SearchSingerEvent());
//
//                SearchVideo mVideoPlayer = (SearchVideo) responseObj;
//                SearchManager.getInstance().setSearchVideo(mVideoPlayer);
//                EventBus.getDefault().post(new SearchVideoEvent());







}