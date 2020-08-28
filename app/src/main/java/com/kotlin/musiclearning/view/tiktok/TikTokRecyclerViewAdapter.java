package com.kotlin.musiclearning.view.tiktok;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.kotlin.musiclearning.R;
import com.kotlin.musiclearning.event.VideoPlayEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_video.videoplayer.video.JZMediaIjk;
import com.kotlin.lib_video.videoplayer.video.JzvdStdTikTok;
import com.kotlin.lib_base.model.MlogModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;

public class TikTokRecyclerViewAdapter extends RecyclerView.Adapter<TikTokRecyclerViewAdapter.MyViewHolder> {

    public static final String TAG = "AdapterTikTokRecyclerView";
    private Context context;
    private List<MlogModel.Datas> mQueue;

    public TikTokRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
        onVideoPlayEvent(new VideoPlayEvent());
    }
    @Subscribe (threadMode = ThreadMode.POSTING)
    public void onVideoPlayEvent(VideoPlayEvent event){
        mQueue = HomeManager.getInstance().getMlogModel().getDatas();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_tiktok, parent,
                false));
        return holder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JZDataSource jzDataSource;
        if  (mQueue.get(position).getData().getLiveData() != null) {
             jzDataSource= new JZDataSource(mQueue.get(position).getData().getLiveData().getLiveRoom().getHttpPullUrl(), mQueue.get(position).getData().getTitle());
            holder.MlogName.setText(mQueue.get(position).getData().getLiveData().getLiveUser().getNickName());
            Glide.with(holder.jzvdStd.getContext()).load(mQueue.get(position).getData().getLiveData().getLiveUser().getAvatarUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(60))).into(holder.MlogPic);



        }else {
             jzDataSource = new JZDataSource(mQueue.get(position).getData().getUrlInfo().getUrl(), mQueue.get(position).getData().getTitle());
            holder.MlogName.setText(mQueue.get(position).getData().getCreator().getNickname());
            Glide.with(holder.jzvdStd.getContext()).load(mQueue.get(position).getData().getCreator().getAvatarUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(60))).into(holder.MlogPic);


        }


        jzDataSource.looping = true;
        holder.jzvdStd.setUp(jzDataSource, Jzvd.SCREEN_NORMAL, JZMediaIjk.class);
        holder.MlogDescription.setText(mQueue.get(position).getData().getTitle());
    }

    @Override
    public int getItemCount() {
        return mQueue.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JzvdStdTikTok jzvdStd;
        TextView MlogName,MlogDescription;
        ImageView MlogPic;

        public MyViewHolder(View itemView) {
            super(itemView);
            jzvdStd = itemView.findViewById(R.id.videoplayer);
            MlogName = itemView.findViewById(R.id.tiktok_name);
            MlogDescription = itemView.findViewById(R.id.tiktok_description);
            MlogPic = itemView.findViewById(R.id.tiktok_avatar);

        }
    }

}
