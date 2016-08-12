package com.ctwl.lzq.howmuchanimation.Contract;

import com.ctwl.lzq.howmuchanimation.BasePresenter;
import com.ctwl.lzq.howmuchanimation.BaseView;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;

import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/12.
 */
public interface SendContract {
    interface View extends BaseView<Presenter> {
        void sendSuccess();
        void showErrorMsg(String msg);
    }
    interface Presenter extends BasePresenter {
        void send(String content,List<String> mList);
    }
}
