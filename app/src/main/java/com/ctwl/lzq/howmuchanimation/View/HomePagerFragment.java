package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Adapter.NewsRecyclerAdapter;
import com.ctwl.lzq.howmuchanimation.BaseFragment;
import com.ctwl.lzq.howmuchanimation.Contract.NewsContract;
import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Model.NewsRepository;
import com.ctwl.lzq.howmuchanimation.Presenter.NewsPresenter;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.listener.OnScrollListener;
import com.ctwl.lzq.howmuchanimation.listener.RecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class HomePagerFragment extends BaseRecyclerViewFragment{

    private View mView;

    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    NewsRecyclerAdapter mNewsRecyclerAdapter;
    NewsPresenter mNewsPresenter;
    List<News> mNewsList;
    RecyclerViewScrollListener mRecyclerViewScrollListener;
    View mFootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_page,container,false);
        mFootView  = inflater.inflate(R.layout.item_foot,container,false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this,mView);
        mNewsPresenter = new NewsPresenter(this,getContext());
        mNewsList = new ArrayList<>();
        mNewsRecyclerAdapter = new NewsRecyclerAdapter(getContext(),mNewsList);
        mRecyclerViewScrollListener = new RecyclerViewScrollListener(mNewsRecyclerAdapter);
        mRecyclerViewScrollListener.setOnScrollListener(this);
        //mRecyclerView滚动监听
        mRecyclerView.addOnScrollListener(mRecyclerViewScrollListener);
        setLinearRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mNewsRecyclerAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        mNewsPresenter.loadNews(NewsRepository.ALL_NEWS_SIGN,"1");
    }

    @Override
    public void loadingMoreSuccess() {
        mNewsList.addAll(mNewsPresenter.getNews());
        mNewsRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadingSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        mNewsList.clear();
        mNewsList.addAll(mNewsPresenter.getNews());
        mNewsRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String msg) {
        super.showErrorMsg(msg);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {

    }

    @Override
    public void onRefresh() {
        mNewsPresenter.loadNews(NewsRepository.ALL_NEWS_SIGN, "1");
    }

    @Override
    public void scrollToBottom() {
        mNewsRecyclerAdapter.setFootView(mFootView);
        mNewsPresenter.loadMore(NewsRepository.ALL_NEWS_SIGN,getPageNumber());
    }

}
