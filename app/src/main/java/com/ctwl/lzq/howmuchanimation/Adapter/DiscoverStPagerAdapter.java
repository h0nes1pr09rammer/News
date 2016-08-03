package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsThreeImageViewHolder;
import com.ctwl.lzq.howmuchanimation.ViewHolder.NewsViewHolder;

import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/2.
 */
public class DiscoverStPagerAdapter extends BaseAdapter{

    List<News> mNewsList;
    Context context;
    private final static int NO_IMG_NEWS = 0;
    private final static int ONE_IMG_NEWS = 1;

    public DiscoverStPagerAdapter(List<News> mNewsList, Context context) {
        this.mNewsList = mNewsList;
        this.context = context;
    }

    @Override
    public void onBaseAdapterBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case NO_IMG_NEWS:
                ((NewsViewHolder)holder).textView.setText(mNewsList.get(position).getTitle());
                if (mNewsList.get(position).getDesc().length()>57){
                    ((NewsViewHolder)holder).desTextView.setText(mNewsList.get(position).getDesc().substring(0,57)+"...");
                }else{
                    ((NewsViewHolder)holder).desTextView.setText(mNewsList.get(position).getDesc());
                }
                break;
            case ONE_IMG_NEWS:
                ((NewsImageViewHolder)holder).titleTextView.setText(mNewsList.get(position).getTitle());
                ((NewsImageViewHolder)holder).sourceTextView.setText(mNewsList.get(position).getSource());
                VolleyUtils.getInstance().displayImageView(mNewsList.get(position).getImageurls().get(0).getUrl(),((NewsImageViewHolder) holder).titleImageView);
                break;
            default:
                ((NewsThreeImageViewHolder)holder).titleTextView.setText(mNewsList.get(position).getTitle());
                if (holder.getItemViewType()==2){
                    VolleyUtils.getInstance().displayImageView(mNewsList.get(position).getImageurls().get(0).getUrl(),((NewsThreeImageViewHolder) holder).oneImageView);
                    VolleyUtils.getInstance().displayImageView(mNewsList.get(position).getImageurls().get(1).getUrl(),((NewsThreeImageViewHolder) holder).twoImageView);
                }else{
                    VolleyUtils.getInstance().displayImageView(mNewsList.get(position).getImageurls().get(0).getUrl(),((NewsThreeImageViewHolder) holder).oneImageView);
                    VolleyUtils.getInstance().displayImageView(mNewsList.get(position).getImageurls().get(1).getUrl(),((NewsThreeImageViewHolder) holder).twoImageView);
                    VolleyUtils.getInstance().displayImageView(mNewsList.get(position).getImageurls().get(2).getUrl(),((NewsThreeImageViewHolder) holder).threeImageView);
                }
                break;
        }
    }

    @Override
    public int getListSize() {
        return mNewsList.size();
    }

    @Override
    public int getBaseItemViewType(int position) {
        return mNewsList.get(position).getImagesNumber();
    }

    @Override
    public RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case NO_IMG_NEWS:
                return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.ry_item_news,parent,false));
            case ONE_IMG_NEWS:
                return new NewsImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_image,parent,false));
            default:
                return new NewsThreeImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_img_three,parent,false));
        }
    }
}
