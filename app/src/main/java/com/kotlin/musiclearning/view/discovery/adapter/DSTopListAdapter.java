package com.kotlin.musiclearning.view.discovery.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.kotlin.musiclearning.R;
import com.kotlin.musiclearning.event.discovery.DSElectronicEvent;
import com.kotlin.musiclearning.event.discovery.DSOriginEvent;
import com.kotlin.musiclearning.event.discovery.DSTopListEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.DSElectronic;
import com.kotlin.lib_base.model.discoverymodel.DSOrigin;
import com.kotlin.lib_base.model.discoverymodel.DSTopList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class DSTopListAdapter extends RecyclerView.Adapter<DSTopListAdapter.MyViewHolder> {


    private DSTopList.Playlist mTopList = new DSTopList.Playlist();
    private List<DSOrigin.Playlist.Tracks> mOriginList = new ArrayList<>();
    private List<DSElectronic.Playlist.Tracks> mElectronic = new ArrayList<>();


//    public DSTopListAdapter(List<DSTopList.Playlist.Tracks> list) {
//        this.mTopList = list;
//    }



        @Subscribe(threadMode = ThreadMode.POSTING)
    public void onTopListEvent(DSTopListEvent dsTopListEvent) {
        try {
            mTopList = HomeManager.getInstance().getmTopList().getPlaylist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onOriginEvent(DSOriginEvent event){
        try {
            mOriginList = HomeManager.getInstance().getOrigin().getPlaylist().getTracks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onElectronic(DSElectronicEvent event){
        try {
            mElectronic = HomeManager.getInstance().getmElectronic().getPlaylist().getTracks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onElectronic(new DSElectronicEvent());
        onOriginEvent(new DSOriginEvent());
        onTopListEvent(new DSTopListEvent());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_toplist,parent,false);
        onElectronic(new DSElectronicEvent());
        onOriginEvent(new DSOriginEvent());
        onTopListEvent(new DSTopListEvent());
        return new MyViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder. mRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        if (position == 0) {
            Glide.with(holder.itemView.getContext()).asBitmap().load(mTopList.getTracks().get(0).getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new BlurTransformation(5, 8))).into(new SimpleTarget<Bitmap>(350, 300) {

                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Drawable drawable = new BitmapDrawable(holder.itemView.getResources(), resource);
                    holder.mRelativeLayout = holder.itemView.findViewById(R.id.toplist_relativelayout);
                    holder.mRelativeLayout.setBackground(drawable);

                }


            });
            holder.mTextView.setText("云音乐飙升榜");
            holder.mDSTopListItemAdapter = new DSTopListItemAdapter(mTopList.getTracks());
            holder.mRecyclerView.setAdapter(holder.mDSTopListItemAdapter);


        }else if (position==1){

            Glide.with(holder.itemView.getContext()).asBitmap().load(mOriginList.get(0).getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new BlurTransformation(5, 8))).into(new SimpleTarget<Bitmap>(350, 300) {

                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Drawable drawable = new BitmapDrawable(holder.itemView.getResources(), resource);
                    holder.mRelativeLayout = holder.itemView.findViewById(R.id.toplist_relativelayout);
                    holder.mRelativeLayout.setBackground(drawable);

                }
            });

            holder.mTextView.setText("网易原创音乐榜");
            holder.mOriginAdapter = new DSOriginAdapter();
            holder.mRecyclerView.setAdapter(holder.mOriginAdapter);


        }else if (position==2){

            Glide.with(holder.itemView.getContext()).asBitmap().centerCrop().load(mElectronic.get(0).getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new BlurTransformation(5, 8))).into(new SimpleTarget<Bitmap>(350, 300) {

                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Drawable drawable = new BitmapDrawable(holder.itemView.getResources(), resource);
                    holder.mRelativeLayout = holder.itemView.findViewById(R.id.toplist_relativelayout);
                    holder.mRelativeLayout.setBackground(drawable);

                }
            });

            holder.mTextView.setText("云音乐电音榜");
            holder.mElectronicAdapter = new DSElectronicAdapter(mElectronic);
            holder.mRecyclerView.setAdapter(holder.mElectronicAdapter);



        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView mRecyclerView;
        private DSTopListItemAdapter mDSTopListItemAdapter;
        private DSOriginAdapter mOriginAdapter;
        private DSElectronicAdapter mElectronicAdapter;
        private RelativeLayout mRelativeLayout;
        private LinearLayout mLinear;
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.toplist_recycler);
            mTextView = itemView.findViewById(R.id.origin_title);
            mLinear = itemView.findViewById(R.id.originlist_title_linear);

        }
    }



}
