package com.ctwl.lzq.howmuchanimation.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by h0nes1pr09rammer on 2016/8/3.
 */
public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener{

    int mListItemCount;
    int lastVisibleItem;
    OnScrollListener mOnScrollListener;
    RecyclerView.Adapter mAdapter;

    public RecyclerViewScrollListener(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }
    public void setOnScrollListener(OnScrollListener mOnScrollListener){
        this.mOnScrollListener = mOnScrollListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        //滑动到底部开始加载数据
        Log.v("RecyclerView","onScrollStateChanged");
        Log.v("RecyclerView","lastVisibleItem"+lastVisibleItem);
        Log.v("RecyclerView","mListItemCount"+mListItemCount);
        Log.v("RecyclerView","newState"+newState);
        if (newState ==RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==mAdapter.getItemCount()) {
            Log.v("RecyclerView","to bottom");
            mOnScrollListener.scrollToBottom();
        }
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Log.v("RecyclerView","onScrolled");
        lastVisibleItem =((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }

}
