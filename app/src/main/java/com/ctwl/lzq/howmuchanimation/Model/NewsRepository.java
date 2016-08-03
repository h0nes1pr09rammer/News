package com.ctwl.lzq.howmuchanimation.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctwl.lzq.howmuchanimation.BaseApi;
import com.ctwl.lzq.howmuchanimation.Callback.HttpCallBack;
import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Model.Bean.NewsType;
import com.ctwl.lzq.howmuchanimation.Utils.CommonUtils;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;
import com.ctwl.lzq.howmuchanimation.db.NewsDataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by B41-80 on 2016/6/28.
 */
public class NewsRepository implements NewsDataSource{

    public static final String ALL_NEWS_SIGN = "123";
    List<NewsType> newsTypeList;
    List<News> newsList;
    NewsDataBaseHelper mNewsDataBaseHelper;
    SQLiteDatabase mSqLiteDatabase;
    HashMap<String, String> map;

    public NewsRepository(Context context) {
        mNewsDataBaseHelper = new NewsDataBaseHelper(context,"News",null,1);
        mSqLiteDatabase = mNewsDataBaseHelper.getWritableDatabase();
        newsTypeList = new ArrayList<NewsType>();
        newsList = new ArrayList<News>();
        map = new HashMap<String,String>();
        map.put("apikey", BaseApi.MY_APPKEY);
    }

    @Override
    public void loadingNewsTypeData(JsonCallBack jsonCallBack) {
        if (CommonUtils.isWifi()){
            loadingNewsTypeDataFromNet(jsonCallBack);
        }else{
            loadingNewsTypeDataFromBd(jsonCallBack);
        }
    }

    @Override
    public int newsTypeNumber() {
        return newsTypeList.size();
    }

    @Override
    public List<NewsType> getNewsTpyeList() {
        return newsTypeList;
    }

    @Override
    public void loadingNews(final String channelId,String pageNumber, final JsonCallBack jsonCallBack) {
        if (CommonUtils.isWifi()){
            getNewsFromNet(channelId,pageNumber,jsonCallBack);
        }else{
            getNewsFromBd(channelId,pageNumber,jsonCallBack);
        }
    }

    /**
     * 获取新闻类型（网络）
     * @param jsonCallBack
     */
    public void loadingNewsTypeDataFromNet(final JsonCallBack jsonCallBack) {
        VolleyUtils.getInstance().postString(BaseApi.NEWS_API, map, null, new HttpCallBack() {
            @Override
            public void onSuccess(Object o) {
                toJson(o.toString());
                jsonCallBack.onSuccess();
            }

            @Override
            public void onFaile() {
                jsonCallBack.onFaile(404,"刷新失败");
            }
            private void toJson(String t) {
                JSONObject jsonObject = JSON.parseObject(t);
                jsonObject = JSON.parseObject(jsonObject.getString("showapi_res_body"));
                List<NewsType> newsTypes = JSON.parseArray(jsonObject.getString("channelList"),NewsType.class);
                newsTypeList.addAll(newsTypes);
                clearBd();
                for (NewsType newsType:newsTypes){
                    insertNewsTypeToBd(newsType);
                }
            }
        });
    }

    /**
     * 将数据存到本地
     * @param newsType
     */
    private void insertNewsTypeToBd(NewsType newsType){
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("channelId",newsType.getChannelId());
        mContentValues.put("name",newsType.getName());
        mSqLiteDatabase.insert("news_type",null,mContentValues);
    }

    /**
     * 获取新闻类型（本地）
     * @param jsonCallBack
     */
    public void loadingNewsTypeDataFromBd(JsonCallBack jsonCallBack) {
        Cursor mCursor = mSqLiteDatabase.query("news_type",null,null,null,null,null,null);
        for (mCursor.moveToFirst();!(mCursor.isAfterLast());mCursor.moveToNext()) {
            NewsType newsType = new NewsType();
            newsType.setChannelId(mCursor.getString(0));
            newsType.setName(mCursor.getString(1));
            newsTypeList.add(newsType);
        }
        if (newsTypeList.size()>0){
            jsonCallBack.onSuccess();
        }else{
            jsonCallBack.onFaile(404,"本地暂无数据");
        }
    }

    /**
     * 获取新闻内容（网络）
     * @param channelId
     * @param jsonCallBack
     */
    public void getNewsFromNet(final String channelId,String pageNumber, final JsonCallBack jsonCallBack) {
        String httpUrl;
        if (channelId.equals(ALL_NEWS_SIGN)){
            httpUrl = BaseApi.NEWS_CONENT_API +"?"+"page="+pageNumber;
        }else{
            httpUrl = BaseApi.NEWS_CONENT_API +"?" + "channelId=" + channelId+"&page="+pageNumber;
        }
        Log.i("getNewsFromNet",httpUrl);
        VolleyUtils.getInstance().postString(httpUrl, map, null, new HttpCallBack() {
            @Override
            public void onSuccess(Object o) {
                Log.v("getNewsFromNet",o.toString());
                toJson(o.toString());
                jsonCallBack.onSuccess();
            }

            @Override
            public void onFaile() {
                jsonCallBack.onFaile(404,"加载失败");
            }
            private void toJson(String t) {
                JSONObject jsonObject = JSON.parseObject(t);
                jsonObject = JSON.parseObject(jsonObject.getString("showapi_res_body"));
                jsonObject = JSON.parseObject(jsonObject.getString("pagebean"));
                List<News> newsTypes = JSON.parseArray(jsonObject.getString("contentlist"),News.class);
                newsList.clear();
                newsList.addAll(newsTypes);
                clearNews();
                insertNewsToBd(t,channelId);
            }
        });
    }

    private void insertNewsToBd(String t,String channelId){
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("content",t);
        mContentValues.put("channelId",channelId);
        mSqLiteDatabase.insert("news",null,mContentValues);
    }

    /**
     * 获取新闻内容（本地）
     * @param channelId
     * @param jsonCallBack
     */
    public void getNewsFromBd(String channelId,String pageNumber, JsonCallBack jsonCallBack) {
        Cursor mCursor = mSqLiteDatabase.query("news", new String[] { "channelId","content" },"channelId=?", new String[]{channelId}, null,null, null, null);
        for (mCursor.moveToFirst();!(mCursor.isAfterLast());mCursor.moveToNext()) {
            JSONObject jsonObject = JSON.parseObject(mCursor.getString(1));
            jsonObject = JSON.parseObject(jsonObject.getString("showapi_res_body"));
            jsonObject = JSON.parseObject(jsonObject.getString("pagebean"));
            List<News> newsTypes = JSON.parseArray(jsonObject.getString("contentlist"),News.class);
            newsList.addAll(newsTypes);
        }
        if (newsList.size()>0){
            Log.v("getNewsFromBd","success");
            jsonCallBack.onSuccess();
        }else{
            Log.v("getNewsFromBd","faile");
            jsonCallBack.onFaile(404,"本地暂无数据");
        }
    }

    @Override
    public void savaNewsType() {

    }

    @Override
    public void saveNews() {

    }

    @Override
    public void clearBd() {
        mSqLiteDatabase.execSQL("DELETE FROM news_type");
    }
    private void clearNews(){
        mSqLiteDatabase.execSQL("DELETE FROM news");
    }
    @Override
    public List<News> getNews() {
        return newsList;
    }
}
