package com.kotlin.lib_audio.MediaPlayer.utils;

import com.kotlin.lib_base.model.audio.SongCollection;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongUrl;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class AllRequest {
      SongDetails.Songs   mSongs;
        SongUrl.Data mDatas;
        SongCollection mCollection;

    public AllRequest(final SongDetails.Songs mSongs, final SongUrl.Data mDatas) {

        this.mSongs = mSongs;
        this.mDatas = mDatas;



        Observable.zip(SongDetailsRequest(mSongs), SongUrlRequest(mDatas), new BiFunction<SongDetails.Songs, SongUrl.Data, Object>() {
            @Override
            public Object apply(SongDetails.Songs songs, SongUrl.Data data) throws Exception {
                return new SongManager();
            }
        }).subscribe(new Observer<SongManager>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SongManager songManager) {
//                AudioController.getInstance().setmQueue(SongManager.getInstance().getmSongUrl().getData());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });





    }
    public Observable SongDetailsRequest(SongDetails.Songs songs){

        Observable<SongDetails.Songs> observable1 = Observable.create(new ObservableOnSubscribe<SongDetails.Songs>() {
            @Override
            public void subscribe(ObservableEmitter<SongDetails.Songs> e) throws Exception {
                e.onNext(mSongs);
            }
        });

        return observable1;
    }


    public Observable SongUrlRequest(SongUrl.Data  data){

        Observable<SongUrl.Data> observable2 = io.reactivex.Observable.create(new ObservableOnSubscribe<SongUrl.Data>() {
            @Override
            public void subscribe(ObservableEmitter<SongUrl.Data> e) throws Exception {
                e.onNext(mDatas);
            }
        });
        return observable2;
    }
}