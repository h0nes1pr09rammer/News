package com.ctwl.lzq.howmuchanimation.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctwl.lzq.howmuchanimation.BaseApi;
import com.ctwl.lzq.howmuchanimation.Callback.HttpCallBack;
import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Model.Bean.Constell;
import com.ctwl.lzq.howmuchanimation.Model.Bean.Dialogue;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;
import com.orhanobut.logger.Logger;


import java.util.HashMap;

/**
 * Created by B41-80 on 2016/7/5.
 */
public class ApiDataRepository implements DialogueDataSource {
    String taici;
    String show;
    String source;
    private static ApiDataRepository apiDataRepository;

    public static ApiDataRepository getInstance(){
        if (apiDataRepository == null){
            apiDataRepository = new ApiDataRepository();
        }
        return apiDataRepository;
    }

    public String getTaici() {
        return taici;
    }

    public String getShow() {
        return show;
    }

    public String getSource() {
        return source;
    }

    @Override
    public Dialogue getDialogue() {
        return null;
    }

    @Override
    public void loadingData(final JsonCallBack jsonCallBack) {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("apikey",BaseApi.MY_APPKEY);
        VolleyUtils.getInstance().postString(BaseApi.ZY_API, map,null, new HttpCallBack() {
            @Override
            public void onSuccess(Object o) {
                JSONObject jsonObject = JSON.parseObject(o.toString());
                taici = jsonObject.getString("taici");
                show = jsonObject.getString("show");
                source = jsonObject.getString("source");
                jsonCallBack.onSuccess();
            }

            @Override
            public void onFaile() {

            }
        });
    }

    @Override
    public void loadConstellData(final JsonCallBack jsonCallBack) {
        HashMap<String,String> headMap = new HashMap<String,String>();
        headMap.put("apikey",BaseApi.MY_APPKEY);
        String httpUrl = BaseApi.COLLEL_API+"?"+"consName=白羊座&type=today";
        VolleyUtils.getInstance().postString(httpUrl, headMap,null, new HttpCallBack() {
            @Override
            public void onSuccess(Object o) {
                Constell constell = JSON.parseObject(o.toString(),Constell.class);
                Logger.json(o.toString());
                Logger.i(constell.getSummary(),"");
                jsonCallBack.onSuccess();
            }

            @Override
            public void onFaile() {

            }
        });
    }
}
