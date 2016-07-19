package com.ctwl.lzq.howmuchanimation.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.Activity.JokeActivity;
import com.ctwl.lzq.howmuchanimation.Contract.LoginContract;
import com.ctwl.lzq.howmuchanimation.Presenter.LoginPresenter;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.main.MainActivity;

/**
 * Created by B41-80 on 2016/7/1.
 */
public class LoginDialog extends DialogFragment implements View.OnClickListener ,LoginContract.View{
    TextView logon;
    TextView login;
    TextView otherLogin;
    TextView mLogin;
    View view;
    LinearLayout otherLoginLy;
    EditText usernameEt;
    EditText passwordEt;
    EditText nameEt;
    TextView title;
    TextView fnLogon;
    TextView loginQQ;
    TextView loginWb;
    TextView loginWx;
    LoginPresenter loginPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog_login);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_login,container,false);
        logon = (TextView) view.findViewById(R.id.logon);
        login = (TextView) view.findViewById(R.id.login);
        otherLogin = (TextView) view.findViewById(R.id.other_login);
        mLogin = (TextView) view.findViewById(R.id.m_login);
        otherLoginLy = (LinearLayout) view.findViewById(R.id.other_login_ly);
        usernameEt = (EditText) view.findViewById(R.id.username);
        passwordEt = (EditText) view.findViewById(R.id.password);
        nameEt = (EditText) view.findViewById(R.id.name);
        title = (TextView) view.findViewById(R.id.title);
        fnLogon = (TextView) view.findViewById(R.id.finnish_logon);
        loginQQ = (TextView) view.findViewById(R.id.login_qq);
        loginWb = (TextView) view.findViewById(R.id.login_weibo);
        loginWx = (TextView) view.findViewById(R.id.login_weixin);
        loginQQ.setOnClickListener(this);
        loginWx.setOnClickListener(this);
        loginWb.setOnClickListener(this);
        fnLogon.setOnClickListener(this);
        logon.setOnClickListener(this);
        login.setOnClickListener(this);
        otherLogin.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addUsernameListener();
        loginPresenter = new LoginPresenter(this);
    }

    private void addUsernameListener() {
        usernameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    passwordEt.setVisibility(View.VISIBLE);
                }else{
                    passwordEt.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                login();
                break;
            case R.id.other_login:
                showOtherLogin();
                break;
            case R.id.logon:
                showLogonView();
                break;
            case R.id.m_login:
                showLoginView();
                break;
            case R.id.finnish_logon:
                logon();
                break;
        }
    }

    private void logon() {
        loginPresenter.logon(usernameEt.getText().toString(),passwordEt.getText().toString(),nameEt.getText().toString());
    }

    private void showLogonView() {
        title.setText("注册");
        passwordEt.setVisibility(View.VISIBLE);
        nameEt.setVisibility(View.VISIBLE);
        usernameEt.setVisibility(View.VISIBLE);
        logon.setVisibility(View.GONE);
        otherLogin.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        fnLogon.setVisibility(View.VISIBLE);
        otherLoginLy.setVisibility(View.GONE);
        mLogin.setVisibility(View.GONE);
    }

    private void showLoginView() {
        title.setText("登录");
        passwordEt.setVisibility(View.GONE);
        nameEt.setVisibility(View.GONE);
        usernameEt.setVisibility(View.VISIBLE);
        logon.setVisibility(View.VISIBLE);
        otherLogin.setVisibility(View.GONE);
        login.setVisibility(View.VISIBLE);
        fnLogon.setVisibility(View.GONE);
        otherLoginLy.setVisibility(View.GONE);
        mLogin.setVisibility(View.GONE);
    }

    private void showOtherLogin() {
        title.setText("社交账号登录");
        passwordEt.setVisibility(View.GONE);
        nameEt.setVisibility(View.GONE);
        usernameEt.setVisibility(View.GONE);
        logon.setVisibility(View.VISIBLE);
        otherLogin.setVisibility(View.GONE);
        login.setVisibility(View.GONE);
        fnLogon.setVisibility(View.GONE);
        otherLoginLy.setVisibility(View.VISIBLE);
        mLogin.setVisibility(View.VISIBLE);
    }

    private void login() {
        loginPresenter.login(usernameEt.getText().toString(),passwordEt.getText().toString());
    }

    @Override
    public void loginSuccess() {
        dismiss();
        startActivity(new Intent(getActivity(), JokeActivity.class));
        getActivity().finish();
    }

    @Override
    public void showErrorMsg(String reponse) {
        dismiss();
        Snackbar snackbar= Snackbar.make(getActivity().findViewById(R.id.fragment_content),reponse,Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    @Override
    public void showPhoneError() {
        Snackbar snackbar= Snackbar.make(getActivity().findViewById(R.id.fragment_content),"请输入正确手机号",Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    @Override
    public void logonSuccess() {
        dismiss();
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
