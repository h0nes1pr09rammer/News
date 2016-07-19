package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.BaseFragment;
import com.ctwl.lzq.howmuchanimation.R;
import com.orhanobut.logger.Logger;

/**
 * Created by B41-80 on 2016/6/27.
 */
public class LaunchComicFragment extends Fragment{
    private View view;
    public TextView appNameTextView;

    public LaunchComicFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_launch_comic,container,false);
        appNameTextView = (TextView) view.findViewById(R.id.app_name);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
    }

}
