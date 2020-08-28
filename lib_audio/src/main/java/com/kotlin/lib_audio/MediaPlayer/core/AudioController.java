package com.kotlin.lib_audio.MediaPlayer.core;



import com.kotlin.lib_audio.MediaPlayer.event.AudioCompletionEvent;
import com.kotlin.lib_audio.MediaPlayer.event.AudioErrorEvent;
import com.kotlin.lib_audio.MediaPlayer.exception.AudioQueueEmptyException;
import com.kotlin.lib_base.event.SongDetailsEvent;
import com.kotlin.lib_base.event.SongEvent;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 控制播放逻辑类
 */
public class AudioController  {

    public enum PlayMode{
        /**
         * 列表循环
         */
        LOOP,
        /**
         * 随机
         */
        RANDOM,
        /**
         *单曲循环
         */
        REPEAT,
    }

    /**
     * 播放逻辑的单例方法
     * @return
     */
     private AudioPlayer mAudioPlayer;                            //播放器
     private PlayMode mPlayMode;                                 //播放模式
     private int AudioQueueIndex;                               //播放歌曲在播放列表的下标
    private SongUrl.Data mDetailDatas;
    private List<SongDetails.Songs> mList;
    private List<SongUrl.Data> mDatas;

    private AudioController() {
        EventBus.getDefault().register(this);
        mAudioPlayer = new AudioPlayer();
        mDetailDatas = new SongUrl.Data();
        mDatas = new ArrayList<>();
        AudioQueueIndex = 0;
        mPlayMode = PlayMode.LOOP;
        mList = new ArrayList<>();
//        SongUrlListEvent(new SongEvent());
//        SongDetails(new SongDetailsEvent());
    }
    private void addCustomAudio(int index, SongUrl.Data bean,SongDetails.Songs data) {
        if (mDatas == null && mList ==null) {
            throw new AudioQueueEmptyException("当前播放队列为空,请先设置播放队列.");
        }
        mDatas.add(index,bean);                                                    //如果为空的话添加歌曲下标和歌曲信息
        mList.add(index,data);
    }
    public AudioPlayer getmAudioPlayer() {
        return mAudioPlayer;
    }

    /**
     * 设置播放队列
     * @param queue
     */

    /**
     * 在设置播放队列的同时设置将要播放的 歌曲
     * @param queue
     * @param queueIndex
     */
    public void setmQueue(List<SongUrl.Data> queue,List<SongDetails.Songs> data ,int queueIndex) {
        mDatas.addAll(queue);
        mList.addAll(data);
        AudioQueueIndex = queueIndex;
    }

    public List<SongUrl.Data> getmQueue() {
        return mDatas == null ?  new ArrayList<SongUrl.Data>() : mDatas;                   //这样外面的业务层不需要判断是否为空
    }


    public void clear(){
        mDatas.clear();
    }


    public static AudioController getInstance(){return AudioController.SingletonHolder.instance;}


    private static class SingletonHolder{
        private static AudioController instance = new AudioController();
    }                        //单例


    private int queryAudio (SongUrl.Data bean){
        return mDatas.indexOf(bean);
    }

    /**
     * 直接从头添加歌曲
     * @param audioBean 歌曲的缓存库
     */
    public void addAudio(SongUrl.Data audioBean,SongDetails.Songs data){
            this.addAudio(0,audioBean,data);
    }

    public void addAudio(int index,SongUrl.Data bean,SongDetails.Songs data) {
        if (mDetailDatas == null) {
            throw new AudioQueueEmptyException("播放队列为空");
        } else {
            int query = queryAudio(bean);
            if (query <= 1){
                addCustomAudio(index,bean,data);                             //没有添加过t添加进去
                setPlayindex(index);
            }else {
                SongUrl.Data currentBean = getNowPlaying();
                SongDetails.Songs currentSong = getNowDetails();
                if (!bean.equals(currentBean.getId())&&(data.equals(currentSong.getId()))){                         //已经添加过但不在播放列表中
                setPlayindex(query);
                }
            }

        }
    }





    public int getAudioQueueIndex() {
        return AudioQueueIndex;
    }

    public PlayMode getmPlayMode() {
        return mPlayMode;
    }

    //对外提供设置播放模式

    public void setmPlayMode(PlayMode mPlayMode) {
        this.mPlayMode = mPlayMode;
    }

    public void setmAudioPlayer(AudioPlayer mAudioPlayer) {
        this.mAudioPlayer = mAudioPlayer;
    }

    public void setAudioQueueIndex(int audioQueueIndex) {
        AudioQueueIndex = audioQueueIndex;
    }

    private CustomMediaPlayer.Status getStatus(){
        return mAudioPlayer.getStatus();
    }
    //对外提供是否在播放中的状态
    public boolean isStartState(){
        return CustomMediaPlayer.Status.STARTED == getStatus();
    }
    public boolean isPauseState(){
        return CustomMediaPlayer.Status.PAUSED == getStatus();

    }

    public boolean isStoppedState(){
        return CustomMediaPlayer.Status.STOPPED == getStatus();

    }
    /**
     * 对外提供获取当前播放时间
     */
    public int getNowPlayTime() {
        return mAudioPlayer.getCurrentPosition();
    }

    /**
     * 对外提供获取总播放时间
     */
    public int getTotalPlayTime() {
        return mAudioPlayer.getDuration();
    }



    /**
     * 如果当前播放的歌曲不在数据库内，添加收藏，否则取消收藏
     * @return
     */
//    public void onFavEvent(){
//        if (GreenDaoHelper.QueryFavourite(getNowPlaying())!=null){
//            // 取消收藏
//            GreenDaoHelper.RemoveFavorite(getNowPlaying());
//            EventBus.getDefault().post(new AudioFavEvent(false));
//        }else {
//            //添加收藏
//            GreenDaoHelper.addFavorite(getNowPlaying());
//            EventBus.getDefault().post(new AudioFavEvent(true));
//        }
//    }
//



    public void setPlayindex(int index){                                                            //s设置歌曲播放下标
        if (mDetailDatas == null)
            throw new AudioQueueEmptyException("当前播放列表为空，请先设置歌曲列表");
        else {
            AudioQueueIndex = index;                                                                //当前下标等于播放队列的下表时
            play();                                                                                 //播放歌曲
        }

    }

    public void play() {
        SongUrl.Data bean = getPlaying(AudioQueueIndex);
        SongDetails.Songs data = getDetails(AudioQueueIndex);
        load(bean,data);

    }
    /*8
    //如果mQueue彻底不为空，且AudioQueueIndex大于等于0,通过get方法定位到当前要播放的歌曲
     */
    private void load(SongUrl.Data url, SongDetails.Songs details){
        mAudioPlayer.load(url,details);
    }



    public void pause() {
        mAudioPlayer.pause();

    }

    public void resume() {
        mAudioPlayer.resume();
    }

    public void release() {
        mAudioPlayer.release();
        EventBus.getDefault().unregister(this);
    }

    /*
     * 播放下一首
     * 在AudioBean里面取出歌曲信息
     */

    public void next(){
        SongUrl.Data bean = getNextPlaying();
        SongDetails.Songs details = getNextDetails();
        load(bean,details);
    }

    /**
     * 播放上一首
     * @return
     */

    public void previous(){
        SongUrl.Data bean = getPrePlaying();
        SongDetails.Songs details = getPreDetails();
        load(bean,details);

    }
    public SongUrl.Data getPlaying(int index) {
        if (mDatas != null && !mDatas.isEmpty() && AudioQueueIndex >= 0 ){
            return mDatas.get(index);

        }else{
            throw  new AudioQueueEmptyException("当前播放l列表为空，请先设置播放列表");

        }




    }
    public SongDetails.Songs getDetails(int index) {
        if (mList != null && !mList.isEmpty() && AudioQueueIndex >= 0){
            return mList.get(index);
        }else{
            throw  new AudioQueueEmptyException("当前播放l列表为空，请先设置播放列表");

        }




    }

    /**
     * 播放前一首歌曲
     * @return
     */

    private SongUrl.Data getPrePlaying() {
        switch (mPlayMode){
            case LOOP:
                AudioQueueIndex = AudioQueueIndex - 1 % mDatas.size();                                                      //为了保证数组不越界取余数作为Index的值
                break;
            case RANDOM:
                AudioQueueIndex = new Random().nextInt(mDatas.size()) % mDatas.size();                                      //随机生成<=数组长度的Index值,然后取余保证不越界
                break;
            case REPEAT:
                break;
        }
        return getPlaying(AudioQueueIndex);
    }

    private SongDetails.Songs getPreDetails() {
        switch (mPlayMode){
            case LOOP:
                AudioQueueIndex = AudioQueueIndex - 1 % mList.size();                                                      //为了保证数组不越界取余数作为Index的值
                break;
            case RANDOM:
                AudioQueueIndex = new Random().nextInt(mList.size()) % mList.size();                                      //随机生成<=数组长度的Index值,然后取余保证不越界
                break;
            case REPEAT:
                break;
        }
        return getDetails(AudioQueueIndex);
    }

    private SongUrl.Data getNextPlaying() {
        switch (mPlayMode){
            case LOOP:
                AudioQueueIndex = AudioQueueIndex + 1 % mDatas.size();
                break;
            case RANDOM:
                AudioQueueIndex = new Random().nextInt(mDatas.size()) % mDatas.size();
                break;
            case REPEAT:
                break;
        }
        return getPlaying(AudioQueueIndex);

    }

    private SongDetails.Songs getNextDetails() {
        switch (mPlayMode){
            case LOOP:
                AudioQueueIndex = AudioQueueIndex + 1 % mList.size();
                break;
            case RANDOM:
                AudioQueueIndex = new Random().nextInt(mList.size()) % mList.size();
                break;
            case REPEAT:
                break;
        }
        return getDetails(AudioQueueIndex);

    }

    public SongUrl.Data getNowPlaying() {
        return getPlaying(AudioQueueIndex);
    }
  public SongDetails.Songs getNowDetails() {
        return getDetails(AudioQueueIndex);
    }

    /**
     * 自动切换播放/暂停
     * @return
     */
    public void playOrPause(){
        if (isStartState()){
            pause();
        }else if(isPauseState()){
            play();
        }
        //return getNowPlaying();
    }



    public int getPlayindex(){
        return AudioQueueIndex;

    }


    @Subscribe (threadMode = ThreadMode.POSTING)
    public void SongUrlListEvent(SongEvent event){
        try {
            mDatas = SongManager.getInstance().getmSongUrl().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Subscribe (threadMode = ThreadMode.POSTING)
    public void SongDetails(SongDetailsEvent event){
        try {
            mList = SongManager.getInstance().getmSongDetails().getSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Subscribe (threadMode = ThreadMode.POSTING)
     public void onAudioCompleteEvent(AudioCompletionEvent event){
        next();
       }

       @Subscribe(threadMode = ThreadMode.POSTING)
       public void onAudioErrorEvent(AudioErrorEvent event){
        next();
       }
}
