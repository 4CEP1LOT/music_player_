package com.kotlin.lib_search.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotlin.lib_base.model.search.SearchVideo;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.adapter.SearchAlbumAdapter;
import com.kotlin.lib_search.adapter.SearchSongAdapter;
import com.kotlin.lib_search.adapter.SearchVideoAdapter;
import com.kotlin.lib_search.event.SearchVideoEvent;
import com.kotlin.lib_search.view.LazyFragment;
import com.kotlin.lib_search.view.ResultSearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Video_Player_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Video_Player_Fragment extends LazyFragment {
        private RecyclerView mRecycler;
        private SearchVideoAdapter mAdapter;
    private List<SearchVideo.Result.Videos> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public static Video_Player_Fragment newInstance() {

        Bundle args = new Bundle();

        Video_Player_Fragment fragment = new Video_Player_Fragment();
        fragment.setArguments(args);
        return fragment;
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


        mRecycler = view.findViewById(R.id.video_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    protected void hideProgressDialog() {
        super.hideProgressDialog();
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
                        mAdapter = new SearchVideoAdapter();
                        mRecycler.setAdapter(mAdapter);
                        hideProgressDialog();
                    }
                });


        super.initData();
    }



    @Override
    protected int getContentViewId() {
        return R.layout.fragment_video__player_;
    }



}