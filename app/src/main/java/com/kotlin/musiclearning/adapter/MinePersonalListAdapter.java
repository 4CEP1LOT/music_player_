package com.kotlin.musiclearning.adapter;

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

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MinePersonalListAdapter extends RecyclerView.Adapter<MinePersonalListAdapter.MyViewHolder> {



    private List<DSPersonalizedPlayList.Result> mPersonalList;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }


    public MinePersonalListAdapter(List<DSPersonalizedPlayList.Result> mPersonalList) {
        this.mPersonalList = mPersonalList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_personal,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DSPersonalizedPlayList.Result mResult = mPersonalList.get(position);
        Glide.with(holder.itemView.getContext()).load(mResult.getPicUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0)))
                .into(holder.mMineRecommendCover);
        holder.mMineRecommendText.setText(mResult.getName());
        holder.mMineRecommendTrackCount.setText((mResult.getTrackCount()) +"万");

        }



    @Override
    public int getItemCount() {
        return 8;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mMineRecommendCover;
        TextView mMineRecommendText,mMineRecommendTrackCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mMineRecommendCover = itemView.findViewById(R.id.mine_recommend__cover);
            mMineRecommendText = itemView.findViewById(R.id.mine_recommend__text);
            mMineRecommendTrackCount = itemView.findViewById(R.id.mine_recommend__count);

        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }
}
