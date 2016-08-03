package com.ctwl.lzq.howmuchanimation.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.ViewHolder.FootViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.HeadViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsThreeImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsViewHolder;

/**
 * Created by h0nes1pr09rammer on 2016/8/2.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final static int BASE_HEAD_SIGN = -404;
    private final static int BASE_FOOT_SIGN = -405;
    private View mHeadView;
    private View mFootView;
    private boolean hasHead;
    private boolean hasFoot;

    @Override
    public int getItemViewType(int position) {
        if (!hasFoot&&!hasHead){
            return getBaseItemViewType(position);
        }else if (hasHead&&!hasFoot){
            if (position == 0){
                return BaseAdapter.BASE_HEAD_SIGN;
            }else{
                return getBaseItemViewType(position-1);
            }
        }else if (hasFoot&&!hasHead){
            if (position == getItemCount()-1){
                return BaseAdapter.BASE_FOOT_SIGN;
            }else{
                return getBaseItemViewType(position);
            }
        }else{
            if (position == getItemCount()-1){
                return BaseAdapter.BASE_FOOT_SIGN;
            }else if(position == 0){
                return BaseAdapter.BASE_HEAD_SIGN;
            }else{
                return getBaseItemViewType(position-1);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case BASE_HEAD_SIGN:
                return new HeadViewHolder(mHeadView);
            case BASE_FOOT_SIGN:
                return new FootViewHolder(mFootView);
        }
        return onBaseCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int mPosition;
        if (!hasHead&&!hasFoot){
            mPosition = position;
        }else if (hasFoot&&hasHead){
            mPosition = position-1;
        }else if (hasHead&&!hasFoot){
            mPosition = position-1;
        }else{
            mPosition = position;
        }
        if (holder.getItemViewType()!=BASE_HEAD_SIGN&&holder.getItemViewType()!=BASE_FOOT_SIGN){
            onBaseAdapterBindViewHolder(holder,mPosition);
        }
    }
    @Override
    public int getItemCount() {
        if (!hasHead&&!hasFoot){
            return getListSize();
        }else if (hasFoot&&hasHead){
            return getListSize()+2;
        }else{
            return getListSize()+1;
        }
    }
    public abstract void onBaseAdapterBindViewHolder(RecyclerView.ViewHolder holder, int position);
    public abstract int getListSize();
    public abstract int getBaseItemViewType(int position);
    public abstract RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType);
    /**
     * 添加头部
     * @param mHeadView
     */
    public void setHeadView(View mHeadView){
        hasHead = true;
        this.mHeadView = mHeadView;
    }

    /**
     * 添加尾部
     * @param mFootView
     */
    public void setFootView(View mFootView){
        hasFoot = true;
        this.mFootView = mFootView;
    }
}
