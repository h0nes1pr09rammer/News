package com.ctwl.lzq.howmuchanimation.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.ScreenUtils;

/**
 * Created by h0nes1pr09rammer on 2016/8/8.
 */
public class ImageViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public ImageView selecteImageView;
    public ImageViewHolder(View itemView, Context context) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img_item);
        selecteImageView = (ImageView) itemView.findViewById(R.id.selected_iv);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth(context)/4,ScreenUtils.getScreenWidth(context)/4);
        itemView.setLayoutParams(layoutParams);
    }
}
