package com.kotlin.musiclearning.view.discovery.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.model.discoverymodel.DSNewSongs;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class DSNewSongsRecyclerAdapter extends RecyclerView.Adapter<DSNewSongsRecyclerAdapter.MyViewHolder> {
    private List<DSNewSongs.Result> mNewSongs = new ArrayList<>();

    public DSNewSongsRecyclerAdapter(List<DSNewSongs.Result> mNewSongs) {
        this.mNewSongs = mNewSongs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newsongs_recycler,parent,false);

        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.mRecyclcerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        DSNewSongsAdapter mAdapter = new DSNewSongsAdapter();
        holder.mRecyclcerView.setAdapter(mAdapter);

        Glide.with(holder.itemView.getContext()).asBitmap().load(mNewSongs.get(position).getPicUrl()).apply(RequestOptions.bitmapTransform(new BlurTransformation(23, 8))).into(new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Drawable drawable = new BitmapDrawable(holder.itemView.getResources(), resource);
                holder.mCardView.setBackground(drawable);
            }

        });

    }

    @Override
    public int getItemCount() {
        return 3;
    }



    static class MyViewHolder extends RecyclerView.ViewHolder {
       RecyclerView mRecyclcerView;
        CardView mCardView;

        public MyViewHolder(@NonNull View rootView) {
            super(rootView);

            mRecyclcerView = rootView.findViewById(R.id.newsongs_recycler);

            mCardView = rootView.findViewById(R.id.newsongs_card);

        }
    }




}

