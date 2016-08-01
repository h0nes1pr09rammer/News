package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Activity.WebViewActivity;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.LogUtils;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;
import com.ctwl.lzq.howmuchanimation.ViewHolder.HeadViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsThreeImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsViewHolder;

import java.util.List;

/**
 * Created by B41-80 on 2016/7/12.
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<News> newsList;
    View mHeadView;

    public NewsRecyclerAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }
    public void setHeadView(View headView){
        mHeadView = headView;
    }
    @Override
    public int getItemViewType(int position) {
        if (mHeadView == null) {
            return newsList.get(position).getImagesNumber();
        }
        if (position==0){
            return -1;
        }
        return newsList.get(position-1).getImagesNumber();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.i("NewsViewHolder",""+viewType);
        switch (viewType){
            case -1:
                return new HeadViewHolder(LayoutInflater.from(context).inflate(R.layout.item_head,parent,false));
            case 0:
                return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.ry_item_news,parent,false));
            case 1:
                return new NewsImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_image,parent,false));
            default:
                return new NewsThreeImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_img_three,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int mPosition;
        if (position!=0){
            if (mHeadView==null){
                mPosition = position;
            }else{
                mPosition = position-1;
            }
        }else{
            mPosition = 0;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url",newsList.get(mPosition).getLink());
                context.startActivity(intent);
            }
        });
        switch (holder.getItemViewType()){
            case 0:
                ((NewsViewHolder)holder).textView.setText(newsList.get(mPosition).getTitle());
                if (newsList.get(position).getDesc().length()>57){
                    ((NewsViewHolder)holder).desTextView.setText(newsList.get(mPosition).getDesc().substring(0,57)+"...");
                }else{
                    ((NewsViewHolder)holder).desTextView.setText(newsList.get(mPosition).getDesc());
                }
                break;
            case 1:
                ((NewsImageViewHolder)holder).titleTextView.setText(newsList.get(mPosition).getTitle());
                ((NewsImageViewHolder)holder).sourceTextView.setText(newsList.get(mPosition).getSource());
                VolleyUtils.getInstance().displayImageView(newsList.get(mPosition).getImageurls().get(0).getUrl(),((NewsImageViewHolder) holder).titleImageView);
                break;
            case -1:
                break;
            default:
                ((NewsThreeImageViewHolder)holder).titleTextView.setText(newsList.get(mPosition).getTitle());
                if (holder.getItemViewType()==2){
                    VolleyUtils.getInstance().displayImageView(newsList.get(mPosition).getImageurls().get(0).getUrl(),((NewsThreeImageViewHolder) holder).oneImageView);
                    VolleyUtils.getInstance().displayImageView(newsList.get(mPosition).getImageurls().get(1).getUrl(),((NewsThreeImageViewHolder) holder).twoImageView);
                }else{
                    VolleyUtils.getInstance().displayImageView(newsList.get(mPosition).getImageurls().get(0).getUrl(),((NewsThreeImageViewHolder) holder).oneImageView);
                    VolleyUtils.getInstance().displayImageView(newsList.get(mPosition).getImageurls().get(1).getUrl(),((NewsThreeImageViewHolder) holder).twoImageView);
                    VolleyUtils.getInstance().displayImageView(newsList.get(mPosition).getImageurls().get(2).getUrl(),((NewsThreeImageViewHolder) holder).threeImageView);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mHeadView==null){
            return newsList.size();
        }else{
            return newsList.size()+1;
        }
    }
}
