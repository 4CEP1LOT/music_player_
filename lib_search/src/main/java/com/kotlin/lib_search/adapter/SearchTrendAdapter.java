package com.kotlin.lib_search.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchTrend;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.event.SearchTrendEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SearchTrendAdapter extends RecyclerView.Adapter<SearchTrendAdapter.MyViewHolder> {
    private ImageLoaderManager imageLoaderManager;
    private Context mContext;
    private List<SearchTrend.Data> mQueue = new ArrayList<>();
    private  View itemView;
    ImageView trendPicture;



    public SearchTrendAdapter() {
        EventBus.getDefault().register(this);

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchTrendEvent( SearchTrendEvent event) {
        Log.d("login","loginEvent");
        //登录之后把未登录布局隐藏
        //用户头像显示出来
        mQueue = SearchManager.getInstance().getSearchTrend().getData();
        Log.d("login","头像");

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        imageLoaderManager = new ImageLoaderManager();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_search_trend,parent,false);
        trendPicture= itemView.findViewById(R.id.trend_pic);

        return new MyViewHolder(itemView);

    }









    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        SearchTrend.Data searchTrend = mQueue.get(position);
        holder.trendName.setText(String.valueOf(searchTrend.getSearchWord()));
            holder.trendContent.setText(String.valueOf(searchTrend.getContent()));
            holder.trendNumber.setText(String.valueOf((position)+1));
            holder.trendScore.setText(String.valueOf(searchTrend.getScore()));
            if (searchTrend.getIconUrl()!=null){
            imageLoaderManager.displayImageForView(trendPicture,String.valueOf(searchTrend.getIconUrl()));
            }else{
                trendPicture.setVisibility(View.GONE);
        }
        }




    @Override
    public int getItemCount() {
        return mQueue.size();
    }




    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView trendName,trendContent,trendNumber,trendScore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trendName = itemView.findViewById(R.id.trend_name);
            trendContent = itemView.findViewById(R.id.trend_content);
            trendNumber = itemView.findViewById(R.id.trend_number);
            trendScore = itemView.findViewById(R.id.trend_score);

        }
    }
}
