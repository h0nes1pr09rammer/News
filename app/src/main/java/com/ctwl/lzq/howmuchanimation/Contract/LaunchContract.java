package com.ctwl.lzq.howmuchanimation.Contract;

import com.ctwl.lzq.howmuchanimation.BasePresenter;
import com.ctwl.lzq.howmuchanimation.BaseView;

/**
 * Created by B41-80 on 2016/6/27.
 */
public interface LaunchContract {
    interface View extends BaseView<Presenter>{
        void showContent(String content);
    }
    interface Presenter extends BasePresenter{

    }
    interface DialogueView extends BaseView<DialogPresenter>{
        void showContnent(String taici,String from);
    }
    interface  DialogPresenter extends  BasePresenter{
        void loadingData();
    }
}
