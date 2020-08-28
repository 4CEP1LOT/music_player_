package com.kotlin.musiclearning.Utils;

import android.content.Context;

import com.kotlin.lib_audio.MediaPlayer.utils.AllRequest;
import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_base.event.MlogEvent;
import com.kotlin.lib_base.event.SongDetailsEvent;
import com.kotlin.lib_base.event.SongEvent;
import com.kotlin.lib_base.event.VideoPlayEvent;
import com.kotlin.lib_base.event.discovery.DSBannerEvent;
import com.kotlin.lib_base.event.discovery.DSDj24hoursEvent;
import com.kotlin.lib_base.event.discovery.DSElectronicEvent;
import com.kotlin.lib_base.event.discovery.DSHotDjEvent;
import com.kotlin.lib_base.event.discovery.DSNewSongsEvent;
import com.kotlin.lib_base.event.discovery.DSOriginEvent;
import com.kotlin.lib_base.event.discovery.DSPersonalizedDJEvent;
import com.kotlin.lib_base.event.discovery.DSPersonalizedPlayListEvent;
import com.kotlin.lib_base.event.discovery.DSTopListEvent;
import com.kotlin.lib_base.event.mine.MinePersonalFmEvent;
import com.kotlin.lib_base.event.mine.MinePlayListEvent;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongUrl;

import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.MineLikedList;
import com.kotlin.lib_base.model.MlogModel;
import com.kotlin.lib_base.model.discoverymodel.DSBannerModel;
import com.kotlin.lib_base.model.discoverymodel.DSDJ24hours;
import com.kotlin.lib_base.model.discoverymodel.DSElectronic;
import com.kotlin.lib_base.model.discoverymodel.DSHotDj;
import com.kotlin.lib_base.model.discoverymodel.DSNewSongs;
import com.kotlin.lib_base.model.discoverymodel.DSOrigin;
import com.kotlin.lib_base.model.discoverymodel.DSPersonalizedDJ;
import com.kotlin.lib_base.model.discoverymodel.DSPersonalizedPlayList;
import com.kotlin.lib_base.model.discoverymodel.DSTopList;
import com.kotlin.lib_base.model.discoverymodel.MinePersonalFm;
import com.kotlin.lib_base.model.discoverymodel.MinePlayList;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;

import org.greenrobot.eventbus.EventBus;


public class RequestSuccessCollection implements DisposeDataListener {

    private static Context context;

    private AllRequest allRequest;

    private SongUrl mSongUrl;
    private SongDetails mSongDetails;

    private Rxjava rxjava;

    public Rxjava getRxjava() {
        return rxjava;
    }



    public static void initMlogModel (Object responseObj){
        MlogModel mlogModel = new MlogModel();
        mlogModel = (MlogModel) responseObj;
        HomeManager.getInstance().setMlogModel(mlogModel);
        EventBus.getDefault().post(new MlogEvent());
        EventBus.getDefault().post(new VideoPlayEvent());
    }


    public static void initDsBannerModel (Object responseObj){
        DSBannerModel mBanner = new DSBannerModel();
        mBanner = (DSBannerModel) responseObj;

        HomeManager.getInstance().setBanner(mBanner);
        EventBus.getDefault().post(new DSBannerEvent());

    }

    public static void initDSPersonalizedPlayList (Object responseObj){
        DSPersonalizedPlayList mPlayList = new DSPersonalizedPlayList();

        mPlayList = (DSPersonalizedPlayList) responseObj;

        HomeManager.getInstance().setPersonalizedPlayList(mPlayList);
        EventBus.getDefault().post(new DSPersonalizedPlayListEvent());

    }

    public static void initPersonalDJ (Object responseObj){
        DSPersonalizedDJ mPersonalizedDj = new DSPersonalizedDJ();

        mPersonalizedDj = (DSPersonalizedDJ) responseObj;
        HomeManager.getInstance().setPersonalizedDJ(mPersonalizedDj);
        EventBus.getDefault().post(new DSPersonalizedDJEvent());


    }

    public static void initmNewSongs (Object responseObj){
        DSNewSongs mNewSongs = new DSNewSongs();
        mNewSongs = (DSNewSongs) responseObj;
        HomeManager.getInstance().setNewSongs(mNewSongs);
        EventBus.getDefault().post(new DSNewSongsEvent());

    }

    public static void initHotDj (Object responseObj){
        DSHotDj mHotDj = new DSHotDj();
        mHotDj = (DSHotDj) responseObj;

        HomeManager.getInstance().setHotDj(mHotDj);
        EventBus.getDefault().post(new DSHotDjEvent());
    }


    public static void initTopList (Object responseObj){
        DSTopList mTopList = new DSTopList();

        mTopList = (DSTopList) responseObj;

        HomeManager.getInstance().setmTopList(mTopList);
        EventBus.getDefault().post(new DSTopListEvent());
    }


    public static void initOrigin (Object responseObj){
        DSOrigin mOrigin = new DSOrigin();

        mOrigin = (DSOrigin) responseObj;

        HomeManager.getInstance().setOrigin(mOrigin);
        EventBus.getDefault().post(new DSOriginEvent());
    }

    public static void initElectronic (Object responseObj){
        DSElectronic mElectronic = new DSElectronic();

        mElectronic = (DSElectronic) responseObj;

        HomeManager.getInstance().setmElectronic(mElectronic);
        EventBus.getDefault().post(new DSElectronicEvent());
    }

    public static void init24hours (Object responseObj){
        DSDJ24hours m24hours = new DSDJ24hours();

        m24hours = (DSDJ24hours) responseObj;

        HomeManager.getInstance().setmDJ24hours(m24hours);
        EventBus.getDefault().post(new DSDj24hoursEvent());
    }


    public static void initPersonalFm (Object responseObj){
        MinePersonalFm mPersonalFm = new MinePersonalFm();

        mPersonalFm = (MinePersonalFm) responseObj;

        HomeManager.getInstance().setmMinePersonalFm(mPersonalFm);
        EventBus.getDefault().post(new MinePersonalFmEvent());
    }

    public static void initMinePlayList (Object responseObj){
        MinePlayList mMinePlayList = new MinePlayList();
        mMinePlayList = (MinePlayList) responseObj;

        HomeManager.getInstance().setmMinePlayList(mMinePlayList);


        EventBus.getDefault().post(new MinePlayListEvent());
    }

    public static void initSongDetail (Object responseObj){
        SongDetails mSongDetails = new SongDetails();
        mSongDetails = (SongDetails) responseObj;

        SongManager.getInstance().setmSongDetails(mSongDetails);
        EventBus.getDefault().post(new SongDetailsEvent());


    }
    public static void initSongUrl (Object responseObj){
        SongUrl mSongUrl = new SongUrl();
        mSongUrl = (SongUrl) responseObj;

        SongManager.getInstance().setmSongUrl(mSongUrl);
        EventBus.getDefault().post(new SongEvent());

    }
    public static void initMineList (Object responseObj){
        MineLikedList mineLikedList = new MineLikedList();
        mineLikedList = (MineLikedList) responseObj;
        HomeManager.getInstance().setmMineLikedList(mineLikedList);
        EventBus.getDefault().post(new com.kotlin.lib_base.event.mine.MinePlayListEvent());

    }




    @Override
    public void onSuccess(Object responseObj) {
        if (responseObj.getClass().equals(DSDJ24hours.class)) {
            RequestSuccessCollection.init24hours(responseObj);

        } else if (responseObj.getClass().equals(DSBannerModel.class)) {
            RequestSuccessCollection.initDsBannerModel(responseObj);

        } else if (responseObj.getClass().equals(DSPersonalizedPlayList.class)) {
            RequestSuccessCollection.initDSPersonalizedPlayList(responseObj);

        } else if (responseObj.getClass().equals(DSElectronic.class)) {
            RequestSuccessCollection.initElectronic(responseObj);

        } else if (responseObj.getClass().equals(DSHotDj.class)) {
            RequestSuccessCollection.initHotDj(responseObj);


        } else if (responseObj.getClass().equals(MinePlayList.class)) {
            RequestSuccessCollection.initMinePlayList(responseObj);
            RequestCenter.RequestLikedPlayList(this,((MinePlayList) responseObj).getPlaylist());


        } else if (responseObj.getClass().equals(MlogModel.class)) {
            RequestSuccessCollection.initMlogModel(responseObj);

        } else if (responseObj.getClass().equals(MinePersonalFm.class)) {
            RequestSuccessCollection.initPersonalFm(responseObj);

        } else if (responseObj.getClass().equals(DSTopList.class)) {
            RequestSuccessCollection.initTopList(responseObj);

        } else if (responseObj.getClass().equals(DSOrigin.class)) {
            RequestSuccessCollection.initOrigin(responseObj);

        } else if (responseObj.getClass().equals(DSNewSongs.class)) {
            RequestSuccessCollection.initmNewSongs(responseObj);

        } else if (responseObj.getClass().equals(DSPersonalizedDJ.class)) {
            RequestSuccessCollection.initPersonalDJ(responseObj);

        }  else if (responseObj.getClass().equals(SongDetails.class)) {
            RequestSuccessCollection.initSongDetail(responseObj);

        }else if(responseObj.getClass().equals(SongUrl.class)) {
            RequestSuccessCollection.initSongUrl(responseObj);

        }else if(responseObj.getClass().equals(MineLikedList.class)) {
            RequestSuccessCollection.initMineList(responseObj);


        }


    }

    @Override
    public void onFailure(Object responseObj) {

    }
}


