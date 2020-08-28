package com.kotlin.musiclearning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.musiclearning.R;

import java.util.List;

public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.MyViewHolder> {
//    private MineLikedList.Playlist mLikedPlayList;

    private List<SongDetails.Songs> mList;

    public LikedAdapter(List<SongDetails.Songs> mList) {
//        this.mLikedPlayList = mLikedPlayList;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_likedlist,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        if (position<mLikedPlayList.getTracks().size()){
//            holder.mLikedSong.setText(mLikedPlayList.getTracks().get(position).getName());
//        holder.mLikedSinger.setText(mLikedPlayList.getTracks().get(position).getAr().get(0).getName());
//        holder.mLikedNum.setText(String.valueOf(position+1));
//        }else {
            holder.mLikedSong.setText(mList.get(position).getName());
            holder.mLikedSinger.setText(mList.get(position).getAr().get(0).getName());
            holder.mLikedNum.setText(String.valueOf(position+1));

//        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mLikedSong,mLikedSinger,mLikedNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mLikedSong = itemView.findViewById(R.id.item_liked_song);
            mLikedSinger = itemView.findViewById(R.id.item_liked_singer);
            mLikedNum = itemView.findViewById(R.id.item_number);
        }
    }
}
