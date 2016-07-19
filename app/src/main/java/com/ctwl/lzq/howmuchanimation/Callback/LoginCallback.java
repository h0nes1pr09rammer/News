package com.ctwl.lzq.howmuchanimation.Callback;

import com.avos.avoscloud.AVException;

/**
 * Created by B41-80 on 2016/7/1.
 */
public interface LoginCallback {
    void onSuccess();
    void onFaile(AVException e);
}
