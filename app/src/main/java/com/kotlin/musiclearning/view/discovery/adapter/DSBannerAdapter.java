package com.kotlin.musiclearning.view.discovery.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kotlin.lib_base.model.discoverymodel.DSBannerModel;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class DSBannerAdapter extends BannerAdapter<DSBannerModel.Banners,DSBannerAdapter.BannerViewHolder> {


    public DSBannerAdapter(List<DSBannerModel.Banners> datas) {
        super(datas);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);

    }

    @Override
    public void onBindView(BannerViewHolder holder, DSBannerModel.Banners data, int position, int size) {

        Glide.with(holder.itemView.getContext())
                .load(mDatas.get(position).getPic())
                .into(holder.imageView);
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public BannerViewHolder(@NonNull View view) {
                super(view);
                this.imageView = (ImageView) view;
             //   imageView =view.findViewById(R.id.banner_image);
            }
        }




}

