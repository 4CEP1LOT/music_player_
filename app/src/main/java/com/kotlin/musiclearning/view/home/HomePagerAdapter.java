package com.kotlin.musiclearning.view.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kotlin.lib_base.model.CHANNEL;
import com.kotlin.musiclearning.view.discovery.DiscoveryFragment;
import com.kotlin.musiclearning.view.mine.MineFragment;

import java.util.ArrayList;


//首页Viewpager的Adapter

public class HomePagerAdapter extends FragmentStateAdapter {
    private CHANNEL[] mList;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    public ArrayList<Fragment> getFragment() {
        fragments.add(DiscoveryFragment.newInstance());
        fragments.add(MineFragment.newInstance());



        return fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return getFragment().get(position);
    }

    @Override
    public int getItemCount() {
        return 2;                           //一定别忘了要改回来！！！！！！！！！！！！！
    }
}
