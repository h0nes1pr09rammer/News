package com.ctwl.lzq.howmuchanimation.Presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVObject;
import com.ctwl.lzq.howmuchanimation.BaseApi;
import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudFindCallback;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudSaveCallback;
import com.ctwl.lzq.howmuchanimation.Contract.NewsContract;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Model.NewsRepository;
import com.ctwl.lzq.howmuchanimation.Utils.LeanCloudUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void upLoad(News news) {
        Map<String,Object> map = new HashMap<>();
        map.put("title",news.getTitle());
        map.put("pubDate",news.getPubDate());
        map.put("source",news.getSource());
        map.put("channelName",news.getChannelName());
        map.put("imagesUrls", JSON.toJSONString(news.getImageurls()));
        map.put("link",news.getLink());
        map.put("channelId",news.getChannelId());
        map.put("desc",news.getDesc());
        map.put("imagesNumber",String.valueOf(news.getImagesNumber()));
        LeanCloudUtils.saveInBackground(BaseApi.COLLECTION_TABLE, map, new LeancloudSaveCallback() {
            @Override
            public void onSaveSuccess() {
                view.showErrorMsg("success");
            }

            @Override
            public void onSaveFaile(String eor) {
                view.showErrorMsg(eor);
            }
        });
    }
}
