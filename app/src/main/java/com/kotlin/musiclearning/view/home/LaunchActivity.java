package com.kotlin.musiclearning.view.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.musiclearning.Utils.RequestSuccessCollection;
import com.kotlin.musiclearning.R;



public class LaunchActivity extends AppCompatActivity implements DisposeDataListener {
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Window window = getWindow();
        window.getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
       RequestCenter.DSTopList(this);
       RequestCenter.DSOriginList(this);
        RequestCenter.DSBanner(this);
        RequestCenter.DSNewSongs(this);
        RequestCenter.DSPersonalizedPlayList(this);
        RequestCenter.DSHotDJ(this);
        RequestCenter.MinePersonalizedFm(this);
        RequestCenter.MineCreatedPlayList(this);
        RequestCenter.DSElectronicList(this);
        RequestCenter.DS24HourDj(this);

        Handler x = new Handler();//定义一个handle对象

        imageView = findViewById(R.id.logo_gif);
        Glide.with(getApplicationContext()).asGif().load(R.drawable.load_gif).centerCrop().into(imageView);
        x.postDelayed(new splashhandler(), 5000);//设置3秒钟延迟执行splashhandler线程。其实你这里可以再新建一个线程去执行初始化工作，如判断SD,网络状态等
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onSuccess(Object responseObj) {

    RequestSuccessCollection requestSuccessCollection = new RequestSuccessCollection();
    requestSuccessCollection.onSuccess(responseObj);



    }




    @Override
    public void onFailure(Object responseObj) {

    }

    class splashhandler implements Runnable{
        public void run() {
            startActivity(new Intent(getApplication(),HomeActivity.class));// 这个线程的作用3秒后就是进入到你的主界面
            LaunchActivity.this.finish();// 把当前的LaunchActivity结束掉
        }
    }




}