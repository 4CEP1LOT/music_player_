package com.kotlin.musiclearning.view.discovery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kotlin.musiclearning.R;
import com.kotlin.musiclearning.event.discovery.DSBannerEvent;
import com.kotlin.musiclearning.event.discovery.DSPersonalizedPlayListEvent;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.discoverymodel.DSBannerModel;
import com.kotlin.lib_base.model.discoverymodel.DSDJ24hours;
import com.kotlin.lib_base.model.discoverymodel.DSElectronic;
import com.kotlin.lib_base.model.discoverymodel.DSHotDj;
import com.kotlin.lib_base.model.discoverymodel.DSNewSongs;
import com.kotlin.lib_base.model.discoverymodel.DSOrigin;
import com.kotlin.lib_base.model.discoverymodel.DSPersonalizedPlayList;
import com.kotlin.lib_base.model.discoverymodel.DSTopList;
import com.kotlin.musiclearning.view.discovery.adapter.DSBannerAdapter;
import com.kotlin.musiclearning.view.discovery.adapter.DSDJ24hoursAdapter;
import com.kotlin.musiclearning.view.discovery.adapter.DSHotDjAdapter;
import com.kotlin.musiclearning.view.discovery.adapter.DSNewSongsAdapter;
import com.kotlin.musiclearning.view.discovery.adapter.DSPersonalizedPlayListAdapter;
import com.kotlin.musiclearning.view.discovery.adapter.DSTopListAdapter;

import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryFragment extends Fragment {

    private List<DSBannerModel.Banners>    mDatas =  new ArrayList<>();
    private RecyclerView mPersonalizedPlayListRecycler;
    private RecyclerView mHotDjRecycler;
    private DSHotDjAdapter mHotDjAdapter;
    private View rootView ;
    private DSPersonalizedPlayListAdapter mPersonalizedPlaylistAdapter;
    private List<DSPersonalizedPlayList.Result> mQueue = new ArrayList<>();
    private TextView mPlayListText;
    private List<DSHotDj.DjRadios> mList = new ArrayList<>();
    private List<DSNewSongs.Result> mNewSongs = new ArrayList<>();
    private CardView mCardView;
    private RecyclerView mNewSongsRecycler;
    private DSNewSongsAdapter mNewSongsAdapter;
    private DSTopList.Playlist mTopList = new DSTopList.Playlist();
    private static List<DSOrigin.Playlist.Tracks> mOriginList;
    private List<DSElectronic.Playlist.Tracks> mElectronicLiST;

    private List<DSDJ24hours.Data.List> m24hours = new ArrayList<>();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        EventBus.getDefault().register(this);
        onBannerEvent(new DSBannerEvent());
        onPersonalizedPlayListEvent(new DSPersonalizedPlayListEvent());
//        onPersonalizedPlayListEvent(new DSPersonalizedPlayListEvent());
//        onHotDjEvent(new DSHotDjEvent());
//        onNewSongs(new DSNewSongsEvent());
//        onTopListEvent(new DSTopListEvent());
//        onOriginEvent(new DSOriginEvent());
//        onElectronic(new DSElectronicEvent());
//        onDs24hours(new DSDj24hoursEvent());



    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public  void onBannerEvent(DSBannerEvent event){

        try {
            mDatas = HomeManager.getInstance().getBanner().getBanners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPersonalizedPlayListEvent(DSPersonalizedPlayListEvent event) {
        try {

            mQueue = HomeManager.getInstance().getPersonalizedPlayList().getResult();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
//

//

//
//



//    private void initView(ViewModel viewModel){
//        mOriginList = HomeManager.getInstance().getOrigin().getPlaylist().getTracks();
//        mTopList = HomeManager.getInstance().getmTopList().getPlaylist();
//        mList = HomeManager.getInstance().getHotDj().getDjRadios();
//        mQueue = HomeManager.getInstance().getPersonalizedPlayList().getResult();
//        mNewSongs = HomeManager.getInstance().getNewSongs().getResult();
//
//    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




//        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
////            // Restore value of members from saved state
//            mOriginList = (List<DSOrigin.Playlist.Tracks>) savedInstanceState.getSerializable("mOriginList");
        mQueue = (List<DSPersonalizedPlayList.Result>) savedInstanceState.getSerializable("mQueue");
//            mElectronicLiST = (List<DSElectronic.Playlist.Tracks>) savedInstanceState.getSerializable("mElectronicLiST");
            mDatas = (List<DSBannerModel.Banners>) savedInstanceState.getSerializable("mDatas");
//            mTopList = (DSTopList.Playlist) savedInstanceState.getSerializable("mTopList");
//            m24hours = (List<DSDJ24hours.Data.List>) savedInstanceState.getSerializable("m24hours");
////        } else {
////            // Probably initialize members with default values for a new instance
//
        }




    }

    public static DiscoveryFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootView = LayoutInflater.from(getContext()).inflate( R.layout.fragment_discovery,container,false);

        initBanner();
        initPersonalizedPlayList();
        initHotDj();
        initTopList();
        init24hours();
        return rootView;


        //更多使用方法仔细阅读文档，或者查看demo
    }



   private void  initBanner(){
       DSBannerAdapter mAdapter = new DSBannerAdapter(mDatas);
       Banner banner = rootView.findViewById(R.id.banner);
       banner.addBannerLifecycleObserver(this)//添加生命周期观察者
               .setAdapter( mAdapter)
               .setUserInputEnabled(true)
               .setIndicator(new CircleIndicator(rootView.getContext()))
                .setBannerRound(20);

    }

private void initPersonalizedPlayList(){
    mPlayListText = rootView.findViewById(R.id.personlized_playlist_title);
    mPlayListText.setText( mQueue.get((mQueue.size()-1)).getCopywriter());
    mPersonalizedPlayListRecycler = rootView.findViewById(R.id.ds_personalized_recycler);
    mPersonalizedPlayListRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false));
    mPersonalizedPlaylistAdapter = new DSPersonalizedPlayListAdapter(mQueue);
    mPersonalizedPlayListRecycler.setAdapter(mPersonalizedPlaylistAdapter);

}

private  void initHotDj(){
    mHotDjRecycler = rootView.findViewById(R.id.ds_dj_recycler);
    mHotDjRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false));
    mHotDjAdapter = new DSHotDjAdapter();
    mHotDjRecycler.setAdapter(mHotDjAdapter);

}


    public void  initTopList(){
        RecyclerView mRecyclerView = new RecyclerView(rootView.getContext());
        mRecyclerView = rootView.findViewById(R.id.ds_hotlist_viewpager);
        DSTopListAdapter mAdapter = new DSTopListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mAdapter);

    }



    public void init24hours(){
        RecyclerView m24hourRecycler  = rootView.findViewById(R.id.ds_24hours_recycler);
        m24hourRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        DSDJ24hoursAdapter m24hoursAdapter = new DSDJ24hoursAdapter();
        m24hourRecycler.setAdapter(m24hoursAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mQueue", (Serializable) mQueue);
//        outState.putSerializable("mNewSongs", (Serializable) mNewSongs);
//        outState.putSerializable("mOriginList", (Serializable) mOriginList);
        outState.putSerializable("mDatas", (Serializable) mDatas);
//        outState.putSerializable("mTopList", (Serializable) mTopList);
//        outState.putSerializable("m24hours", (Serializable) m24hours);


    }
}
