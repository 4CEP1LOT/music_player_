package com.kotlin.musiclearning.view.discovery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.kotlin.musiclearning.R;
import com.kotlin.musiclearning.event.discovery.DSDj24hoursEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.DSDJ24hours;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class DSDJ24hoursAdapter extends RecyclerView.Adapter<DSDJ24hoursAdapter.MyViewHolder>{

    private List<DSDJ24hours.Data.List> m24hours = new ArrayList<>();




        @Subscribe(threadMode = ThreadMode.POSTING)
        public void onDs24hours(DSDj24hoursEvent event){
        try {
            m24hours  = HomeManager.getInstance().getmDJ24hours().getData().getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onDs24hours(new DSDj24hoursEvent());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dj_24hours,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mDj24hoursText.setText(m24hours.get(position).getProgram().getName());
       Glide.with(holder.itemView).load(m24hours.get(position).getProgram().getCoverUrl()).apply(RequestOptions.bitmapTransform(new BlurTransformation(3,5))).into(holder.mDj24hoursPic);
       Glide.with(holder.itemView).load(m24hours.get(position).getProgram().getDj().getAvatarUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.mDj24hoursAvatar);
       holder.mDj24hoursCreator.setText(m24hours.get(position).getProgram().getDj().getNickname());
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class  MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mDj24hoursPic,mDj24hoursAvatar;
        TextView mDj24hoursText,mDj24hoursCreator;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDj24hoursText = itemView.findViewById(R.id.dj_24hour_name);
            mDj24hoursPic = itemView.findViewById(R.id.dj_24hour_pic);
            mDj24hoursAvatar = itemView.findViewById(R.id.dj_24hour_avatar);
            mDj24hoursCreator = itemView.findViewById(R.id.dj_24hour_creator);
        }
    }
}
