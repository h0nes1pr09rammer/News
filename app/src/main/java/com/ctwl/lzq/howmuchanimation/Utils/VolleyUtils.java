package com.ctwl.lzq.howmuchanimation.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ctwl.lzq.howmuchanimation.Callback.HttpCallBack;
import com.ctwl.lzq.howmuchanimation.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by B41-80 on 2016/7/11.
 */
public class VolleyUtils {
    public static VolleyUtils volleyUtils;
    public RequestQueue mQueue;
    private Context context;
    public void initVolley(Context context){
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
    }
    public static VolleyUtils getInstance(){
        if (volleyUtils == null){
            volleyUtils = new VolleyUtils();
        }
        return volleyUtils;
    }
    public void getString(String url, final HttpCallBack httpCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                httpCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpCallBack.onFaile();
            }
        });
        mQueue.add(stringRequest);
    }
    public void postString(String url,final HashMap<String,String> headParam,
                           final HashMap<String,String> urlParam, final HttpCallBack httpCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                httpCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpCallBack.onFaile();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headParam;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return urlParam;
            }
        };
        mQueue.add(stringRequest);
    }
    public void getImage(String url, final ImageView imageView, final HttpCallBack httpCallBack){
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                if (imageView!=null){
                    imageView.setImageBitmap(response);
                }
                httpCallBack.onSuccess(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpCallBack.onFaile();
            }
        });
        mQueue.add(imageRequest);
    }
    public void disPlayImageView(String url, final ImageView imageView){
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                if (imageView!=null){
                    imageView.setImageBitmap(response);
                }
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.iu));
            }
        });
        mQueue.add(imageRequest);
    }
}