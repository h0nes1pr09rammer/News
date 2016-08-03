package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Activity.WebViewActivity;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;
import com.ctwl.lzq.howmuchanimation.View.NewsFragment;
import com.ctwl.lzq.howmuchanimation.ViewHolder.FootViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.HeadViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsThreeImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsViewHolder;

import java.util.List;

/**
 * Created by B41-80 on 2016/7/12.
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final static int HAS_HEAD_SIGN = -1;
    private final static int HAS_FOOT_SIGN = -2;
    private final static int NO_IMG_NEWS = 0;
    private final static int ONE_IMG_NEWS = 1;
    private Context context;
    private List<News> newsList;
    private View mHeadView;
    private View mFootView;
    private boolean hasHead;
    private boolean hasFoot;


    public NewsRecyclerAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    /**
     * 添加头部
     * @param mHeadView
     */
    public void setHeadView(View mHeadView){
        hasHead = true;
        this.mHeadView = mHeadView;
    }

    /**
     * 添加尾部
     * @param mFootView
     */
    public void setFootView(View mFootView){
        hasFoot = true;
        this.mFootView = mFootView;
    }
    @Override
    public int getItemViewType(int position) {
        if (!hasFoot&&!hasHead){
            return newsList.get(position).getImagesNumber();
        }else if (hasHead&&!hasFoot){
            if (position == 0){
                return NewsRecyclerAdapter.HAS_HEAD_SIGN;
            }else{
                return newsList.get(position-1).getImagesNumber();
            }
        }else if (hasFoot&&!hasHead){
            if (position == getItemCount()-1){
                return NewsRecyclerAdapter.HAS_FOOT_SIGN;
            }else{
                return newsList.get(position).getImagesNumber();
            }
        }else{
            if (position == getItemCount()-1){
                return NewsRecyclerAdapter.HAS_FOOT_SIGN;
            }else if(position == 0){
                return NewsRecyclerAdapter.HAS_HEAD_SIGN;
            }else{
                return newsList.get(position-1).getImagesNumber();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case HAS_HEAD_SIGN:
                return new HeadViewHolder(mHeadView);
            case HAS_FOOT_SIGN:
                return new FootViewHolder(mFootView);
            case NO_IMG_NEWS:
                return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.ry_item_news,parent,false));
            case ONE_IMG_NEWS:
                return new NewsImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_image,parent,false));
            default:
                return new NewsThreeImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_img_three,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int mPosition;
        if (!hasHead&&!hasFoot){
            mPosition = position;
        }else if (hasFoot&&hasHead){
            mPosition = position-1;
        }else if (hasHead&&!hasFoot){
            mPosition = position-1;
        }else{
            mPosition = position;
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
            case NO_IMG_NEWS:
                ((NewsViewHolder)holder).textView.setText(newsList.get(mPosition).getTitle());
                if (newsList.get(mPosition).getDesc().length()>57){
                    ((NewsViewHolder)holder).desTextView.setText(newsList.get(mPosition).getDesc().substring(0,57)+"...");
                }else{
                    ((NewsViewHolder)holder).desTextView.setText(newsList.get(mPosition).getDesc());
                }
                break;
            case ONE_IMG_NEWS:
                ((NewsImageViewHolder)holder).titleTextView.setText(newsList.get(mPosition).getTitle());
                ((NewsImageViewHolder)holder).sourceTextView.setText(newsList.get(mPosition).getSource());
                VolleyUtils.getInstance().displayImageView(newsList.get(mPosition).getImageurls().get(0).getUrl(),((NewsImageViewHolder) holder).titleImageView);
                break;
            case NewsRecyclerAdapter.HAS_HEAD_SIGN:
                break;
            case NewsRecyclerAdapter.HAS_FOOT_SIGN:
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
        if (!hasHead&&!hasFoot){
            return newsList.size();
        }else if (hasFoot&&hasHead){
            return newsList.size()+2;
        }else{
            return newsList.size()+1;
        }
    }
}
