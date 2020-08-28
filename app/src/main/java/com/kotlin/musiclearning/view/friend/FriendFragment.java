//package com.kotlin.musiclearning.view.friend;
//
//import android.content.Context;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.kotlin.lib_common_ui.wrapper.LoadMoreWrapper;
//import com.kotlin.lib_network.okHttp.listener.DisposeDataListener;
//import com.kotlin.musiclearning.R;
//import com.kotlin.musiclearning.com.kotlin.lib_base.api.RequestCenter;
//import com.kotlin.lib_base.model.BaseFriendModel;
//import com.kotlin.lib_base.model.FriendBodyValue;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class FriendFragment extends Fragment implements LoadMoreWrapper.OnLoadMoreListener{
///*
//    UI
//*/
//
//    private SwipeRefreshLayout mSwipeRefreshLayout;
//    private RecyclerView mRecyclerView;
//    private Context mContext;
//
//    private BaseFriendModel mFriendDatas;
//    private List<FriendBodyValue> mDatas = new ArrayList<>();
//    private FriendRecyclerAdapter adapter;
//    private LoadMoreWrapper mLoadMoreWrapper;
//
//
//    public static FriendFragment newInstance() {
//        FriendFragment fragment = new FriendFragment();
//        return fragment;
//
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////            onRefresh();
////            onRequestDate();
//    }
//
////    private void onRequestDate() {
////        RequestCenter.requestFriendData(new DisposeDataListener() {
////            @Override
////            public void onSuccess(Object responseObj) {
////                //如果成功返回显示数据
////                mFriendDatas = (BaseFriendModel)responseObj;
////                updateUi();
////            }
////
////            @Override
////            public void onFailure(Object responseObj) {
//////                失败则弹框
////                Toast.makeText(mContext,"加载错误",Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
//    //更新界面
//    private void updateUi() {
////        mSwipeRefreshLayout.setRefreshing(false);
////        mDatas = mFriendDatas.data.list;
////        adapter = new FriendRecyclerAdapter(mContext,mDatas);
////        mRecyclerView.setAdapter(adapter);
////        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
////        mLoadMoreWrapper = new LoadMoreWrapper(adapter);                       //加载更多的效果器，为最后一个选项后面添加加载更多按钮
////        mLoadMoreWrapper.setOnLoadMoreListener(this);
//
//
//
//    }
//
//    private void onRefresh() {
//        onRequestDate();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);
////        mSwipeRefreshLayout = rootView.findViewById(R.id.swipeRefresh);
////        mRecyclerView = rootView.findViewById(R.id.recylerView);
//        return rootView;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        onRequestDate();
//    }
//
//    @Override
//    public void onLoadMoreRequested() {
//
//
//    }
//    //加载更多
//    private void LoadMore(){
//        RequestCenter.requestFriendData(new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                //如果成功返回显示数据
//                mFriendDatas = (BaseFriendModel)responseObj;
//                BaseFriendModel moreDate = (BaseFriendModel) responseObj;
//                mDatas.addAll(moreDate.data.list);
//                mLoadMoreWrapper.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Object responseObj) {
////                失败则弹框
//                Toast.makeText(mContext,"加载错误",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}