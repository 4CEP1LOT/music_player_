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
import com.kotlin.musiclearning.event.discovery.DSOriginEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.DSOrigin;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DSOriginAdapter extends RecyclerView.Adapter<DSOriginAdapter.MyViewHolder> {
    private List<DSOrigin.Playlist.Tracks> mOriginList = new ArrayList<>();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onOriginEvent(new DSOriginEvent());

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onOriginEvent(DSOriginEvent event){
        try {
            mOriginList = HomeManager.getInstance().getOrigin().getPlaylist().getTracks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_origin,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mOrigintName.setText(mOriginList.get(position).getName());
        holder.mOrigintSinger.setText(mOriginList.get(position).getAr().get(0).getName());
        holder.mOriginNum.setText(String.valueOf(position+1));
        Glide.with(holder.itemView).load(mOriginList.get(position).getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0))).into(holder.mOrigintPic);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mOrigintSinger,mOrigintName,mOriginNum;
        ImageView mOrigintPic;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mOrigintName = itemView.findViewById(R.id.originlist_name);
            mOrigintSinger= itemView.findViewById(R.id.originlist_singer);
            mOrigintPic =itemView.findViewById(R.id.originlist_pic);
            mOriginNum = itemView.findViewById(R.id.origin_list_number);
        }
    }
}
