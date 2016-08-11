package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.ViewHolder.CarePageItemViewHolder;

import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/11.
 */
public class CarePageAdapter extends BaseAdapter{
    List<String> mList;
    Context mContext;
    public CarePageAdapter(List<String> mList,Context context) {
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public void onBaseAdapterBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CarePageItemViewHolder)holder).mNameTextView.setText(mList.get(position));
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
        return new CarePageItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_care,parent,false));
    }
}
