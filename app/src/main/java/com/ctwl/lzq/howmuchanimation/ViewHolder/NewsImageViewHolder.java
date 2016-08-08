package com.ctwl.lzq.howmuchanimation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/7/12.
 */
public class NewsImageViewHolder extends RecyclerView.ViewHolder{
    public ImageView titleImageView;
    public TextView titleTextView;
    public TextView sourceTextView;
    public ImageView mCollectionImageView;
    public ImageView mShareImageView;
    public NewsImageViewHolder(View itemView) {
        super(itemView);
        titleImageView = (ImageView) itemView.findViewById(R.id.img);
        titleTextView = (TextView) itemView.findViewById(R.id.news_title);
        sourceTextView = (TextView) itemView.findViewById(R.id.news_srouce);
        mCollectionImageView = (ImageView) itemView.findViewById(R.id.m_collection);
        mShareImageView = (ImageView) itemView.findViewById(R.id.m_share);
    }
}
