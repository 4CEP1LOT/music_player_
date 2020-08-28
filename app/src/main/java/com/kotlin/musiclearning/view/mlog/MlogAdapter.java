package com.kotlin.musiclearning.view.mlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.musiclearning.R;
import com.kotlin.musiclearning.event.MlogEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.MlogModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MlogAdapter extends RecyclerView.Adapter<MlogAdapter.MyViewHolder>implements View.OnClickListener{

    private List<MlogModel.Datas> mQueue;
    private ArrayList<Integer> heights;
    ImageLoaderManager imageLoaderManager = new ImageLoaderManager();
    private OnItemClickListener mOnItemClickListener;




    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onLongItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());//注意这里使用getTag方法获取position
        }
    }

        public void setOnItemClickListener (OnItemClickListener listener){
            this.mOnItemClickListener = listener;
        }




    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMlogEvent(MlogEvent event){
         mQueue = HomeManager.getInstance().getMlogModel().getDatas();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onMlogEvent(new MlogEvent());

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mlog,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        myViewHolder.itemView.setOnClickListener(this);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mlogText.setText(mQueue.get(position).getData().getTitle());
        if (mQueue.get(position).getData().getCreator()!=null){
        holder.mlogCreator.setText(mQueue.get(position).getData().getCreator().getNickname());
            imageLoaderManager.displayImageForCircleView(holder.mlogAvatar,mQueue.get(position).getData().getCreator().getAvatarUrl());

        }else {
            holder.mlogCreator.setText(mQueue.get(position).getData().getLiveData().getLiveUser().getNickName());
            imageLoaderManager.displayImageForCircleView(holder.mlogAvatar,mQueue.get(position).getData().getLiveData().getLiveUser().getAvatarUrl());

        }
        Glide.with(holder.itemView.getContext()).load(mQueue.get(position).getData().getCoverUrl())
                .centerCrop()
                .into(holder.mlogPic);

        holder.itemView.setTag(position);//将position保存在itemView的tag中，一边点击时获取
      //  holder.mlogCard.setTag(position);//将position保存在itemView的tag中，一边点击时获取



    }

    @Override
    public int getItemCount() {
        return mQueue.size();
    }





    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mlogText,mlogCreator;
        ImageView mlogPic,mlogAvatar;
        CardView mlogCard;
        public MyViewHolder(View itemView) {
            super(itemView);
            mlogPic = itemView.findViewById(R.id.mlog_pic);
            mlogText = itemView.findViewById(R.id.mlog_text);
            mlogCreator = itemView.findViewById(R.id.mlog_creator);
            mlogAvatar = itemView.findViewById(R.id.creator_avatar);
            mlogCard = itemView.findViewById(R.id.mlog_card);

        }

    }






}
