package com.ctwl.lzq.howmuchanimation.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by h0nes1pr09rammer on 2016/8/8.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img_item);
    }
}
