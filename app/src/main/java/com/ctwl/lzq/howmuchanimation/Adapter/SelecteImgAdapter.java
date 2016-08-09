package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;
import com.ctwl.lzq.howmuchanimation.ViewHolder.ImageViewHolder;

import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/9.
 */
public class SelecteImgAdapter extends BaseAdapter{

    List<String> mList;
    Context mContext;

    public SelecteImgAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public void onBaseAdapterBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VolleyUtils.getInstance().displayImageView("content://"+mList.get(position),((ImageViewHolder)holder).imageView);
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
        return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image,parent,false));
    }
}
