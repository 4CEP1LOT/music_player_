package com.kotlin.lib_search.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchSinger;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.adapter.SearchSingerAdapter;
import com.kotlin.lib_search.adapter.SearchSongAdapter;
import com.kotlin.lib_search.adapter.SearchVideoAdapter;
import com.kotlin.lib_search.event.SearchSingerEvent;
import com.kotlin.lib_search.view.LazyFragment;
import com.kotlin.lib_search.view.ResultSearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Singer_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Singer_Fragment extends LazyFragment {


    private SearchSingerAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public Singer_Fragment() {
    }

    private List<SearchSinger.Result.Artists> mList = new ArrayList<>();

    public static Singer_Fragment newInstance() {

        Bundle args = new Bundle();

        Singer_Fragment fragment = new Singer_Fragment();
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
        initData();
        return super.onCreateView(inflater,container,savedInstanceState);
    }


    @Override
    protected void initView(View view) {
        super.initView(view);


         mRecyclerView = view.findViewById(R.id.singer_recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(ResultSearchActivity.getContext()));


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
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
                        mAdapter = new SearchSingerAdapter();
                        mRecyclerView.setAdapter(mAdapter);
                        hideProgressDialog();
                    }
                });


    }







    @Override
    protected int getContentViewId() {
        return R.layout.fragment_singer;
    }


}