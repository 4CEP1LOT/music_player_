package com.kotlin.lib_search.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kotlin.lib_search.view.fragment.Album_Fragment;
import com.kotlin.lib_search.view.fragment.All_Fragment;
import com.kotlin.lib_search.view.fragment.Singer_Fragment;
import com.kotlin.lib_search.view.fragment.SongFragment;
import com.kotlin.lib_search.view.fragment.Video_Player_Fragment;

import java.util.ArrayList;

public class ResultPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public ResultPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    public ArrayList<Fragment> getFragment() {
        fragments.add(All_Fragment.newInstance());
        fragments.add(SongFragment.newInstance());
        fragments.add(Singer_Fragment.newInstance());
        fragments.add(Album_Fragment.newInstance());
        fragments.add(Video_Player_Fragment.newInstance());


        return fragments;
    }

    /**
     * 如果一次性的建完所有的Fragment的话，加载时就会同时加载造成首页的卡顿，提高内存使用率
     */


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return getFragment().get(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }


}




