package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Adapter.NewsRecyclerAdapter;
import com.ctwl.lzq.howmuchanimation.BasePresenter;
import com.ctwl.lzq.howmuchanimation.Contract.NewsContract;
import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.listener.OnScrollListener;
import com.ctwl.lzq.howmuchanimation.listener.RecyclerViewScrollListener;

import butterknife.ButterKnife;

/**
 * Created by h0nes1pr09rammer on 2016/8/3.
 */
public abstract class BaseRecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,NewsContract.View,OnScrollListener {
    private int pageNumber;

    public void init(RecyclerView mRecyclerView, final SwipeRefreshLayout mSwipeRefreshLayout, RecyclerView.Adapter mAdapter){
        RecyclerViewScrollListener mRecyclerViewScrollListener = new RecyclerViewScrollListener(mAdapter);
        mRecyclerViewScrollListener.setOnScrollListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }

    @Override
    public void onRefresh() {

    }
    @Override
    public void scrollToBottom() {

    }

    @Override
    public void loadingMoreSuccess() {

    }

    @Override
    public void loadingSuccess() {

    }

    @Override
    public void showErrorMsg(String msg) {
        Snackbar.make(getView(),msg,Snackbar.LENGTH_LONG).show();
    }


    protected void setLinearRecyclerView(RecyclerView mRecyclerView){
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //设置移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
    }
    protected String getPageNumber(){
        pageNumber++;
        return String.valueOf(pageNumber);
    }
}
