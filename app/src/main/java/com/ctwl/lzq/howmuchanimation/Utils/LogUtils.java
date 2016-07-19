package com.ctwl.lzq.howmuchanimation.Utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by B41-80 on 2016/7/12.
 */
public class LogUtils {
    public static void i(String tag,String msg){
        Log.i(tag,msg);
        Logger.i(msg,"");
    }
}
