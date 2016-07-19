package com.ctwl.lzq.howmuchanimation.Contract;

import com.ctwl.lzq.howmuchanimation.BasePresenter;
import com.ctwl.lzq.howmuchanimation.BaseView;

/**
 * Created by B41-80 on 2016/7/1.
 */
public interface LoginContract {
    interface LoginCallback{
        void onSuccess();
        void onFaile();
    }
    interface View extends BaseView<Presenter>{
        void loginSuccess();
        void showErrorMsg(String reponse);
        void showPhoneError();
        void logonSuccess();
    }
    interface Presenter extends BasePresenter{
        void login(String phoneNumber,String password);
        void sendSMSCode(String phoneNumber);
        void logon(String phoneNumber,String password,String name);
    }
}
