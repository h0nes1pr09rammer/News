package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Activity.SelecteImgActivity;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.ImageLoaderUtils;
import com.ctwl.lzq.howmuchanimation.ViewHolder.ImageViewHolder;

import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/8.
 */
public class AddImageViewAdapter extends BaseAdapter{
    Context mContext;
    List<String> mList;

    public AddImageViewAdapter(Context mContext,List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }



    @Override
    public void onBaseAdapterBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageLoaderUtils.getInstance().dispalyNativeImg(mList.get(position),((ImageViewHolder)holder).imageView);
    }

    @Override
    public int getListSize() {
        return mList.size();
    }

    @Override
    public int getBaseItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image,parent,false),mContext);
    }
}
