package com.kotlin.lib_search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kotlin.lib_common_ui.base.BaseActivity;
import com.kotlin.lib_search.R;
import com.kotlin.lib_search.model.CHANNEL;
import com.kotlin.lib_search.view.fragment.All_Fragment;
import com.kotlin.lib_search.view.fragment.SongFragment;

public class ResultSearchActivity extends BaseActivity {
    private TabLayout mTabLayout;
   private ViewPager2 mViewpager;
   private FragmentTransaction mFragmentTransaction;
   private CHANNEL[] mChannel;
   private All_Fragment all_fragment;

   private ResultPagerAdapter mAdapter;
   private SongFragment songFragment;
//   private static final CHANNEL[] CHANNELS =
//           new CHANNEL[]{CHANNEL.ALBUM,CHANNEL.SINGER,CHANNEL.SONG,CHANNEL.ALL,CHANNEL.VIDEO_PLAYER};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

           initView();


        //  setViewpagerListener();



    }





    /**
     * 如果一次性的建完所有的Fragment的话，加载时就会同时加载造成首页的卡顿，提高内存使用率
     */
    private void initView(){

        mTabLayout = findViewById(R.id.tab_layout);
//        for(int i=0;i<titles.length;i++){
//            mTabLayout.addTab(mTabLayout.newTab());
//            mTabLayout.getTabAt(i).setText(titles[i]);
//        }                RequestCenter.searchAlbum(SearchActivity.this);



        mAdapter = new ResultPagerAdapter(getSupportFragmentManager(),getLifecycle());
        mViewpager = findViewById(R.id.result_viewpager);
        mViewpager.setAdapter(mAdapter);
        TabLayoutMediator tabLayoutMediator=  new TabLayoutMediator(mTabLayout, mViewpager, new TabLayoutMediator.TabConfigurationStrategy() {

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if(position==0) {
                            tab.setText("综合");
                        }else if(position==1){

                        tab.setText("单曲");
                        }else if (position==2){
                            tab.setText("歌手");

                        }else if(position==3){
                            tab.setText("专辑");

                        }else {
                            tab.setText("视频");

                        }



                }

        });
        tabLayoutMediator.attach();
    }

}
