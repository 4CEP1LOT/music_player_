package com.kotlin.lib_search.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchVideo;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.event.SearchAllEvent;
import com.kotlin.lib_search.event.SearchVideoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class SearchVideoAdapter extends RecyclerView.Adapter<SearchVideoAdapter.MyViewHolder> {


    private List<SearchVideo.Result.Videos> mQueue;
    private ImageView itemAllPic;


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        onSearchVideoEvent(new SearchVideoEvent());

    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onSearchVideoEvent( SearchVideoEvent event) {
        mQueue = SearchManager.getInstance().getSearchVideo().getResult().getVideos();
        Log.d("SearchSongAdapter","给点力啊老哥");
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        itemAllPic = itemView.findViewById(R.id.video_image);



        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SearchVideo.Result.Videos mVideos = mQueue.get(position);
        holder.itemAllVideoName.setText(mVideos.getTitle());
        holder.itemAllVideoCreator.setText(mVideos.getCreator().get(0).getUserName());
        holder.ItemAllVideoTime.setText(timeParse((mVideos.getDurationms())));
        Glide.with(holder.itemView.getContext()).load(mQueue.get(position).getCoverUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25,0)))
                .into(itemAllPic);

    }

    @Override
    public int getItemCount() {
        return mQueue.size();
    }

    public static String timeParse(long duration) {
        String time = "" ;
        long minute = duration / 60000 ;
        long seconds = duration % 60000 ;
        long second = Math.round((float)seconds/1000) ;
        if( minute < 10 ){
            time += "0" ;
        }
        time += minute+":" ;
        if( second < 10 ){
            time += "0" ;
        }
        time += second ;
        return time ;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemAllVideoName,itemAllVideoCreator,ItemAllVideoTime;
        ImageView itemAllPic;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            itemAllVideoName = itemView.findViewById(R.id.video_name);
            itemAllVideoCreator = itemView.findViewById(R.id.video_creator);
            ItemAllVideoTime = itemView.findViewById(R.id.video_time);





        }
    }
}
