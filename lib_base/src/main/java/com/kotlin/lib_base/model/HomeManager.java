package com.kotlin.lib_base.model;

import com.kotlin.lib_base.model.audio.SongUrl;
import com.kotlin.lib_base.model.discoverymodel.DSBannerModel;
import com.kotlin.lib_base.model.discoverymodel.DSDJ24hours;
import com.kotlin.lib_base.model.discoverymodel.DSElectronic;
import com.kotlin.lib_base.model.discoverymodel.DSHotDj;
import com.kotlin.lib_base.model.discoverymodel.DSOrigin;
import com.kotlin.lib_base.model.discoverymodel.DSTopList;
import com.kotlin.lib_base.model.discoverymodel.DSNewSongs;
import com.kotlin.lib_base.model.discoverymodel.DSPersonalizedDJ;
import com.kotlin.lib_base.model.discoverymodel.DSPersonalizedPlayList;
import com.kotlin.lib_base.model.discoverymodel.MinePersonalFm;
import com.kotlin.lib_base.model.discoverymodel.MinePlayList;

public class HomeManager {

    private static HomeManager mInstance;

    private MlogModel mlogModel;



    private MinePersonalFm mMinePersonalFm;

    private DSBannerModel mBanner;

    private DSHotDj mHotDj;

    private MinePlayList mMinePlayList;

    private DSTopList mTopList;

    private DSPersonalizedDJ mPersonalizedDJ;

    private DSPersonalizedPlayList mPersonalizedPlayList;

    private MineDetailUser mDetailUser;
    private MineLikedList mMineLikedList;

    public MinePlayList getmMinePlayList() {
        return mMinePlayList;
    }

    public void setmMinePlayList(MinePlayList mMinePlayList) {
        this.mMinePlayList = mMinePlayList;
    }

    public DSDJ24hours getmDJ24hours() {
        return mDJ24hours;
    }

    public void setmDJ24hours(DSDJ24hours mDJ24hours) {
        this.mDJ24hours = mDJ24hours;
    }

    private DSDJ24hours mDJ24hours;

    private SongUrl mSongUrl;

    public SongUrl getmSongUrl() {
        return mSongUrl;
    }

    public void setmSongUrl(SongUrl mSongUrl) {
        this.mSongUrl = mSongUrl;
    }

    private DSNewSongs mNewSongs;

    private DSOrigin mOrigin;


    private DSElectronic mElectronic;

    public DSHotDj getHotDj() {
        return mHotDj;
    }






    public MineLikedList getmMineLikedList() {
        return mMineLikedList;
    }

    public void setmMineLikedList(MineLikedList mMineLikedList) {
        this.mMineLikedList = mMineLikedList;
    }

    public static HomeManager getInstance(){
        if(mInstance == null){
            synchronized (HomeManager.class){                       //字节码锁，因为我们这个类的字节码锁是唯一的
                if(mInstance == null){
                    mInstance = new HomeManager();
                }

            }
        }
        return mInstance;
    }
    public MineDetailUser getmDetailUser() {
        return mDetailUser;
    }

    public void setmDetailUser(MineDetailUser mDetailUser) {
        this.mDetailUser = mDetailUser;
    }

    public MinePersonalFm getmMinePersonalFm() {
        return mMinePersonalFm;
    }

    public void setmMinePersonalFm(MinePersonalFm mMinePersonalFm) { this.mMinePersonalFm = mMinePersonalFm; }

    public DSElectronic getmElectronic() {
        return mElectronic;
    }

    public void setmElectronic(DSElectronic mElectronic) {
        this.mElectronic = mElectronic;
    }


    public MlogModel getMlogModel() {
        return mlogModel;
    }

    public void setMlogModel(MlogModel mlogModel) {
        this.mlogModel = mlogModel;
    }

    public void setBanner(DSBannerModel mBanner) {
        this.mBanner = mBanner;
    }

    public DSBannerModel getBanner() {
        return mBanner;
    }


    public DSHotDj gemHotDj() {
        return mHotDj;
    }

    public void setHotDj(DSHotDj mHotDj) {
        this.mHotDj = mHotDj;
    }

    public DSNewSongs getNewSongs() {
        return mNewSongs;
    }

    public DSOrigin getOrigin() {
        return mOrigin;
    }

    public void setOrigin(DSOrigin mOrigin) {
        this.mOrigin = mOrigin;
    }

    public void setNewSongs(DSNewSongs mNewSongs) {
        this.mNewSongs = mNewSongs;
    }


    public DSPersonalizedDJ getPersonalizedDJ() {
        return mPersonalizedDJ;
    }

    public void setPersonalizedDJ(DSPersonalizedDJ mPersonalizedDJ) {
        this.mPersonalizedDJ = mPersonalizedDJ;
    }

    public DSPersonalizedPlayList getPersonalizedPlayList() {
        return mPersonalizedPlayList;
    }

    public void setPersonalizedPlayList(DSPersonalizedPlayList mPersonalizedPlayList) {
        this.mPersonalizedPlayList = mPersonalizedPlayList;
    }
    public DSTopList getmTopList() {
        return mTopList;
    }

    public void setmTopList(DSTopList mTopList) {
        this.mTopList = mTopList;
    }
}

