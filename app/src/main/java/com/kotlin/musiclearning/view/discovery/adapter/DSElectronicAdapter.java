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
import com.kotlin.musiclearning.event.discovery.DSElectronicEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.DSElectronic;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DSElectronicAdapter extends RecyclerView.Adapter<DSElectronicAdapter.MyViewHolder> {
    private List<DSElectronic.Playlist.Tracks> mElectronic = new ArrayList<>();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onElectronic(new DSElectronicEvent());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onElectronic(DSElectronicEvent event){
        try {
            mElectronic = HomeManager.getInstance().getmElectronic().getPlaylist().getTracks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_electronic,parent,false);

        return new MyViewHolder(itemView);
    }

    public DSElectronicAdapter(List<DSElectronic.Playlist.Tracks> mElectronic) {
        this.mElectronic = mElectronic;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mElectronicName.setText(mElectronic.get(position).getName());
        holder.mElectronicSinger.setText(mElectronic.get(position).getAr().get(0).getName());
        holder.mElectronicNum.setText(String.valueOf(position+1));
        Glide.with(holder.itemView).load(mElectronic.get(position).getAl().getPicUrl()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0))).into(holder.mElectronicPic);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mElectronicSinger,mElectronicName,mElectronicNum;
        ImageView mElectronicPic;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mElectronicName = itemView.findViewById(R.id.electronic_name);
            mElectronicSinger= itemView.findViewById(R.id.electronic_singer);
            mElectronicPic =itemView.findViewById(R.id.electronic_pic);
            mElectronicNum = itemView.findViewById(R.id.electronic_number);
        }
    }

}
