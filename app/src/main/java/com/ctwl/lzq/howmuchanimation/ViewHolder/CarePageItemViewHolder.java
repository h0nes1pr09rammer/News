package com.ctwl.lzq.howmuchanimation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by h0nes1pr09rammer on 2016/8/11.
 */
public class CarePageItemViewHolder extends RecyclerView.ViewHolder{
    public TextView mNameTextView;
    public CarePageItemViewHolder(View itemView) {
        super(itemView);
        mNameTextView = (TextView) itemView.findViewById(R.id.item_name);
    }
}
