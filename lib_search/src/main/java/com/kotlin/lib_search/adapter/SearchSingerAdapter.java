package com.kotlin.lib_search.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchSinger;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.event.SearchSingerEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SearchSingerAdapter extends RecyclerView.Adapter<SearchSingerAdapter.MyViewHolder> {
    private  List<SearchSinger.Result.Artists> mQueue;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onSearchEvent(new SearchSingerEvent());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSearchEvent( SearchSingerEvent event) {
        mQueue = SearchManager.getInstance().getSearchSinger().getResult().getArtists();
        Log.d("SearchSongAdapter","给点力啊老哥");

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_singer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SearchSinger.Result.Artists artists = mQueue.get(position);
            holder.searchSingerName.setText(artists.getName());
        ImageLoaderManager imageLoaderManager  = new ImageLoaderManager();
        imageLoaderManager.displayImageForCircleView(holder.searchSingerPic,artists.getPicUrl());


    }



    @Override
    public int getItemCount() {


        return mQueue.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView searchSingerName;
        ImageView searchSingerPic;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            searchSingerName = itemView.findViewById(R.id.singer_name);
            searchSingerPic = itemView.findViewById(R.id.singer_img);



        }
    }




}
