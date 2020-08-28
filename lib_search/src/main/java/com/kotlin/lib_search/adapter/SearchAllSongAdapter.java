package com.kotlin.lib_search.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchAll;
import com.kotlin.lib_search.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SearchAllSongAdapter extends RecyclerView.Adapter<SearchAllSongAdapter.MyViewHolder> {

    private SearchAll mQueue = new SearchAll();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song,parent,false);
        return new MyViewHolder(itemView);
    }

    public SearchAllSongAdapter(SearchAll mQueue) {
        this.mQueue = mQueue;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        position = 4;
        SearchAll.Result.Song.Songs songs = mQueue.getResult().getSong().getSongs().get(position);
        SearchAll.Result.Song.Songs.Ar artists = songs.getAr().get(position);
        holder.itemAllSongName.setText(songs.getName());
        holder.itemAllSingerName.setText(artists.getName());




    }


    @Override
    public int getItemCount() {
        return 4;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemAllSongName,itemAllSingerName;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            itemAllSongName = itemView.findViewById(R.id.album_song);
            itemAllSingerName = itemView.findViewById(R.id.album_singer);



        }
    }

}
