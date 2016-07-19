package com.ctwl.lzq.howmuchanimation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/7/12.
 */
public class NewsThreeImageViewHolder extends RecyclerView.ViewHolder{
    public ImageView oneImageView;
    public ImageView twoImageView;
    public ImageView threeImageView;
    public TextView titleTextView;
    public NewsThreeImageViewHolder(View itemView) {
        super(itemView);
        oneImageView = (ImageView) itemView.findViewById(R.id.img_1);
        twoImageView = (ImageView) itemView.findViewById(R.id.ima_2);
        threeImageView = (ImageView) itemView.findViewById(R.id.img_3);
        titleTextView = (TextView) itemView.findViewById(R.id.news_title);
    }
}
