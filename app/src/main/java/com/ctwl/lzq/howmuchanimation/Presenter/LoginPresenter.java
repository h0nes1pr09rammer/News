package com.ctwl.lzq.howmuchanimation.Presenter;

import com.avos.avoscloud.AVException;
import com.ctwl.lzq.howmuchanimation.Callback.LoginCallback;
import com.ctwl.lzq.howmuchanimation.Contract.LoginContract;
import com.ctwl.lzq.howmuchanimation.Dialog.LoginDialog;
import com.ctwl.lzq.howmuchanimation.Utils.LeanCloudUtils;
import com.ctwl.lzq.howmuchanimation.Utils.SmsUtil;

/**
 * Created by B41-80 on 2016/7/1.
 */
public class LoginPresenter implements LoginContract.Presenter {
    LoginDialog loginDialog;
    public LoginPresenter(LoginDialog loginDialog) {
        this.loginDialog = loginDialog;
    }

    @Override
    public void start() {

    }

    @Override
    public void login(String phoneNumber, String password) {
        if(SmsUtil.patternPhoneNumber(phoneNumber)){
            LeanCloudUtils.login(phoneNumber, password, new LoginCallback() {
                @Override
                public void onSuccess() {
                    loginDialog.loginSuccess();
                }

                @Override
                public void onFaile(AVException e) {
                    loginDialog.showErrorMsg(e.toString());
                }
            });
        }else{
            loginDialog.showPhoneError();
        }

    }

    @Override
    public void sendSMSCode(String phoneNumber) {
        LeanCloudUtils.sendSMSCode(phoneNumber, new LoginCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFaile(AVException e) {

            }
        });
    }

    @Override
    public void logon(String phoneNumber, String password, String name) {
        LeanCloudUtils.logon(phoneNumber, password, name, new LoginCallback() {
            @Override
            public void onSuccess() {
                loginDialog.logonSuccess();
            }

            @Override
            public void onFaile(AVException e) {
                loginDialog.showErrorMsg(e.toString());
            }
        });
    }
}
