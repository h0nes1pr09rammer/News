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

    public NewsRecyclerAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getItemViewType(int position) {
        return newsList.get(position).getImagesNumber();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.i("NewsViewHolder",""+viewType);
        switch (viewType){
            case 0:
                return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.ry_item_news,parent,false));
            case 1:
                return new NewsImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_image,parent,false));
            default:
                return new NewsThreeImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_img_three,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url",newsList.get(position).getLink());
                context.startActivity(intent);
            }
        });
        switch (holder.getItemViewType()){
            case 0:
                ((NewsViewHolder)holder).textView.setText(newsList.get(position).getTitle());
                if (newsList.get(position).getDesc().length()>57){
                    ((NewsViewHolder)holder).desTextView.setText(newsList.get(position).getDesc().substring(0,57)+"...");
                }else{
                    ((NewsViewHolder)holder).desTextView.setText(newsList.get(position).getDesc());
                }
                break;
            case 1:
                ((NewsImageViewHolder)holder).titleTextView.setText(newsList.get(position).getTitle());
                ((NewsImageViewHolder)holder).sourceTextView.setText(newsList.get(position).getSource());
                VolleyUtils.getInstance().disPlayImageView(newsList.get(position).getImageurls().get(0).getUrl(),((NewsImageViewHolder) holder).titleImageView);
                break;
            default:
                ((NewsThreeImageViewHolder)holder).titleTextView.setText(newsList.get(position).getTitle());
                if (holder.getItemViewType()==2){
                    VolleyUtils.getInstance().disPlayImageView(newsList.get(position).getImageurls().get(0).getUrl(),((NewsThreeImageViewHolder) holder).oneImageView);
                    VolleyUtils.getInstance().disPlayImageView(newsList.get(position).getImageurls().get(1).getUrl(),((NewsThreeImageViewHolder) holder).twoImageView);
                }else{
                    VolleyUtils.getInstance().disPlayImageView(newsList.get(position).getImageurls().get(0).getUrl(),((NewsThreeImageViewHolder) holder).oneImageView);
                    VolleyUtils.getInstance().disPlayImageView(newsList.get(position).getImageurls().get(1).getUrl(),((NewsThreeImageViewHolder) holder).twoImageView);
                    VolleyUtils.getInstance().disPlayImageView(newsList.get(position).getImageurls().get(2).getUrl(),((NewsThreeImageViewHolder) holder).threeImageView);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
