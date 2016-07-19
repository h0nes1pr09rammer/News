package com.ctwl.lzq.howmuchanimation;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by B41-80 on 2016/6/30.
 */
public abstract class BaseFragment extends Fragment{

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            Log.i("NewsFragment","Visible");
            onVisible();
        } else {
            Log.i("NewsFragment","inVisible");
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        clearData();
    }

    protected abstract void clearData();


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

}
