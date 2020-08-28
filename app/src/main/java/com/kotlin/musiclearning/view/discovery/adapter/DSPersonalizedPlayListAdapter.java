package com.kotlin.musiclearning.view.discovery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.model.discoverymodel.DSPersonalizedPlayList;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DSPersonalizedPlayListAdapter extends RecyclerView.Adapter<DSPersonalizedPlayListAdapter.MyViewHolder> {

    private List<DSPersonalizedPlayList.Result> mQueue;
    private ImageView mPicView ;

    public DSPersonalizedPlayListAdapter(List<DSPersonalizedPlayList.Result> mQueue) {
        this.mQueue = mQueue;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personalizedplaylist,parent,false);
        mPicView= itemView.findViewById(R.id.personlized_playlist_pic);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DSPersonalizedPlayList.Result mResult =  mQueue.get(position);
        Glide.with(holder.itemView.getContext()).load(mResult.getPicUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0)))
                .into(mPicView);
        holder.mTextView.setText(mResult.getName());
        holder.mPlayCount.setText((mResult.getTrackCount()) +"万");

    }

    @Override
    public int getItemCount() {
        return mQueue.size();
    }

     static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView,mPlayCount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.personlized_playlist_text);
            mPlayCount = itemView.findViewById(R.id.playlist_playCount);
        }
    }
}
