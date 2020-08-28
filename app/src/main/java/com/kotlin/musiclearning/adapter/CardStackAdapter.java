package com.kotlin.musiclearning.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kotlin.lib_base.model.audio.SongUrl;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.model.discoverymodel.MinePersonalFm;

import java.util.ArrayList;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.MyViewHolder> {
    private List<MinePersonalFm.Data> mPersonalFm = new ArrayList<>();

    public CardStackAdapter(List<MinePersonalFm.Data> mPersonalFm, List<SongUrl.Data> mSongUrl) {
        this.mPersonalFm = mPersonalFm;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_fm,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                Glide.with(holder.itemView.getContext()).load(mPersonalFm.get(position).getAlbum().getPicUrl()).circleCrop().into(holder.mPersonfmPic);




        Log.d("personalfm", String.valueOf(position));
        Log.d("personalfm", String.valueOf(mPersonalFm.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return mPersonalFm.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mPersonfmPic;
        RelativeLayout mRelativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mPersonfmPic = itemView.findViewById(R.id.personal_cover);

        }
    }
}
