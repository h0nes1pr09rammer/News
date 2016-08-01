package com.ctwl.lzq.howmuchanimation.Model;

import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Model.Bean.NewsType;

import java.util.List;

/**
 * Created by B41-80 on 2016/6/28.
 */
public interface NewsDataSource {
    //void loadingNewsTypeDataFromNet(JsonCallBack jsonCallBack);
    //void loadingNewsTypeDataFromBd(JsonCallBack jsonCallBack);
    void loadingNewsTypeData(JsonCallBack jsonCallBack);
    int newsTypeNumber();
    List<NewsType> getNewsTpyeList();
    void loadingNews(final String channelId, final JsonCallBack jsonCallBack);
    //void getNewsFromNet(String channelId,JsonCallBack jsonCallBack);
    //void getNewsFromBd(String channelId,JsonCallBack jsonCallBack);
    void savaNewsType();
    void saveNews();
    void clearBd();
    List<News> getNews();
}
