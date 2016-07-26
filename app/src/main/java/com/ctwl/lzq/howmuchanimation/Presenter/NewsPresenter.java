package com.ctwl.lzq.howmuchanimation.Presenter;

import android.widget.TextView;

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

    public NewsPresenter(NewsContract.View newsView){
        view = newsView;
        view.setPresenter(this);
        newsRepository = new NewsRepository();
    }

    @Override
    public void start() {
    }

    @Override
    public int newsItemNumber() {
        return newsRepository.getNews().size();
    }

    @Override
    public void setNewsContent(TextView textView,int itemContent) {
        if (!newsRepository.getNews().get(itemContent).getDesc().isEmpty()){
            textView.setText(newsRepository.getNews().get(itemContent).getDesc());
        }else{
            textView.setText("null");
        }

    }

    @Override
    public void loadingNews() {
        view.setRefereshing(false);
    }



    @Override
    public void loadNews(String channelId) {
        newsRepository.getNews(channelId, new JsonCallBack() {
            @Override
            public void onSuccess() {
                view.loadingSuccess();
            }

            @Override
            public void onFaile(int errorNo, String strMsg) {
                view.showErrorMsg();
            }
        });
    }

    @Override
    public List<News> getNews() {
        return newsRepository.getNews();
    }


}
