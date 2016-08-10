package com.ctwl.lzq.howmuchanimation.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.ctwl.lzq.howmuchanimation.Model.Bean.ImgInfo;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.ImageLoaderUtils;
import com.ctwl.lzq.howmuchanimation.Utils.ScreenUtils;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;
import com.ctwl.lzq.howmuchanimation.ViewHolder.ImageViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/9.
 */
public class SelecteImgAdapter extends BaseAdapter{
    List<ImgInfo> mList;
    Context mContext;
    RecyclerView recyclerView;
    int lastVisibleItem;
    int firstVisibleItem;
    boolean isFirstEnter = true;
    int mSelectCount;
    ArrayList<String> selectImgPath;

    public SelecteImgAdapter(RecyclerView recyclerView, final List<ImgInfo> mList, Context mContext) {
        selectImgPath = new ArrayList<>();
        this.mList = mList;
        this.mContext = mContext;
        this.recyclerView = recyclerView;
//        this.recyclerView.setOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE){
//                    Log.v("SelecteImgAdapter","静止");
//                    for (int i  = firstVisibleItem;i<lastVisibleItem-firstVisibleItem;i++){
//                        ImageLoaderUtils.getInstance().dispalyNativeImg(mList.get(i), (ImageView) recyclerView.findViewWithTag(mList.get(i)),isScroll);
//                    }
//                    isScroll = true;
////                    notifyDataSetChanged();
//                }else{
//                    Log.v("SelecteImgAdapter","滚动");
//                    isScroll = false;
//                    ImageLoaderUtils.getInstance().cancelAllTasks();
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem =((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                firstVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                if (isFirstEnter){
//                    for (int i  = firstVisibleItem;i<lastVisibleItem-firstVisibleItem;i++){
//                        ImageLoaderUtils.getInstance().dispalyNativeImg(mList.get(i), (ImageView) recyclerView.findViewWithTag(mList.get(i)),isScroll);
//                    }
//                    isFirstEnter = false;
//                }
//            }
//        });
    }

    @Override
    public void onBaseAdapterBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        ((ImageViewHolder)holder).imageView.setImageBitmap(BitmapFactory.decodeFile(mList.get(position)));
//        VolleyUtils.getInstance().displayImageView("content://"+mList.get(position),((ImageViewHolder)holder).imageView);
//        ((ImageViewHolder)holder).imageView.setTag(mList.get(position));
        ImageLoaderUtils.getInstance().dispalyNativeImg(mList.get(position).getPath(),((ImageViewHolder)holder).imageView);
        ((ImageViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.get(position).isSelected()){
                    if (mSelectCount>0){
                        ((ImageViewHolder)holder).selecteImageView.setImageResource(R.mipmap.img_unselected);
                        mList.get(position).setSelected(false);
                        selectImgPath.remove(mList.get(position).getPath());
                        mSelectCount--;
                    }
                }else{
                    if (mSelectCount<9){
                        ((ImageViewHolder)holder).selecteImageView.setImageResource(R.mipmap.img_selected);
                        mList.get(position).setSelected(true);
                        selectImgPath.add(mList.get(position).getPath());
                        mSelectCount++;
                    }
                }
            }
        });
    }

    @Override
    public int getListSize() {
        return mList.size();
    }

    @Override
    public int getBaseItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image,parent,false),mContext);
    }
    public ArrayList<String> getSelectImgPath(){
        if (selectImgPath != null){
            selectImgPath = new ArrayList<>();
        }
        return selectImgPath;
    }
}
