package com.kotlin.musiclearning.Utils;


import com.kotlin.lib_audio.app.AudioHelper;
import com.kotlin.lib_base.model.HomeManager;
import com.kotlin.lib_base.model.audio.SongDetails;
import com.kotlin.lib_base.model.audio.SongManager;
import com.kotlin.lib_base.model.audio.SongUrl;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Rxjava {

    private static String DATA_AUDIOS = "AUDIOS";
    private static String DATA_DETAILS = "DETAILS";
    private static String ACTION_START = "ACTION_START";




    public  void initRxjava(Object responseObj) {

        Observable.just(responseObj)
                .ofType(SongDetails.class).subscribe(new Consumer<SongDetails>() {
            @Override
            public void accept(SongDetails songDetails) throws Exception {

                SongManager.getInstance().setmSongDetails(songDetails);
            }
        });

        Observable.just(responseObj)
                .ofType(SongUrl.class).subscribe(new Consumer<SongUrl>() {
            @Override
            public void accept(SongUrl songUrl) throws Exception {
                SongManager.getInstance().setmSongUrl(songUrl);
            }
        });


        if (SongManager.getInstance().getmSongDetails()!=null && SongManager.getInstance().getmSongUrl() !=null) {

            AudioHelper.startMusicService(SongManager.getInstance().getmSongUrl().getData().get(0), SongManager.getInstance().getmSongDetails().getSongs().get(0));
        }

    }


    public Rxjava() {

    }




}