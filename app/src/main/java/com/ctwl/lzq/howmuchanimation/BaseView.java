package com.ctwl.lzq.howmuchanimation;

/**
 * Created by B41-80 on 2016/6/27.
 */
public interface BaseView<T> {
    // 规定View必须要实现setPresenter方法，则View中保持对Presenter的引用
    void setPresenter(T presenter);
}
