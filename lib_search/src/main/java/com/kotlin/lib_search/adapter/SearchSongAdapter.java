package com.kotlin.lib_search.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchSong;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.event.SearchSongEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SearchSongAdapter extends RecyclerView.Adapter<SearchSongAdapter.MyViewHolder> {
    private  List<SearchSong.Result.Songs> mQueue = new ArrayList<>();



    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onSearchSongEvent(new SearchSongEvent());

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSearchSongEvent( SearchSongEvent event) {
        mQueue = SearchManager.getInstance().getSearchSong().getResult().getSongs();
        Log.d("SearchSongAdapter","给点力啊老哥");
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SearchSong.Result.Songs songs = mQueue.get(position);
        SearchSong.Result.Songs.Artists artists = mQueue.get(position).getArtists().get(0);
            holder.itemSongName.setText(songs.getName());
            holder.itemSingerName.setText(artists.getName());



    }



    @Override
    public int getItemCount() {
        return 30;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemSongName,itemSingerName;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            itemSongName = itemView.findViewById(R.id.album_song);
            itemSingerName = itemView.findViewById(R.id.album_singer);



        }

    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }
}
