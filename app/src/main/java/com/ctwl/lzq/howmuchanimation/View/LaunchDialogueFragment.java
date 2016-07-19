package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.BaseFragment;
import com.ctwl.lzq.howmuchanimation.Contract.LaunchContract;
import com.ctwl.lzq.howmuchanimation.Presenter.DialoguePresenter;
import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/7/5.
 */
public class LaunchDialogueFragment extends BaseFragment implements LaunchContract.DialogueView{
    private View view;
    private DialoguePresenter dialoguePresenter;
    private boolean isPrepare;
    private TextView taiciTextView;
    private TextView scoureTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_launch_dialogue,container,false);
        taiciTextView = (TextView) view.findViewById(R.id.taici_tv);
        scoureTextView = (TextView) view.findViewById(R.id.from_tv);
        isPrepare = true;
        lazyLoad();
        return view;
    }
    @Override
    protected void clearData() {
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepare){
            return;
        }
        dialoguePresenter = new DialoguePresenter(this);
        dialoguePresenter.loadingData();
    }

    @Override
    public void showContnent(String taici,String from) {
        taiciTextView.setText(taici);
        scoureTextView.setText("——"+from);
    }

    @Override
    public void setPresenter(LaunchContract.DialogPresenter presenter) {

    }
}
