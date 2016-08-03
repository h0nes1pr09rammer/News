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

import com.ctwl.lzq.howmuchanimation.Adapter.DiscoverStPagerAdapter;
import com.ctwl.lzq.howmuchanimation.Adapter.NewsRecyclerAdapter;
import com.ctwl.lzq.howmuchanimation.Contract.NewsContract;
import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Presenter.NewsPresenter;
import com.ctwl.lzq.howmuchanimation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by h0nes1pr09rammer on 2016/8/2.
 */
public class DiscoverStPagerFragment extends Fragment implements NewsContract.View,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    NewsPresenter mNewsPresenter;
    DiscoverStPagerAdapter mDiscoverStPagerAdapter;
    LinearLayoutManager mLinearLayoutManager;
    private View mContentView;
    private List<News> mNewsList;
    private int pageNumber = 0;

    private String getPageNumber(){
        pageNumber++;
        return String.valueOf(pageNumber);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_discover_st,container,false);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this,getView());
        mNewsList = new ArrayList<News>();
        mNewsPresenter = new NewsPresenter(this,getContext());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mDiscoverStPagerAdapter = new DiscoverStPagerAdapter(mNewsList,getContext());
        //设置布局管理器
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //设置移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mDiscoverStPagerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        mNewsPresenter.loadNews("5572a109b3cdc86cf39001db","1");
    }

    @Override
    public void loadingMoreSuccess() {

    }

    @Override
    public void loadingSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        mNewsList.clear();
        mNewsList.addAll(mNewsPresenter.getNews());
        mDiscoverStPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String msg) {
        mSwipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getView(),"加载错误",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }

    @Override
    public void onRefresh() {
        pageNumber = 1;
        mNewsPresenter.loadMore("5572a109b3cdc86cf39001db","1");
    }
}
