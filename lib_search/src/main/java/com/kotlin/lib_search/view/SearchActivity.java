package com.kotlin.lib_search.view;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_base.model.search.SearchAlbum;
import com.kotlin.lib_base.model.search.SearchAll;
import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchPlayList;
import com.kotlin.lib_base.model.search.SearchSinger;
import com.kotlin.lib_base.model.search.SearchSong;
import com.kotlin.lib_base.model.search.SearchVideo;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.adapter.SearchTrendAdapter;
import com.kotlin.lib_search.event.SearchAllEvent;
import com.kotlin.lib_search.event.SearchPlayListEvent;
import com.kotlin.lib_search.event.SearchSingerEvent;
import com.kotlin.lib_search.event.SearchSongEvent;
import com.kotlin.lib_search.event.SearchVideoEvent;

import com.kotlin.lib_search.event.SearchAlbumEvent;
import com.kotlin.lib_common_ui.base.BaseActivity;


import org.greenrobot.eventbus.EventBus;


public class SearchActivity extends BaseActivity implements DisposeDataListener {
    private static Context mContext;
    private SearchView mSearchView;
    public static String mNewText;
    public RecyclerView mRecyclerView;
    private ResultPagerAdapter mAdapter;
    Fragment fragment;
    private SearchSong mSearchSongs;


    //    public static void start(Context context){
//        Intent intent = new Intent(context,SearchActivity.class);
//        context.startActivity(intent);
//    }
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setStatusBarTransparent();
        initView();


        mSearchView = findViewById(R.id.search_activity);


        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestCenter.searchPlayList(SearchActivity.this,query);
                RequestCenter.searchSong(SearchActivity.this,query);
                RequestCenter.searchAll(SearchActivity.this,query);
                RequestCenter.searchAlbum(SearchActivity.this,query);
                RequestCenter.searchSinger(SearchActivity.this,query);
                Intent intent = new Intent(SearchActivity.this, ResultSearchActivity.class);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                if (!TextUtils.isEmpty(newText))
//                    mNewText = newText;
//
//                RequestCenter.searchPlayList(SearchActivity.this);
//
//                RequestCenter.searchSong(SearchActivity.this);
//                RequestCenter.searchAll(SearchActivity.this);
//                RequestCenter.searchAlbum(SearchActivity.this);
//                RequestCenter.searchVideo(SearchActivity.this);
//
//                RequestCenter.searchSinger(SearchActivity.this);
//





                return false;
            }

        });


    }

    private void initView() {

        mRecyclerView = findViewById(R.id.searchRecyler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        SearchTrendAdapter mAdapter = new SearchTrendAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSuccess(Object responseObj) {
        SearchAlbum mSearchAlbum = new SearchAlbum();
        SearchSong mSearchSong = new SearchSong();
        SearchAll mSearchAll = new SearchAll();
        SearchVideo mSearchVideo = new SearchVideo();
        SearchSinger mSearchSinger = new SearchSinger();
        SearchPlayList mPlayList = new SearchPlayList();

        if (responseObj.getClass() == mSearchAlbum.getClass()) {
            mSearchAlbum = (SearchAlbum) responseObj;
            SearchManager.getInstance().saveSearchAlbum(mSearchAlbum);
            EventBus.getDefault().post(new SearchAlbumEvent());

        } else if (responseObj.getClass() == mSearchSong.getClass()) {
            mSearchSong = (SearchSong) responseObj;
            SearchManager.getInstance().setSearchSong(mSearchSong);
            EventBus.getDefault().post(new SearchSongEvent());

        } else if (responseObj.getClass() == mSearchAll.getClass()) {
            mSearchAll = (SearchAll) responseObj;
            SearchManager.getInstance().setSearchAll(mSearchAll);
            EventBus.getDefault().post(new SearchAllEvent());

        } else if (responseObj.getClass() == mSearchVideo.getClass()) {
            mSearchVideo = (SearchVideo) responseObj;
            SearchManager.getInstance().setSearchVideo(mSearchVideo);
            EventBus.getDefault().post(new SearchVideoEvent());

        } else if (responseObj.getClass() == mSearchSinger.getClass()) {
            mSearchSinger = (SearchSinger) responseObj;
            SearchManager.getInstance().setSearchSinger(mSearchSinger);
            EventBus.getDefault().post(new SearchSingerEvent());


        } else if (responseObj.getClass() == mPlayList.getClass()) {
            mPlayList = (SearchPlayList) responseObj;
            SearchManager.getInstance().setSearchPlayList(mPlayList);
            EventBus.getDefault().post(new SearchPlayListEvent());


        }


    }



    @Override
    public void onFailure(Object responseObj) {

    }







}