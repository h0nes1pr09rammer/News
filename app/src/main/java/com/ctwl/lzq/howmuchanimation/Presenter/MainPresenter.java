package com.ctwl.lzq.howmuchanimation.Presenter;

import android.content.Context;

import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Contract.MainContract;
import com.ctwl.lzq.howmuchanimation.Model.Bean.NewsType;
import com.ctwl.lzq.howmuchanimation.Model.NewsRepository;
import com.ctwl.lzq.howmuchanimation.main.MainActivity;

/**
 * Created by B41-80 on 2016/6/30.
 */
public class MainPresenter implements MainContract.Presenter{

    private NewsRepository newsRepository;
    Context mainActivity;
    MainContract.View mView;

    public MainPresenter(Context mainActivity,MainContract.View mView) {
        newsRepository = new NewsRepository(mainActivity);
        this.mainActivity = mainActivity;
        this.mView = mView;
    }

    @Override
    public int newsTypeNumber() {
        return newsRepository.newsTypeNumber();
    }

    @Override
    public NewsType getNewsType(int position) {
        return newsRepository.getNewsTpyeList().get(position);
    }

    @Override
    public void loadingData() {
        newsRepository.loadingNewsTypeData(new JsonCallBack() {
            @Override
            public void onSuccess() {
                mView.loadingDataSuccess();
            }

            @Override
            public void onFaile(int errorNo, String strMsg) {
                mView.showErrorMsg(strMsg);
            }
        });
    }


    @Override
    public void start() {

    }
}
