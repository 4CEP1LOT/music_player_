package com.kotlin.lib_search.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchSong;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.adapter.SearchSongAdapter;
import com.kotlin.lib_search.view.LazyFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SongFragment extends LazyFragment {

    private RecyclerView mRecyclerView;
    private SearchSongAdapter mAdapter;
    private List<SearchSong.Result.Songs> mList;



    public SongFragment() {
        // Required empty public constructor

    }

    public static SongFragment newInstance() {

        Bundle args = new Bundle();



        SongFragment fragment = new SongFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return super.onCreateView(inflater,container,savedInstanceState);
    }


    @Override
    protected void initView(View view) {
        super.initView(view);


        mRecyclerView = view.findViewById(R.id.song_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




    }


    public List<SearchSong.Result.Songs> getmList() {
        return mList;
    }

    @Override
    protected void initData() {
        showProgressDialog("正在加载中");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mAdapter = new SearchSongAdapter();
                        mRecyclerView.setAdapter(mAdapter);
                        hideProgressDialog();
                    }
                });


        super.initData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_song;
    }

    @Override
    protected void hideProgressDialog() {
        super.hideProgressDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}