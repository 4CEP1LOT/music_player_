package com.kotlin.musiclearning.view.mlog;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kotlin.lib_base.api.RequestCenter;
import com.kotlin.lib_connection.okHttp.listener.DisposeDataListener;
import com.kotlin.lib_search.view.LazyFragment;
import com.kotlin.musiclearning.R;
import com.kotlin.lib_base.model.MlogModel;
import com.kotlin.musiclearning.view.tiktok.ActivityTikTok;

import java.util.List;

import static androidx.recyclerview.widget.StaggeredGridLayoutManager.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MlogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MlogFragment extends LazyFragment implements DisposeDataListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<MlogModel.Datas> mList;

    public static MlogFragment newInstance() {
        MlogFragment fragment = new MlogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mlog, container, false);

        RecyclerView mlogRecyclerView = rootView.findViewById(R.id.mlog_recycler);
        MlogAdapter mlogAdapter = new MlogAdapter();
        mlogRecyclerView.setAdapter(mlogAdapter);
        mlogRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         RequestCenter.recommend(MlogFragment.this);
                                         mSwipeRefreshLayout.setRefreshing(false);

                                     }
                                 });
        mlogAdapter.setOnItemClickListener(new MlogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ActivityTikTok.class);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }


    @Override
    protected int getContentViewId() {
        return 0;
    }

    public void initView(){



    }

    @Override
    public void onSuccess(Object responseObj) {

    }

    @Override
    public void onFailure(Object responseObj) {

    }
}