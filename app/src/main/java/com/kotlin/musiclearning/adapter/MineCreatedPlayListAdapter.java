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
import com.kotlin.musiclearning.event.mine.MinePlayListEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.MinePlayList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MineCreatedPlayListAdapter extends RecyclerView.Adapter<MineCreatedPlayListAdapter.MyViewHolder>{


    private List<MinePlayList.Playlist> mCreatedPlayList;
    private List<MinePlayList.Playlist> mList;
    private static final String TRUE = "true";
    private static final String FALSE = "false";



    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMinePlayList(MinePlayListEvent event) {
        try {
            mCreatedPlayList = HomeManager.getInstance().getmMinePlayList().getPlaylist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onMinePlayList(new MinePlayListEvent());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine,parent,false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            if (mCreatedPlayList.get(position).getCreator().getUserId() == mCreatedPlayList.get(0).getUserId()) {

                holder.mMineCreatedTrackCount.setText(mCreatedPlayList.get(position).getTrackCount() + "首");
                holder.mMineCreatedText.setText(mCreatedPlayList.get(position).getName());
                Glide.with(holder.itemView.getContext()).load(mCreatedPlayList.get(position).getCoverImgUrl()).circleCrop().into(holder.mMineCreatedCover);
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



    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mMineCreatedCover;
        TextView mMineCreatedText,mMineCreatedTrackCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mMineCreatedCover = itemView.findViewById(R.id.mine_created_cover);
            mMineCreatedText = itemView.findViewById(R.id.mine_created_text);
            mMineCreatedTrackCount = itemView.findViewById(R.id.mine_created_track_count);

        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }
}
