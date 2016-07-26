package com.ctwl.lzq.howmuchanimation.Contract;

import com.ctwl.lzq.howmuchanimation.BasePresenter;
import com.ctwl.lzq.howmuchanimation.BaseView;
import com.ctwl.lzq.howmuchanimation.Model.Bean.NewsType;

/**
 * Created by B41-80 on 2016/6/30.
 */
public interface MainContract {
    interface View extends BaseView<Presenter> {
        void setRefereshing(boolean refereshing);
        void waitLoading();
        void loadingDataSuccess();
        void showErrorMsg(String error);
    }
    interface Presenter extends BasePresenter {
        int newsTypeNumber();
        NewsType getNewsType(int position);
        void LoadingData();
    }
}
