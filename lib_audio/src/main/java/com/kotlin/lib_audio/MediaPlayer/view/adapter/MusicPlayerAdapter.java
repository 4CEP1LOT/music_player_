package com.kotlin.lib_audio.MediaPlayer.view.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MusicPlayerAdapter extends BannerAdapter<SongDetails.Songs,MusicPlayerAdapter.BannerViewHolder> {


    public MusicPlayerAdapter(List<SongDetails.Songs> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, SongDetails.Songs data, int position, int size) {
        Glide.with(holder.imageView.getContext()).load(data.getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,20))).into(holder.imageView);

    }




    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
