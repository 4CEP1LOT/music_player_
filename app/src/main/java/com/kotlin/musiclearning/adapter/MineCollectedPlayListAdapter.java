package com.kotlin.musiclearning.adapter;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.model.discoverymodel.MinePlayList;

import java.util.ArrayList;
import java.util.List;

public class MineCollectedPlayListAdapter extends RecyclerView.Adapter<MineCollectedPlayListAdapter.MyViewHolder> {

    private List<MinePlayList.Playlist> mCreatedPlayList = new ArrayList<>();




    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect_playlist,parent,false);

        return new MyViewHolder(itemView);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            if (mCreatedPlayList.get(position).getCreator().getUserId() != mCreatedPlayList.get(0).getUserId()) {
                holder.mCollectedText.setText(mCreatedPlayList.get(position).getName());
                holder.mCollectedCount.setText(String.valueOf(mCreatedPlayList.get(position).getTrackCount()));
                Glide.with(holder.itemView.getContext()).load(mCreatedPlayList.get(position).getCoverImgUrl()).circleCrop().into(holder.mCollectedCover);
                Log.d("mCreatedPlayList", String.valueOf(mCreatedPlayList.get(position).getCreator().getUserId()));
                Log.d("mCreatedPlayList", String.valueOf(position));
            }else {
                holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0,0));

            }

        }



    @Override
    public int getItemCount() {
        return mCreatedPlayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mCollectedCover;
        TextView mCollectedText,mCollectedCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCollectedCover = itemView.findViewById(R.id.mine_collected_cover);
            mCollectedText = itemView.findViewById(R.id.mine_collected_text);
            mCollectedCount = itemView.findViewById(R.id.mine_collected_count);
        }
    }
}
