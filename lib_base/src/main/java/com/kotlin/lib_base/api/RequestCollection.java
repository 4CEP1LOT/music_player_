package com.kotlin.lib_base.api;

import android.util.Log;

import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongUrl;
import com.kotlin.lib_base.model.search.SearchAlbum;
import com.kotlin.lib_base.model.search.SearchAll;
import com.kotlin.lib_base.model.search.SearchPlayList;
import com.kotlin.lib_base.model.search.SearchSinger;
import com.kotlin.lib_base.model.search.SearchSong;
import com.kotlin.lib_base.model.search.SearchVideo;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.lib_connection.okHttp.request.RequestParams;

import com.kotlin.lib_base.model.MineDetailUser;
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
import com.kotlin.lib_base.model.user.User;
import com.kotlin.lib_base.model.user.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class RequestCollection {

    //type: 搜索类型；默认为 1 即单曲 , 取值意义 : 1: 单曲, 10: 专辑, 100: 歌手, 1000: 歌单, 1002: 用户, 1004: MV, 1006: 歌词, 1009: 电台, 1014: 视频, 1018:综合

    private static String mSongs = "1";
    private static String mAlbum = "10";
    private static String mSinger = "100";
    private static String mVideo = "1014";
    private static String mAll = "1018";
    private static String mPlayList = "1000";
    private static String ids ;
    private static String ids2;
    private static List<MinePersonalFm.Data> mPersonalFm = new ArrayList<>();
    private static List<MinePlayList.Playlist> mCreatedPlayList = new ArrayList<>();

    private CountDownLatch mCountDownLatch;
    private DisposeDataListener listener;



    public static void RequestLogin(DisposeDataListener listener,String username,String password){
        RequestParams params = new RequestParams();
        params.put("phone",username);
        params.put("password",password);
        RequestCenter.getRequest(RequestCenter.HttpConstants.LOGIN,params,listener, User.class);
        Log.d("login","Request");

    }

    public static void RequestSearchSongs(DisposeDataListener listener,String mNewText){
        RequestParams params = new RequestParams();
        params.put("keywords", mNewText);
        params.put("type",mSongs);
        RequestCenter.getRequest(RequestCenter.HttpConstants.SEARCH,params,listener, SearchSong.class);

    }

    public static void RequestSearchAlbum(DisposeDataListener listener,String mNewText){
        RequestParams params = new RequestParams();
        params.put("keywords", mNewText);
        params.put("type",mAlbum);
        RequestCenter.getRequest(RequestCenter.HttpConstants.SEARCH,params,listener, SearchAlbum.class);

    }

    public static void RequestSearchSinger(DisposeDataListener listener,String mNewText){
        RequestParams params = new RequestParams();
        params.put("keywords", mNewText);
        params.put("type",mSinger);
        RequestCenter.getRequest(RequestCenter.HttpConstants.SEARCH,params,listener, SearchSinger.class);

    }

    public static void RequestSearchVideo(DisposeDataListener listener,String mNewText){
        RequestParams params = new RequestParams();
        params.put("keywords", mNewText);
        params.put("type",mVideo);
        RequestCenter.getRequest(RequestCenter.HttpConstants.SEARCH,params,listener, SearchVideo.class);

    }
    public static void RequestAll(DisposeDataListener listener,String mNewText){
        RequestParams params = new RequestParams();
        params.put("keywords", mNewText);
        params.put("type",mAll);
        RequestCenter.getRequest(RequestCenter.HttpConstants.SEARCH,params,listener, SearchAll.class);

    }
    public static void searchPlayList(DisposeDataListener listener,String mNewText){
        RequestParams params = new RequestParams();
        params.put("keywords", mNewText);
        params.put("type",mPlayList);
        RequestCenter.getRequest(RequestCenter.HttpConstants.SEARCH,params,listener, SearchPlayList.class);

    }
    public static void RequestLikedPlayList(DisposeDataListener listener,List<MinePlayList.Playlist> createdPlayList){
        RequestParams params = new RequestParams();
        params.put("id",String.valueOf(createdPlayList.get(0).getId()));
        RequestCenter.getRequest(RequestCenter.HttpConstants.LIST,params,listener, MineLikedList.class);

    }


    public static void RequestRecommend(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.VIDEO_RECOMMEND,listener, MlogModel.class);

    }

    public static void RequestHotSinger(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.TOP_ARTIST,listener, DSTopList.class);

    }
    public static void RequestHotDJ(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.HOT_DJ,listener, DSHotDj.class);

    }

    public static void RequestPersonalizedPlayList(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.PERSONALIZED,listener, DSPersonalizedPlayList.class);

    }

    public static void RequestNewSongs(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.PERSONALIZED_NEW_SONG,listener, DSNewSongs.class);

    }
    public static void RequestDSBanner(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.BANNER,listener, DSBannerModel.class);

    }



        public static void RequestDSTopList(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.TOP_LIST,listener, DSTopList.class);

    }

            public static void DSPersonalizedDJ(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.PERSONALIZED_DJ_PROGRAM,listener, DSPersonalizedDJ.class);

    }


    public static void DSOriginList(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.ORIGIN_LIST,listener, DSOrigin.class);

    }
    public static void DSElectronicList(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.ELECTRONIC_LIST,listener, DSElectronic.class);

    }

    public static void DS24hourDj(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.HOURS_24_DJ,listener, DSDJ24hours.class);

    }

    public static void MinePersonFM(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.PERSONAL_FM,listener, MinePersonalFm.class);

    }

    public static void MineLikedSong(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.LIKED_SONG,listener, MinePersonalFm.class);

    }

    public static void MineCreatedPlayList(DisposeDataListener listener){

        RequestCenter.getSimpleRequest(RequestCenter.HttpConstants.CREATE_PLAYLIST,listener, MinePlayList.class);

    }

    public static void MineDetailedUser(DisposeDataListener listener){
        RequestParams params = new RequestParams();

        params.put("uid", String.valueOf(UserManager.getInstance().getUser().getProfile().getUserId()));

        RequestCenter.getRequest(RequestCenter.HttpConstants.DETAIL_USER,params,listener, MineDetailUser.class);

    }

    public static void SongUrl(DisposeDataListener listener,Long data)  {
            ids = String.valueOf(data);


//        }else {
//            ids = String.valueOf(HomeManager.getInstance().getmMinePersonalFm().getData().get(index+1).getId());
//
//        }

        RequestCenter.getSongRequest(RequestCenter.HttpConstants.SONG_URL, ids, listener, SongUrl.class);
    }


        public static void SongDetails(DisposeDataListener listener,Long data) {

            ids2 = String.valueOf(data);



        RequestCenter.getSongRequest(RequestCenter.HttpConstants.SONG_DETAILS,ids2,listener, SongDetails.class);

    }

        public static void SongPlaylistDetails(DisposeDataListener listener, List<?>data) {

        RequestCenter.getSongDetailPlayList(RequestCenter.HttpConstants.SONG_DETAILS,data,listener, SongDetails.class);

    }

    public static void SongPlaylistUrl(DisposeDataListener listener,List<?>data) {

        RequestCenter.getSongDetailPlayList(RequestCenter.HttpConstants.SONG_URL,data,listener, SongUrl.class);

    }








}
