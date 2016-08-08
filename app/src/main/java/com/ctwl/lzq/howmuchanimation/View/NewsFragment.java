package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.Adapter.NewsRecyclerAdapter;
import com.ctwl.lzq.howmuchanimation.BaseFragment;
import com.ctwl.lzq.howmuchanimation.Contract.NewsContract;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.listener.OnScrollListener;
import com.ctwl.lzq.howmuchanimation.listener.RecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/6/28.
 */
public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,NewsContract.View,OnScrollListener,NewsRecyclerAdapter.OnNewsItemClikListener{

    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.id_swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    NewsContract.Presenter newsPresenter;
    NewsRecyclerAdapter mNewsRecyclerAdapter;
    RecyclerViewScrollListener mRecyclerViewScrollListener;

    private int itemContent;
    private String channelId;
    private List<News> mNewsList;
    private View mFootView;
    private View mContentView;
    private View mLodingView;
    private TextView mFootLoadView;
    private ImageView mLoadingImageView;
    private FrameLayout mFrameLayout;
    private Animation mAnimation;
    private int pageNumber = 0;

    public  NewsFragment(int position,String channelId){
        itemContent = position;
        this.channelId = channelId;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_news,container,false);
        mFrameLayout = new FrameLayout(getActivity());
        ButterKnife.bind(this,mContentView);
        mNewsList = new ArrayList<News>();
        mAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_view_reverse);
        mFootView = inflater.inflate(R.layout.item_foot,container,false);
        mFootLoadView = (TextView) mFootView.findViewById(R.id.from_tv);
        mFrameLayout.addView(mContentView);
        addLoadView();
        setPrepared(true);
        onVisible();
        return mFrameLayout;
    }

    private void addLoadView() {
        if (!isHasLoad()){
            mLodingView = View.inflate(getActivity(), R.layout.loding_news_fragment, null);
            mLoadingImageView = (ImageView) mLodingView.findViewById(R.id.loading);
            mLoadingImageView.startAnimation(mAnimation);
            mFrameLayout.addView(mLodingView);
        }
    }

    private void initRecyclerView() {
        mNewsRecyclerAdapter = new NewsRecyclerAdapter(getActivity(),mNewsList);
        setLinearRecyclerView(mRecyclerView);
        mRecyclerViewScrollListener = new RecyclerViewScrollListener(mNewsRecyclerAdapter);
        mRecyclerViewScrollListener.setOnScrollListener(this);
        //mRecyclerView滚动监听
        mRecyclerView.addOnScrollListener(mRecyclerViewScrollListener);
        mRecyclerView.setAdapter(mNewsRecyclerAdapter);
        mNewsRecyclerAdapter.setFootView(mFootView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initRecyclerView();
        mNewsRecyclerAdapter.setOnNewsItemClikListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        newsPresenter.loadMore(channelId,"1");
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        newsPresenter = presenter;
    }

    @Override
    public void loadingMoreSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        mFootLoadView.clearAnimation();
        mNewsList.addAll(newsPresenter.getNews());
        mNewsRecyclerAdapter.notifyDataSetChanged();
        Log.v("NewsFragment","加载成功");
    }

    @Override
    public void loadingSuccess() {
        mNewsList.clear();
        mLoadingImageView.clearAnimation();
        mNewsList.addAll(newsPresenter.getNews());
        mNewsRecyclerAdapter.notifyDataSetChanged();
        setHasLoad(true);
        mFrameLayout.removeView(mLodingView);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMsg(String msg) {
        mLoadingImageView.clearAnimation();
        mLoadingImageView.setImageDrawable(getResources().getDrawable(R.mipmap.down_load_faile));
//        Snackbar.make(mFrameLayout,msg,Snackbar.LENGTH_INDEFINITE).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void clearData() {

    }

    @Override
    protected void lazyLoad() {
        newsPresenter.loadNews(channelId,addPageNumber());
        setHasLoad(true);
    }

    @Override
    public void scrollToBottom() {
        mFootLoadView.startAnimation(mAnimation);
        newsPresenter.loadMore(channelId,addPageNumber());
    }
    private String addPageNumber(){
        pageNumber++;
        return String.valueOf(pageNumber);
    }

    @Override
    public void onClikShareButton(News news) {

    }

    @Override
    public void onClikCollectionButton(News news) {
        newsPresenter.upLoad(news);
    }
}
