package com.kotlin.lib_base.api;

import android.util.Log;

import com.kotlin.lib_base.model.search.SearchTrend;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataHandle;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.lib_connection.okHttp.request.CommonOkHttpClient;
import com.kotlin.lib_connection.okHttp.request.CommonRequest;
import com.kotlin.lib_connection.okHttp.request.RequestParams;
import com.kotlin.lib_base.model.BaseFriendModel;
import com.kotlin.lib_base.model.discoverymodel.MinePlayList;

import java.util.List;


/**
 * 业务层如何调用网络框架，请求和接口的集合
 */

public class RequestCenter {
    static class HttpConstants{
        private static final String ROOT_URL = "http://192.168.1.5:3000";

        private static final String HOME_RECOMMAND = ROOT_URL + "/module_voice/home_recommand";

        private static final String HOME_FRIEND = ROOT_URL + "/module_voice/home_recommand_more ";

        private static final String HOME_RECOMMAND_MORE =ROOT_URL + "/module_voice/home_friend";

        public static final String LOGIN = ROOT_URL + "/login/cellphone";

        public static final String SEARCH = ROOT_URL + "/search";

        public static final String HOT_DJ = ROOT_URL + "/dj/hot";

        public static final String VIDEO_RECOMMEND = ROOT_URL + "/video/timeline/recommend";

        public static final String SEARCH_TREND = ROOT_URL + "/search/hot/detail";

        public static final String TOP_ARTIST = ROOT_URL + "/top/artist";

        public static final String PERSONALIZED = ROOT_URL + "/personalized";

        public static final String TOP_LIST = ROOT_URL + "/playlist/detail?id=19723756";

        public static final String LIST = ROOT_URL + "/playlist/detail";

        public static final String ORIGIN_LIST = ROOT_URL + "/playlist/detail?id=2884035";

        public static final String ELECTRONIC_LIST = ROOT_URL + "/playlist/detail?id=1978921795";

        public static final String BANNER = ROOT_URL + "/banner?type=2";

        public static final String PERSONALIZED_NEW_SONG = PERSONALIZED + "/newsong";

        public static final String PERSONALIZED_DJ_PROGRAM = PERSONALIZED + "/djprogram";

        public static final String HOURS_24_DJ = ROOT_URL + "/dj/program/toplist/hours";

        public static final String PERSONAL_FM = ROOT_URL + "/personal_fm";

        public static final String LIKED_SONG = ROOT_URL + "/likelist?=uid89773352";

        public static final String CREATE_PLAYLIST = ROOT_URL + "/user/playlist?uid=89773352";

        public static final String DETAIL_USER = ROOT_URL + "/user/detail";

        public static final String SONG_URL = ROOT_URL + "/song/url?id=";

        public static final String SONG_DETAILS = ROOT_URL + "/song/detail?ids=";














    }
    public static void getSongRequest(String url, String params, DisposeDataListener listener, Class<?> clazz){
        CommonOkHttpClient.get(CommonRequest.createSongGetRequest(url,params),new DisposeDataHandle(listener,clazz));               //
    }    //根据参数发送所以post请求
    public static void getSongDetailPlayList(String url, List<?> params, DisposeDataListener listener, Class<?> clazz){
        CommonOkHttpClient.get(CommonRequest.createGetPlaylistRequest(url,params),new DisposeDataHandle(listener,clazz));               //
    }    //根据参数发送所以post请求

    //根据参数发送所以post请求
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz){
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url,params),new DisposeDataHandle(listener,clazz));               //
    }    //根据参数发送所以post请求

    public static void getSimpleRequest(String url,  DisposeDataListener listener,Class<?> clazz){
        Log.d("getSimpleRequest","能收到");
        CommonOkHttpClient.get(CommonRequest.createSimpleGetRequest(url),new DisposeDataHandle(listener,clazz));               //
    }

//    public static void getSearchRequest(String url, RequestParams params, DisposeDataListener listener,Class<?> clazz){
//        CommonOkHttpClient.get(CommonRequest.(url,params),new DisposeDataHandle(listener,clazz));               //
//    }

    /**
     * 用户登录请求
     */
    public static void login (DisposeDataListener listener,String username,String password){
        RequestCollection.RequestLogin(listener,username,password);

    }

     public static void searchSong(final DisposeDataListener listener,String mNewText){

                     RequestCollection.RequestSearchSongs(listener,mNewText);

     }

     public static void searchTrend(DisposeDataListener listener){

         RequestCenter.getSimpleRequest(HttpConstants.SEARCH_TREND,listener, SearchTrend.class);

     }
    public static void searchAlbum( DisposeDataListener listener,String mNewText){

                    RequestCollection.RequestSearchAlbum(listener,mNewText);

    }
    public static void searchSinger( DisposeDataListener listener,String mNewText){

                    RequestCollection.RequestSearchSinger(listener,mNewText);

    }

    public static void searchAll( DisposeDataListener listener,String mNewText){

                    RequestCollection.RequestAll(listener,mNewText);

    }

    public static void searchVideo( DisposeDataListener listener,String mNewText){

                    RequestCollection.RequestSearchVideo(listener,mNewText);

    }

    public static void searchPlayList(final DisposeDataListener listener,String string){

                    RequestCollection.searchPlayList(listener,string);

    }
    public static void recommend(final DisposeDataListener listener){

        RequestCollection.RequestRecommend(listener);

    }

    public static void DSHotSinger(DisposeDataListener listener){
         RequestCollection.RequestHotSinger(listener);
    }

    public static void DSTopList(DisposeDataListener listener){
        RequestCollection.RequestDSTopList(listener);
    }
    public static void DSHotDJ(DisposeDataListener listener){
         RequestCollection.RequestHotDJ(listener);
    }

    public static void DSNewSongs(DisposeDataListener listener){
         RequestCollection.RequestNewSongs(listener);
    }

    public static void DSPersonalizedPlayList(DisposeDataListener listener){
         RequestCollection.RequestPersonalizedPlayList(listener);
    }

    public static void DSPersonalizedDJ(DisposeDataListener listener){
         RequestCollection.DSPersonalizedDJ(listener);
    }

    public static void MinePersonalizedFm(DisposeDataListener listener){
         RequestCollection.MinePersonFM(listener);
    }




    public static void DSBanner(DisposeDataListener listener){
         RequestCollection.RequestDSBanner(listener);
    }

    public static void DSOriginList(DisposeDataListener listener){
         RequestCollection.DSOriginList(listener);
    }


    public static void DSElectronicList(DisposeDataListener listener){
         RequestCollection.DSElectronicList(listener);
    }

    public static void DS24HourDj(DisposeDataListener listener){
         RequestCollection.DS24hourDj(listener);
    }

    public static void MineCreatedPlayList(DisposeDataListener listener){
         RequestCollection.MineCreatedPlayList(listener);
    }

    public static void MineDetailedUser(DisposeDataListener listener){
         RequestCollection.MineDetailedUser(listener);
    }

    public static void SongUrl(DisposeDataListener listener,Long data){
         RequestCollection.SongUrl(listener,data);
    }



    public static void SongDetails(DisposeDataListener listener,Long data){
         RequestCollection.SongDetails(listener,data);
    }

   public static void SongPlayListDetails(DisposeDataListener listener,List<?>data){

         RequestCollection.SongPlaylistDetails(listener,data);
    }
    public static void SongPlayListUrl(DisposeDataListener listener, List<?>data){

         RequestCollection.SongPlaylistUrl(listener,data);
    }


    public static void RequestLikedPlayList(DisposeDataListener listener,List<MinePlayList.Playlist> mCreatedPlayList){
         RequestCollection.RequestLikedPlayList(listener,mCreatedPlayList);
    }






    //朋友页面请求
     public static void requestFriendData(DisposeDataListener listener){
         RequestCenter.getRequest(HttpConstants.HOME_FRIEND,null,listener, BaseFriendModel.class);          //数据返回之后解析成BaseFriendModel
     }

}
