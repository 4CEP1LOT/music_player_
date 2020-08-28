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
import com.kotlin.musiclearning.event.discovery.DSHotDjEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.DSHotDj;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DSHotDjAdapter extends RecyclerView.Adapter<DSHotDjAdapter.MyViewHolder> {

    private List<DSHotDj.DjRadios> mList;


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onHotDjEvent(DSHotDjEvent event){
        try {
            mList = HomeManager.getInstance().getHotDj().getDjRadios();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onHotDjEvent(new DSHotDjEvent());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotdj,parent,false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.itemView).load(mList.get(position).getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0))).into(holder.mHotdjPic);
        holder.mHotdjText.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

   static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mHotdjPic;
        TextView mHotdjText;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           mHotdjPic = itemView.findViewById(R.id.hotdj_pic);
           mHotdjText = itemView.findViewById(R.id.hotdj_text);
       }
   }
}
