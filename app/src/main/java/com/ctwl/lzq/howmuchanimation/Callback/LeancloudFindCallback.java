package com.ctwl.lzq.howmuchanimation.Callback;

import com.avos.avoscloud.AVObject;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;

import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/8.
 */
public interface LeancloudFindCallback {
    void onFindSuccess(List<News> list);
    void onFindFaile(String eor);
}
