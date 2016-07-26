package com.ctwl.lzq.howmuchanimation.Presenter;

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
    MainActivity mainActivity;

    public MainPresenter(MainActivity mainActivity) {
        newsRepository = new NewsRepository();
        this.mainActivity = mainActivity;
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
    public void LoadingData() {
        newsRepository.interpretingData(new JsonCallBack() {
            @Override
            public void onSuccess() {
                mainActivity.loadingDataSuccess();
            }

            @Override
            public void onFaile(int errorNo, String strMsg) {
                mainActivity.showErrorMsg(strMsg);
            }
        });
    }


    @Override
    public void start() {

    }
}
