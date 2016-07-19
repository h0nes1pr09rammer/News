package com.ctwl.lzq.howmuchanimation.Presenter;

import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Contract.LaunchContract;
import com.ctwl.lzq.howmuchanimation.Model.ApiDataRepository;
import com.ctwl.lzq.howmuchanimation.View.LaunchDialogueFragment;

/**
 * Created by B41-80 on 2016/7/5.
 */
public class DialoguePresenter implements LaunchContract.DialogPresenter{
    private LaunchDialogueFragment launchDialogueFragment;
    private ApiDataRepository apiDataRepository;
    public DialoguePresenter(LaunchDialogueFragment launchDialogueFragment) {
        this.launchDialogueFragment = launchDialogueFragment;
        apiDataRepository = new ApiDataRepository();

    }

    @Override
    public void loadingData() {
        apiDataRepository.loadingData(new JsonCallBack() {
            @Override
            public void onSuccess() {
                launchDialogueFragment.showContnent(apiDataRepository.getTaici(), apiDataRepository.getSource());
            }

            @Override
            public void onFaile(int errorNo, String strMsg) {

            }
        });
    }

    @Override
    public void start() {

    }
}
