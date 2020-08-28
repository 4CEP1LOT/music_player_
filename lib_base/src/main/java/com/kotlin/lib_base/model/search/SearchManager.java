package com.kotlin.lib_base.model.search;


import com.kotlin.lib_base.surface.audio.BaseModel;

public class SearchManager extends BaseModel {
    private SearchTrend mSearchTrend;
    private SearchSong mSearchSong;
    private static SearchManager mInstance;
    private SearchAlbum mAlbum;
    private SearchVideo mSearchVideo;
    private SearchSinger mSearchSinger;

    private SearchAll mSearchaAll;

    public SearchAll getmSearchAll() {
        return mSearchAll;
    }

    public void setmSearchAll(SearchAll mSearchAll) {
        this.mSearchAll = mSearchAll;
    }

    public SearchPlayList getSearchPlayList() {
        return mSearchPlayList;
    }

    public void setSearchPlayList(SearchPlayList mSearchPlayList) {
        this.mSearchPlayList = mSearchPlayList;
    }

    SearchPlayList mSearchPlayList;

SearchAll getSearchAll() {
        return mSearchAll;
    }

    public void setSearchAll(SearchAll mSearchAll) {
        this.mSearchAll = mSearchAll;
    }

    private SearchAll mSearchAll;


    public static  SearchManager getInstance(){
        if(mInstance == null){
            synchronized (SearchManager.class){                       //字节码锁，因为我们这个类的字节码锁是唯一的
                if(mInstance == null){
                    mInstance = new SearchManager();
                }

            }
        }
        return mInstance;
    }


    /**
     * 保存搜索结果到内存
     * @param searchSong      user为搜索结果
     *
     */
    public void saveSearchSong(SearchSong searchSong){
        mSearchSong = searchSong;
        saveLocal(searchSong);
    }

    /**
     * 保存到本地数据库，持久化信息
     * @param searchSong
     */
    private void saveLocal (SearchSong searchSong){

    }

    /**
     * 获取搜索结果
     * @return
     */

    public SearchTrend getSearchTrend(){
        return mSearchTrend;
    }

    public void setSearchTrend(SearchTrend searchTrend) {
        this.mSearchTrend = searchTrend;
    }

    /**
     * 从本地获取
     * @return
     */


    public boolean hasSearched(){
        return getSearchSong() != null;
    }

    /**
     * 删除搜索结果
     */
    public void removeSearchTrend(){
        mSearchTrend = null;
        removeLocal();
    }


    /**
     * 保存搜索结果到内存
     *
     */
    public void saveSearchTrend(SearchTrend searchTrend){
        mSearchTrend = searchTrend;
        saveLocal(searchTrend);
    }

    /**
     * 保存到本地数据库，持久化信息
     */
    private void saveLocal (SearchTrend searchTrend){

    }

    /**
     * 获取搜索结果
     * @return
     */

    public SearchSong getSearchSong(){
        return mSearchSong;
    }

    public void setSearchSong(SearchSong searchSong) {
        this.mSearchSong = searchSong;
    }

    /**
     * 从本地获取
     * @return
     */

    private SearchSong getLocal(){
        return  null;
    }

    /**
     * 判断是否搜索过
     * @return
     */


    /**
     * 删除搜索结果
     */
    public void removeSearchSong(){
        mSearchSong = null;
        removeLocal();
    }

    public void saveSearchAlbum(SearchAlbum Album){
        mAlbum = Album;
        saveAlbumLocal(mAlbum);
    }


    private void removeLocal() {


    }  
    
    private void saveAlbumLocal (SearchAlbum mAlbum){

    }

    /**
     * 获取搜索结果
     * @return
     */



    public void setSearchAlbum(SearchAlbum mSearchAlbum) {
        this.mAlbum = mSearchAlbum;
    }

    /**
     * 从本地获取
     * @return
     */

    private SearchAlbum getSearchAlbumLocal(){
        return  null;
    }




    /**
     * 获取搜索结果
     * @return
     */

    public SearchAlbum getSearchAlbum(){
        return mAlbum;
    }




    private void saveSingerLocal (SearchSinger mSinger){

    }

    /**
     * 获取搜索结果
     * @return
     */

    public SearchSinger getSearchSinger(){
        return mSearchSinger;
    }

    public void setSearchSinger(SearchSinger mSinger) {
        this.mSearchSinger = mSinger;
    }

    /**
     * 从本地获取
     * @return
     */

    private SearchAlbum getSearchSingerLocal(){
        return  null;
    }




    /**e
     * 获取搜索结果
     * @return
     */


    public SearchVideo getSearchVideo() {
        return mSearchVideo;
    }

    public void setSearchVideo(SearchVideo mSearchVideo) {
        this.mSearchVideo = mSearchVideo;
    }

    public void saveVideoLocal(SearchVideo mSearchVideo){

    }
}
