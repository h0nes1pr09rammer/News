package com.ctwl.lzq.howmuchanimation.ViewPageTransformer;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.LogUtils;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by B41-80 on 2016/7/26.
 */
public class DepthPagetransformer implements ViewPager.PageTransformer
{

    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position)
    {
        LogUtils.i("DepthPagetransformer",view.getTag().toString());
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1)
        { // [-Infinity,-1)
            ViewHelper.setAlpha(view, 0);
        } else if (position <= 0)// a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
        { // [-1,0]
            if (view.getTag().toString().equals("fate")||view.getTag().toString()=="fate"){
                ViewHelper.setTranslationY(view.findViewById(R.id.qq_ic), pageHeight * position);
                ViewHelper.setTranslationX(view.findViewById(R.id.qq_ic), -pageWidth * position);
                ViewHelper.setTranslationX(view.findViewById(R.id.qq_weibo), pageWidth * position);
                ViewHelper.setTranslationY(view.findViewById(R.id.qq_weixin),-pageHeight * position);
                ViewHelper.setTranslationX(view.findViewById(R.id.qq_weixin), -pageWidth * position);
            }
            ViewHelper.setAlpha(view, 1);
        } else if (position <= 1)
        { // (0,1]
            if (view.getTag().toString().equals("comic")||view.getTag().toString()=="comic"){
                ViewHelper.setTranslationY(view.findViewById(R.id.app_name), pageHeight * position);
            }
            if (view.getTag().toString().equals("constellation")||view.getTag().toString()=="constellation"){
                ViewHelper.setTranslationY(view.findViewById(R.id.set_up_birth_day), pageHeight * position);
            }
            if (view.getTag().toString().equals("dialogue")||view.getTag().toString()=="dialogue"){
                ViewHelper.setTranslationY(view.findViewById(R.id.taici_tv), pageHeight * position);
                ViewHelper.setTranslationY(view.findViewById(R.id.from_tv), pageHeight * position);
            }
            ViewHelper.setAlpha(view, 1);
        } else
        { // (1,+Infinity]
            ViewHelper.setAlpha(view, 0);
        }
    }
}
