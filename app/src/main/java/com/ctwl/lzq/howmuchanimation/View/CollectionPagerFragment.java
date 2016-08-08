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
import com.ctwl.lzq.howmuchanimation.Callback.HttpCallBack;
import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Presenter.CollectionPresenter;
import com.ctwl.lzq.howmuchanimation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class CollectionPagerFragment extends Fragment{
    private View mView;
    private List<News> mNewsList;
    CollectionPresenter mCollectionPresenter;
    NewsRecyclerAdapter mNewsRecyclerAdapter;

    @BindView(R.id.m_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.m_recyclerview)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_collection_pager,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //设置移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        mNewsList = new ArrayList<>();
        mNewsRecyclerAdapter = new NewsRecyclerAdapter(getContext(),mNewsList);
        mRecyclerView.setAdapter(mNewsRecyclerAdapter);
        mCollectionPresenter = new CollectionPresenter(this);
        mCollectionPresenter.downLoad(new HttpCallBack() {
            @Override
            public void onSuccess(Object o) {
                mNewsList.addAll(mCollectionPresenter.getNewsList());
                mNewsRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFaile() {

            }
        });
    }

}
