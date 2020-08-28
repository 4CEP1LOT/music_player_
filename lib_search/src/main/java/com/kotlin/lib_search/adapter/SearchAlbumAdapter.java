package com.kotlin.lib_search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchAlbum;
import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.event.SearchAlbumEvent;

import com.kotlin.lib_search.view.ResultSearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SearchAlbumAdapter extends RecyclerView.Adapter<SearchAlbumAdapter.MyViewHolder> {
    private List<SearchAlbum.Result.Albums> mQueue = new ArrayList<>();
    private ImageLoaderManager mImageLoaderManager;
    private ImageView AlbumPics;
    private Context mContext;


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onSearchAlbumEvent(new SearchAlbumEvent());
    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSearchAlbumEvent(SearchAlbumEvent event){
        mQueue = SearchManager.getInstance().getSearchAlbum().getResult().getAlbums();


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SearchAlbum.Result.Albums album = mQueue.get(position);
        SearchAlbum.Result.Albums.Artist artists = mQueue.get(position).getArtist();
        holder.AlbumName.setText(album.getName());
        holder.AlbumSinger.setText(artists.getName());
        holder.mImageLoaderManager.displayImageForView(holder.AlbumPics,album.getPicUrl());

    }



    @Override
    public int getItemCount() {
        return mQueue.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView AlbumName,AlbumSinger;
        ImageLoaderManager mImageLoaderManager;
        ImageView AlbumPics;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            AlbumName = itemView.findViewById(R.id.album_song);
            AlbumSinger = itemView.findViewById(R.id.album_singer);
            mImageLoaderManager = new ImageLoaderManager();
            AlbumPics =  itemView.findViewById(R.id.album_view);




        }
    }

}
