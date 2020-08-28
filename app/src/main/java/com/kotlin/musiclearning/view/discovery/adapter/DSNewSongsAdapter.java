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
import com.kotlin.musiclearning.event.discovery.DSNewSongsEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.DSNewSongs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class DSNewSongsAdapter extends RecyclerView.Adapter<DSNewSongsAdapter.MyViewHolder> {

    private List<DSNewSongs.Result> mNewSongs = new ArrayList<>();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onNewSongs(new DSNewSongsEvent());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newsongs,parent,false);

        return new MyViewHolder(rootView);
    }

        @Subscribe(threadMode = ThreadMode.POSTING)
    public void onNewSongs(DSNewSongsEvent event){
        try {
            mNewSongs = HomeManager.getInstance().getNewSongs().getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

     //     Glide.with(holder.itemView).load(mNewSongs.get((position)+4).getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20, 0))).into(holder.mNewSongsPic2);
//        Glide.with(holder.itemView).load(mNewSongs.get((position)+5).getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20, 0))).into(holder.mNewSongsPic3);
                holder.mNewSongsName3.setText(mNewSongs.get(position).getSong().getName());
                holder.mNewSongsSinger3.setText(mNewSongs.get(position).getSong().getArtists().get(0).getName());
                holder.mNewSongsName2.setText(mNewSongs.get(position).getSong().getName());
                holder.mNewSongsSinger2.setText(mNewSongs.get(position).getSong().getArtists().get(0).getName());
                holder.mNewSongsName1.setText(mNewSongs.get(position).getSong().getName());
                holder.mNewSongsSinger1.setText(mNewSongs.get(position).getSong().getArtists().get(0).getName());
                Glide.with(holder.itemView).load(mNewSongs.get(position).getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20, 0))).into(holder.mNewSongsPic3);
                Glide.with(holder.itemView).load(mNewSongs.get(position).getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20, 0))).into(holder.mNewSongsPic2);
                Glide.with(holder.itemView).load(mNewSongs.get(position).getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20, 0))).into(holder.mNewSongsPic1);



        }







    @Override
    public int getItemCount() {
        return 3;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
         TextView mNewSongsName3,mNewSongsSinger3,mNewSongsName2,mNewSongsName1,mNewSongsSinger2,mNewSongsSinger1;
         ImageView mNewSongsPic1,mNewSongsPic2,mNewSongsPic3;


        public MyViewHolder(@NonNull View rootView) {
            super(rootView);

            mNewSongsName3 = rootView.findViewById(R.id.newsongs_name3);

            mNewSongsPic3 = rootView.findViewById(R.id.newsong_pic3);
            mNewSongsPic1 = rootView.findViewById(R.id.newsong_pic1);
            mNewSongsPic2 = rootView.findViewById(R.id.newsong_pic2);
             mNewSongsSinger3 = rootView.findViewById(R.id.newsongs_singer3);
             mNewSongsSinger2 = rootView.findViewById(R.id.newsongs_singer2);
             mNewSongsSinger1 = rootView.findViewById(R.id.newsongs_singer1);
            mNewSongsName1= rootView.findViewById(R.id.newsongs_name1);
            mNewSongsName2= rootView.findViewById(R.id.newsongs_name2);
        }
    }
}
