package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.ViewHolder.ImageViewHolder;

/**
 * Created by h0nes1pr09rammer on 2016/8/8.
 */
public class AddImageViewAdapter extends RecyclerView.Adapter{
    Context mContext;

    public AddImageViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }
}
