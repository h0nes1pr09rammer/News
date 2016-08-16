package com.ctwl.lzq.howmuchanimation;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ctwl.lzq.howmuchanimation.Utils.CommonUtils;
import com.ctwl.lzq.howmuchanimation.Utils.LeanCloudUtils;
import com.ctwl.lzq.howmuchanimation.Utils.UpdataUtils;
import com.ctwl.lzq.howmuchanimation.Utils.VolleyUtils;

/**
 * Created by B41-80 on 2016/6/27.
 */
public class BaseApplication extends Application{
    public static RequestQueue mQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        LeanCloudUtils.init(getApplicationContext());
        CommonUtils.init(getApplicationContext());
        mQueue = Volley.newRequestQueue(getApplicationContext());
        VolleyUtils.getInstance().initVolley(this);
    }
}
