package com.ctwl.lzq.howmuchanimation.Presenter;

import android.content.Context;

import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Contract.NewsContract;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Model.NewsRepository;

import java.util.List;

/**
 * Created by B41-80 on 2016/6/28.
 */
public class NewsPresenter implements NewsContract.Presenter{
    private NewsContract.View view;
    private NewsRepository newsRepository;

    public NewsPresenter(NewsContract.View newsView, Context context){
        view = newsView;
        view.setPresenter(this);
        newsRepository = new NewsRepository(context);
    }

    @Override
    public void start() {
    }

    @Override
    public void loadNews(String channelId,String pageNumber) {
        newsRepository.loadingNews(channelId,pageNumber, new JsonCallBack() {
            @Override
            public void onSuccess() {
                view.loadingSuccess();
            }

            @Override
            public void onFaile(int errorNo, String strMsg) {
                view.showErrorMsg(strMsg);
            }
        });
    }

    @Override
    public List<News> getNews() {
        return newsRepository.getNews();
    }

    @Override
    public void loadMore(String channelId,String pageNumber) {
        newsRepository.getNewsFromNet(channelId,pageNumber, new JsonCallBack() {
            @Override
            public void onSuccess() {
                view.loadingMoreSuccess();
            }

            @Override
            public void onFaile(int errorNo, String strMsg) {
                view.showErrorMsg(strMsg);
            }
        });
    }


}
