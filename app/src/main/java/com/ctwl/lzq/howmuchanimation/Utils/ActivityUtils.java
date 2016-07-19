package com.ctwl.lzq.howmuchanimation.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by B41-80 on 2016/6/27.
 */
public class ActivityUtils {
    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int frameId){
       if (fragmentManager!=null&&fragment!=null){
           FragmentTransaction transaction = fragmentManager.beginTransaction();
           transaction.add(frameId,fragment);
           transaction.commit();
       }
    }
}
