package com.kotlin.musiclearning.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

//import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_base.event.search.SearchTrendEvent;
import com.kotlin.lib_base.model.search.SearchManager;
import com.kotlin.lib_base.model.search.SearchTrend;
import com.kotlin.lib_common_ui.base.BaseActivity;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.lib_image_loader.ImageLoaderManager;
import com.kotlin.lib_search.view.SearchActivity;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.model.CHANNEL;
import com.kotlin.lib_base.model.user.UserManager;
import com.kotlin.musiclearning.event.LoginEvent;
import com.kotlin.musiclearning.view.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;
import org.greenrobot.eventbus.ThreadMode;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public  class HomeActivity extends BaseActivity implements View.OnClickListener , DisposeDataListener {

    //首页出现的卡片
    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{CHANNEL.MY,CHANNEL.DISCOVERY};

    //初始化activity_home的部件
    private DrawerLayout
            mDrawerLayout;
    private View mToggleView;
    private ViewPager mViewPager;
    private HomePagerAdapter mAdapter;
    private ImageView mSearchView;
    private View unLogginLayout;
    private ImageView mPhotoVIew;
   private ImageLoaderManager imageLoaderManager = new ImageLoaderManager();
   private TextView mUserName;


    private SearchActivity mSearchActivity;
    private TabLayout mTabLayout;


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        initView();
//        initData();
    }
    private void initView(){
        mDrawerLayout = findViewById(R.id.drawer_layout);

        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);

        mToggleView = findViewById(R.id.toggle_view);
        mToggleView.setOnClickListener(this);
        //登录ui的组件
        unLogginLayout = findViewById(R.id.unloggin_layout);
        unLogginLayout.setOnClickListener(this);
        mPhotoVIew = findViewById(R.id.avatr_view);
        mPhotoVIew.setVisibility(GONE);
        mUserName = findViewById(R.id.mUserName);

        initViewPager();


    }

//    private void goToWebView(String url){
//        ARouter.getInstance().build(Constant.Router.ROUTER_WEB_ACTIVITY)                                //使用Arouter跳转到webview的页面
//                .withString("url",url)                                                                  //使用了withString方法的"url"参数这样就不需要Activity再去传参了
//                .navigation();
//
//
//    }

private  void initViewPager() {
    ViewPager2 viewPager = new ViewPager2(this);

    mTabLayout = findViewById(R.id.home_tab_layout);
//        for(int i=0;i<titles.length;i++){
//            mTabLayout.addTab(mTabLayout.newTab());
//            mTabLayout.getTabAt(i).setText(titles[i]);
//        }                RequestCenter.searchAlbum(SearchActivity.this);


    mAdapter = new HomePagerAdapter(getSupportFragmentManager(), getLifecycle());
    viewPager = findViewById(R.id.view_pager);
    viewPager.setAdapter(mAdapter);
    TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy(){

        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            if (position == 0) {
                tab.setText("发现");
            } else if (position == 1) {

                tab.setText("我的");
            } else if (position == 2) {
                tab.setText("歌手");

            } else {
                tab.setText("视频");

            }


        }

    });
    tabLayoutMediator.attach();


}



    private void initData() {
//        mLists.add(new AudioBean("100001", "http://sp-sycdn.kuwo.cn/resource/n2/85/58/433900159.mp3",
//                "以你的名字喊我", "周杰伦", "七里香", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698076304&di=e6e99aa943b72ef57b97f0be3e0d2446&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201401%2F04%2F20140104170315_XdG38.jpeg",
//                "4:30"));
//        mLists.add(
//                new AudioBean("100002", "http://sq-sycdn.kuwo.cn/resource/n1/98/51/3777061809.mp3", "勇气",
//                        "梁静茹", "勇气", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
//                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698193627&di=711751f16fefddbf4cbf71da7d8e6d66&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D213168965%2C1040740194%26fm%3D214%26gp%3D0.jpg",
//                        "4:40"));
//        mLists.add(
//                new AudioBean("100003", "http://sp-sycdn.kuwo.cn/resource/n2/52/80/2933081485.mp3", "灿烂如你",
//                        "汪峰", "春天里", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
//                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698239736&di=3433a1d95c589e31a36dd7b4c176d13a&imgtype=0&src=http%3A%2F%2Fpic.zdface.com%2Fupload%2F201051814737725.jpg",
//                        "3:20"));
//        mLists.add(
//                new AudioBean("100004", "https://music.163.com/#/song?id=1432156509.mp3", "小情歌",
//                        "五月天", "小幸运", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
//                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698289780&di=5146d48002250bf38acfb4c9b4bb6e4e&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20131220%2Fbki-20131220170401-1254350944.jpg",
//                        "2:45"));

//        AudioHelper.startMusicService(mLists);

    }






    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.unloggin_layout:                      //判断是否登录
                if (!UserManager.getInstance().hasLogined()){
                    LoginActivity.start(this);              //如果没登录了之后就跳转到LoginActivity
                }else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);        //如果登录后了就关闭DrawerLayout
//                    unLogginLayout.setVisibility(GONE);                                       //登录之后把未登录布局隐藏
//
//                    //用户头像显示出来
//                    ImageLoaderManager.getInstance().displayImageForView(mPhotoVIew, UserManager.getInstance().getProfile().avatarUrl);

                }
                break;
            case R.id.search_view:
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(intent);
                RequestCenter.searchTrend(this);



                break;

        }




    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onLoginEvent( LoginEvent event) {
        unLogginLayout.setVisibility(View.GONE);
        mPhotoVIew.setVisibility(VISIBLE);
                                       //登录之后把未登录布局隐藏
        mUserName.setText(UserManager.getInstance().getUser().getProfile().getNickname());
                    //用户头像显示出来
        imageLoaderManager.displayImageForCircleView(mPhotoVIew, UserManager.getInstance().getUser().getProfile().getAvatarUrl());

    }

    @Subscribe
    public void onSubscriberExceptionEvent(SubscriberExceptionEvent event) {
        // 订阅方法抛出异常时, 会到这里
        Log.i("TEST", "onSubscriberExceptionEvent --> " + event.throwable.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       EventBus.getDefault().unregister(this);

    }



    @Override
    public void onSuccess(Object responseObj) {
        SearchTrend searchTrend = new SearchTrend();
        if (responseObj.getClass().equals(SearchTrend.class)) {
            searchTrend = (SearchTrend) responseObj;
            SearchManager.getInstance().setSearchTrend(searchTrend);                   //把得到的用户信息保存到内存中
            EventBus.getDefault().post(new SearchTrendEvent());
        }

    }

    @Override
    public void onFailure(Object responseObj) {
        SearchTrend searchTrend = new SearchTrend();
        if(searchTrend.getCode()== 400){
            Toast.makeText(this,"搜索失败,请重试",Toast.LENGTH_LONG).show();
        }
    }
}
