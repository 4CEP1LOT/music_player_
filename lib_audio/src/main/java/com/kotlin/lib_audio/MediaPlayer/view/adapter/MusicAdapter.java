package com.kotlin.lib_audio.MediaPlayer.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kotlin.lib_audio.R;
import com.kotlin.lib_base.model.audio.SongDetails;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder>{

    public List<SongDetails.Songs> mSongs;

    public MusicAdapter(List<SongDetails.Songs> mSongs) {
        this.mSongs = mSongs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_player,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.itemView).load(mSongs.get(position).getAl().getPicUrl()).into(holder.mMusicCover);
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView mMusicCover;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           mMusicCover = itemView.findViewById(R.id.music_cover);


       }
   }
}
