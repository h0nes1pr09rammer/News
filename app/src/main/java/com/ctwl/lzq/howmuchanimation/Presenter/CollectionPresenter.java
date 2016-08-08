package com.ctwl.lzq.howmuchanimation.Presenter;

import com.avos.avoscloud.AVObject;
import com.ctwl.lzq.howmuchanimation.BaseApi;
import com.ctwl.lzq.howmuchanimation.Callback.HttpCallBack;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudFindCallback;
import com.ctwl.lzq.howmuchanimation.Model.Bean.News;
import com.ctwl.lzq.howmuchanimation.Utils.LeanCloudUtils;
import com.ctwl.lzq.howmuchanimation.View.CollectionPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h0nes1pr09rammer on 2016/8/8.
 */
public class CollectionPresenter {

    CollectionPagerFragment mCollectionPagerFragment;
    List<News> mNewsList;

    public CollectionPresenter(CollectionPagerFragment collectionPagerFragment) {
        mCollectionPagerFragment = collectionPagerFragment;
        mNewsList = new ArrayList<>();
    }

    public void downLoad(final HttpCallBack httpCallBack){
        LeanCloudUtils.findInBackground(BaseApi.COLLECTION_TABLE, new LeancloudFindCallback() {
            @Override
            public void onFindSuccess(List<News> list) {
                mNewsList.addAll(list);
                httpCallBack.onSuccess("");
            }

            @Override
            public void onFindFaile(String eor) {
                httpCallBack.onFaile();
            }
        });
    }
    public List<News> getNewsList(){
        return mNewsList;
    }
}
