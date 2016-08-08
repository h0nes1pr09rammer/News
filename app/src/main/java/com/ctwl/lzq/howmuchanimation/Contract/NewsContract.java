package com.ctwl.lzq.howmuchanimation.Contract;

import com.ctwl.lzq.howmuchanimation.BasePresenter;
import com.ctwl.lzq.howmuchanimation.BaseView;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;

import java.util.List;

/**
 * Created by B41-80 on 2016/6/28.
 */
public interface NewsContract {

    interface View extends BaseView<Presenter>{
        void loadingMoreSuccess();
        void loadingSuccess();
        void showErrorMsg(String msg);
    }
    interface Presenter extends BasePresenter{
        void loadNews(String channelId,String pageNumber);
        List<News> getNews();
        void loadMore(String channelId,String pageNumber);
        void upLoad(News news);
    }

}
