package com.ctwl.lzq.howmuchanimation.Utils;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.ctwl.lzq.howmuchanimation.BaseApi;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudFindCallback;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudSaveCallback;
import com.ctwl.lzq.howmuchanimation.Callback.LoginCallback;
import com.ctwl.lzq.howmuchanimation.Model.Bean.ImageUrls;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by B41-80 on 2016/7/1.
 */
public class LeanCloudUtils {
    public static void init(Context context){
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(context, BaseApi.APPLICAION_ID,BaseApi.CLIENT_ID);
    }
    public static void login(String phoneNumber, String password, final LoginCallback loginCallback){
        AVUser.logInInBackground(phoneNumber, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e==null){
                    loginCallback.onSuccess();
                }else{
                    loginCallback.onFaile(e);
                }
            }
        });
    }
    public static void sendSMSCode(String phoneNumber, final LoginCallback loginCallback){
        AVOSCloud.requestSMSCodeInBackground(phoneNumber, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                // 发送失败可以查看 e 里面提供的信息
                if (e==null){
                    loginCallback.onSuccess();
                }else{
                    loginCallback.onFaile(e);
                }
            }
        });
    }
    public static void loginByVerifyCode(String phoneNumber, String mVerifyCode, final LoginCallback loginCallback){
        AVUser.signUpOrLoginByMobilePhoneInBackground(phoneNumber, mVerifyCode, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                // 如果 e 为空就可以表示登录成功了，并且 user 是一个全新的用户
                if (e == null){
                    loginCallback.onSuccess();
                }else{
                    loginCallback.onFaile(e);
                }
            }
        });
    }
    public static void logon(String phoneNumber, String password, String name, final LoginCallback loginCallback){
        AVUser user = new AVUser();
        user.setUsername(phoneNumber);
        user.setPassword(password);
        //user.setEmail("hang@leancloud.rocks");
        // 其他属性可以像其他AVObject对象一样使用put方法添加
        //user.put("mobilePhoneNumber", "186-1234-0000");
        user.put("name",name);
        user.signUpInBackground(new SignUpCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // successfully
                    loginCallback.onSuccess();
                } else {
                    // failed
                    loginCallback.onFaile(e);
                }
            }
        });
    }
    public void bindingPhoneNumber(String phoneNumber,final LoginCallback loginCallback){
        AVUser.getCurrentUser().put("mobilePhoneNumber",phoneNumber);
        AVUser.getCurrentUser().signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    loginCallback.onSuccess();
                }else{
                    loginCallback.onFaile(e);
                }
            }
        });
    }
    public void loginByPhoneNumber(String phoneNumber, String password, final LoginCallback loginCallback){
        AVUser.loginByMobilePhoneNumberInBackground(phoneNumber, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e==null){
                    loginCallback.onSuccess();
                }else{
                    loginCallback.onFaile(e);
                }
            }
        });
    }
    public static boolean isLogin(){
        if (AVUser.getCurrentUser()==null){
            Log.i("LeanCloudUtils","no login");
            return true;
        }else{
            Log.i("LeanCloudUtils","login");
            return false;
        }
    }
    public static void logOut(){
        AVUser.getCurrentUser().logOut();
    }
    public static void saveInBackground(String name, Map<String,Object> map,final LeancloudSaveCallback mLeancloudSaveCallback){
        AVObject todo = new AVObject(name);
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            todo.put(entry.getKey().toString(), entry.getValue());
        }
        todo.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 存储成功
                    mLeancloudSaveCallback.onSaveSuccess();
                } else {
                    // 失败的话，请检查网络环境以及 SDK 配置是否正确
                    mLeancloudSaveCallback.onSaveFaile(e.toString());
                }
            }
        });
    }
    public static void findInBackground(String name,final LeancloudFindCallback mLeancloudFindCallback){
        AVQuery<AVObject> query = new AVQuery<>(name);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e==null&&list!=null){
                    List<News> mNewsList = new ArrayList<News>();
                    for (int i = 0;i<list.size();i++){
                        mNewsList.add(toNews(list.get(i)));
                    }
                    mLeancloudFindCallback.onFindSuccess(mNewsList);
                }else{
                    mLeancloudFindCallback.onFindFaile(e.toString());
                }
            }
        });
    }
    private static News toNews(AVObject avObject) {
        News news = new News();
        news.setChannelId(avObject.getString("channelId"));
        news.setChannelName(avObject.getString("channelName"));
        news.setDesc(avObject.getString("desc"));
//        Log.v("toNews",JSON.parseObject(avObject.getString("imagesUrls")).toString());
        news.setImageurls(JSON.parseArray(avObject.getString("imagesUrls"),ImageUrls.class));
        news.setImagesNumber(Integer.valueOf(avObject.getString("imagesNumber")));
        news.setLink(avObject.getString("link"));
        news.setPubDate(avObject.getString("pubDate"));
        news.setSource(avObject.getString("source"));
        news.setTitle(avObject.getString("title"));
        return news;
    }
    public static void upDataUserHead(String path,String tableName,String cloumName,final LeancloudSaveCallback mLeancloudSaveCallback){
        AVFile avFile = new AVFile(Time.getCurrentTimezone(),BitmapUtils.Bitmap2Bytes(ImageLoaderUtils.getInstance().decodeSampledBitmapFromNative(path,100,100)));
        // 第一参数是 className,第二个参数是 objectId
        AVObject todo = AVObject.createWithoutData(tableName, AVUser.getCurrentUser().getObjectId());
        // 修改 content
        todo.put(cloumName,avFile);
        // 保存到云端
        todo.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e==null){
                    mLeancloudSaveCallback.onSaveSuccess();
                }else{
                    mLeancloudSaveCallback.onSaveFaile(e.toString());
                }
            }
        });
    }
}
