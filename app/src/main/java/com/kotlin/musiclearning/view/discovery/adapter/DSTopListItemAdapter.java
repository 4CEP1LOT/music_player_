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
import com.kotlin.lib_base.model.discoverymodel.DSTopList;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DSTopListItemAdapter extends RecyclerView.Adapter<DSTopListItemAdapter.MyViewHolder> {

    private List<DSTopList.Playlist.Tracks> mTopList = new ArrayList<>();

    public DSTopListItemAdapter(List<DSTopList.Playlist.Tracks> mTopList) {
        this.mTopList = mTopList;
    }

    @NonNull
    @Override
    public DSTopListItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toplist,parent,false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DSTopListItemAdapter.MyViewHolder holder, int position) {
        holder.mTopListName.setText(mTopList.get(position).getName());
        holder.mTopListSinger.setText(mTopList.get(position).getAr().get(0).getName());
        holder.mTopListNum.setText(String.valueOf(position+1));
        Glide.with(holder.itemView).load(mTopList.get(position).getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0))).into(holder.mTopListPic);



    }

    @Override
    public int getItemCount() {

        return 6;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTopListName,mTopListSinger,mTopListNum;
         ImageView mTopListPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTopListPic = itemView.findViewById(R.id.toplist_pic);
            mTopListSinger= itemView.findViewById(R.id.toplist_singer);
            mTopListName =itemView.findViewById(R.id.toplist_name);
            mTopListNum = itemView.findViewById(R.id.toplist_number);


        }
    }
}
