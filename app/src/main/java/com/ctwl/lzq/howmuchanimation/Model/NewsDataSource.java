package com.ctwl.lzq.howmuchanimation.Model;

import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Model.Data.News;
import com.ctwl.lzq.howmuchanimation.Model.Data.NewsType;

import java.util.List;

/**
 * Created by B41-80 on 2016/6/28.
 */
public interface NewsDataSource {
    void interpretingData(JsonCallBack jsonCallBack);
    int newsTypeNumber();
    List<NewsType> getNewsTpyeList();
    void getNews(String channelId,JsonCallBack jsonCallBack);
    List<News> getNews();
}
