package com.ctwl.lzq.howmuchanimation.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ctwl.lzq.howmuchanimation.BaseApi;
import com.ctwl.lzq.howmuchanimation.BaseApplication;
import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Model.Bean.NewsType;
import com.ctwl.lzq.howmuchanimation.Utils.LogUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by B41-80 on 2016/6/28.
 */
public class NewsRepository implements NewsDataSource{
    List<NewsType> newsTypeList;
    List<News> newsList;

    public NewsRepository() {
    }

    @Override
    public void interpretingData(final JsonCallBack jsonCallBack) {
        newsTypeList = new ArrayList<NewsType>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BaseApi.NEWS_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.json(response);
                toJson(response);
                jsonCallBack.onSuccess();
            }
            private void toJson(String t) {
                JSONObject jsonObject = JSON.parseObject(t);
                jsonObject = JSON.parseObject(jsonObject.getString("showapi_res_body"));
                List<NewsType> newsTypes = JSON.parseArray(jsonObject.getString("channelList"),NewsType.class);
                newsTypeList.addAll(newsTypes);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String,String>();
                map.put("apikey", BaseApi.MY_APPKEY);
                return map;
            }
        };
        BaseApplication.mQueue.add(stringRequest);
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
    public void getNews(String channelId, final JsonCallBack jsonCallBack) {
        newsList = new ArrayList<News>();
        String httpUrl = BaseApi.NEWS_CONENT_API + "?" + "channelId=" + channelId;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.json(response);
                toJson(response);
                jsonCallBack.onSuccess();
            }
            private void toJson(String t) {
                JSONObject jsonObject = JSON.parseObject(t);
                jsonObject = JSON.parseObject(jsonObject.getString("showapi_res_body"));
                jsonObject = JSON.parseObject(jsonObject.getString("pagebean"));
                List<News> newsTypes = JSON.parseArray(jsonObject.getString("contentlist"),News.class);
                for (int i = 0;i<newsTypes.size();i++){
                    LogUtils.i("Repository",""+newsTypes.get(i).getImagesNumber());
                }
                newsList.addAll(newsTypes);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("apikey",BaseApi.MY_APPKEY);
                return map;
            }
        };
        BaseApplication.mQueue.add(stringRequest);
    }

    @Override
    public List<News> getNews() {
        return newsList;
    }
}
