package com.ctwl.lzq.howmuchanimation.Contract;

import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.BasePresenter;
import com.ctwl.lzq.howmuchanimation.BaseView;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;

import java.util.List;

/**
 * Created by B41-80 on 2016/6/28.
 */
public interface NewsContract {

    interface View extends BaseView<Presenter>{
        void setRefereshing(boolean refereshing);
        void waitLoading();
        void loadingSuccess();
        void showErrorMsg();
    }
    interface Presenter extends BasePresenter{
        int newsItemNumber();
        void setNewsContent(TextView textView,int itemContent);
        void loadingNews();
        void loadNews(String channelId);
        List<News> getNews();
    }

}
