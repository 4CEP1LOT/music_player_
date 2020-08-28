package com.kotlin.lib_search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchPlayList;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.event.SearchPlayListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SearchPlayListAdapter extends RecyclerView.Adapter<SearchPlayListAdapter.MyViewHolder> {

    private List<SearchPlayList.Result.Playlists> mQueue;
    private int PlayCount;
    private int BookCount;


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onSearchPlayList(new SearchPlayListEvent());
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSearchPlayList(SearchPlayListEvent event){
        mQueue = SearchManager.getInstance().getSearchPlayList().getResult().getPlaylists();

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_playlist,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SearchPlayList.Result.Playlists mPlayList= mQueue.get(position);

        holder.itemAllPlayListSongNumber.setText(String.valueOf(mPlayList.getTrackCount()));
        holder.itemAllPlayListName.setText(mPlayList.getName());
        holder.itemAllPlayCount.setText(String.valueOf(mPlayList.getPlayCount()));
        holder.itemAllBookCount.setText(String.valueOf(mPlayList.getBookCount()));
        holder.mImageManager.displayImageForView(holder.itemAllPlayListPic,mPlayList.getCoverImgUrl());




    }


    @Override
    public int getItemCount() {
        return 4;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemAllPlayListSongNumber,itemAllPlayListName,itemAllPlayCount,itemAllBookCount;
        ImageLoaderManager mImageManager;
        ImageView itemAllPlayListPic;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            itemAllPlayListName = itemView.findViewById(R.id.all_playlist_name);
            itemAllPlayListSongNumber = itemView.findViewById(R.id.all_playlist_trackCount);
            mImageManager = new ImageLoaderManager();
            itemAllPlayListPic = itemView.findViewById(R.id.all_playlist_pic);
            itemAllPlayCount = itemView.findViewById(R.id.all_playlist_playCount);
            itemAllBookCount = itemView.findViewById(R.id.all_playlist_bookCount);
        }
    }

}
