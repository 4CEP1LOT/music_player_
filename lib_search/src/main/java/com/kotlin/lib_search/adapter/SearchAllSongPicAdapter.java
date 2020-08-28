package com.kotlin.lib_search.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchAll;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.event.SearchSongEvent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SearchAllSongPicAdapter extends RecyclerView.Adapter<SearchAllSongPicAdapter.MyViewHolder> {


    private SearchAll mQueue;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_song_pic,parent,false);
        return new MyViewHolder(itemView);
    }

    public SearchAllSongPicAdapter(SearchAll mQueue) {
        this.mQueue = mQueue;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SearchAll.Result.Song.Songs mSongs = mQueue.getResult().getSong().getSongs().get(position);
        holder.itemAllSongName.setText(mSongs.getName());
        holder.itemAllSingerName.setText(mSongs.getAr().get(0).getName());
        ImageLoaderManager mImageLoadManager = new ImageLoaderManager();
        mImageLoadManager.displayImageForView(holder.itemAllSongView,mSongs.getAl().getPicUrl());

    }


    @Override
    public int getItemCount() {
        return mQueue.getResult().getSong().getSongs().size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemAllSongName,itemAllSingerName;
        ImageView itemAllSongView;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            itemAllSongName = itemView.findViewById(R.id.all_song_name);
            itemAllSingerName = itemView.findViewById(R.id.all_song_singer);
            itemAllSongView = itemView.findViewById(R.id.all_song_pic);

        }
    }

}
