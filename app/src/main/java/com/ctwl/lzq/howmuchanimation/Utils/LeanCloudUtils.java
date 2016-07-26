package com.ctwl.lzq.howmuchanimation.Utils;

import android.content.Context;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SignUpCallback;
import com.ctwl.lzq.howmuchanimation.BaseApi;
import com.ctwl.lzq.howmuchanimation.Callback.LoginCallback;

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
}
