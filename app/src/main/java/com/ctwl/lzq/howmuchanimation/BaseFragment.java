package com.ctwl.lzq.howmuchanimation;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by B41-80 on 2016/6/30.
 */
public abstract class BaseFragment extends Fragment{

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 页面加载完成的标志
     */
    protected boolean isPrepared;
    /**
     * 数据是否已经加载完成的标志
     */
    protected boolean isHasLoad;

    public boolean isHasLoad() {
        return isHasLoad;
    }

    public void setHasLoad(boolean hasLoad) {
        isHasLoad = hasLoad;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }



    /**
     * 获取页面显隐状态
     * @return
     */
    protected boolean getVisible(){
        return isVisible;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        Log.v("BaseFragment","isVisible--"+getVisible());
        Log.v("BaseFragment","isHasLoad--"+isHasLoad);
        Log.v("BaseFragment","isPrepared--"+isPrepared);
        if (!isPrepared()||isHasLoad()||!getVisible()){
            return;
        }
        Log.v("BaseFragment","lazqload");
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        clearData();
    }

    protected void setLinearRecyclerView(RecyclerView mRecyclerView){
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //设置移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
    }

    protected abstract void clearData();


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

}
