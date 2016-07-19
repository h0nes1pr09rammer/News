package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ctwl.lzq.howmuchanimation.Adapter.NewsRecyclerAdapter;
import com.ctwl.lzq.howmuchanimation.BaseFragment;
import com.ctwl.lzq.howmuchanimation.Contract.NewsContract;
import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/6/28.
 */
public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,NewsContract.View{

    View view;
    View lodingView;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    NewsContract.Presenter newsPresenter;
    int itemContent;
    String channelId;
    boolean isHasLoad;
    FrameLayout frameLayout;
    ImageView loadingImageView;
    Animation mAnimation;
    boolean isPrepared;

    public  NewsFragment(int position,String channelId){
        itemContent = position;
        this.channelId = channelId;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (frameLayout==null){
            frameLayout = new FrameLayout(getActivity());
        }
        view = inflater.inflate(R.layout.fragment_news,container,false);
        frameLayout.addView(view);
        if (!isHasLoad){
            lodingView = View.inflate(getActivity(), R.layout.loding_news_fragment, null);
            loadingImageView = (ImageView) lodingView.findViewById(R.id.loading);
            mAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_view_reverse);
            loadingImageView.startAnimation(mAnimation);
            frameLayout.addView(lodingView);
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swiperefreshlayout);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL_LIST));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        swipeRefreshLayout.setOnRefreshListener(this);
        isPrepared = true;
        lazyLoad();
        return frameLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        newsPresenter.loadingNews();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        newsPresenter = presenter;
    }

    @Override
    public void setRefereshing(boolean refereshing) {
        swipeRefreshLayout.setRefreshing(refereshing);
    }

    @Override
    public void waitLoading() {

    }

    @Override
    public void loadingSuccess() {
        //设置adapter
        recyclerView.setAdapter(new NewsRecyclerAdapter(getActivity(),newsPresenter.getNews()));
        isHasLoad = true;
        loadingImageView.clearAnimation();
        frameLayout.removeView(lodingView);
    }

    @Override
    public void showErrorMsg() {
        Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void clearData() {

    }

    @Override
    protected void lazyLoad() {
       if (!isPrepared||isHasLoad||!isVisible){
           return;
       }
        newsPresenter.loadNews(channelId);
        isHasLoad = true;
    }
}
