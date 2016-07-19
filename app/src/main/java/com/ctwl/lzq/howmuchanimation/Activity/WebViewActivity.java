package com.ctwl.lzq.howmuchanimation.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ctwl.lzq.howmuchanimation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/14.
 */
public class WebViewActivity extends AppCompatActivity{
    @BindView(R.id.id_web_view)
    WebView mWebView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private WebSettings mWebSettings = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(getIntent().getStringExtra("url"));
    }
}
