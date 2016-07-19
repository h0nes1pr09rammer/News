package com.ctwl.lzq.howmuchanimation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/7/12.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder{
    public TextView textView;
    public TextView desTextView;
    public NewsViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.news_tv);
        desTextView = (TextView) itemView.findViewById(R.id.news_dec);
    }
}
