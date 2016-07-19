package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ctwl.lzq.howmuchanimation.Contract.LaunchContract;
import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/6/27.
 */
public class LaunchFateFragment extends Fragment implements LaunchContract.View {

    private View view;
    public ImageView qqImageView;
    public ImageView wbImageView;
    public ImageView wxImageView;
    private boolean isPrepared;

    public LaunchFateFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_launch_fate,container,false);
        qqImageView = (ImageView) view.findViewById(R.id.qq_ic);
        wbImageView = (ImageView) view.findViewById(R.id.qq_weibo);
        wxImageView = (ImageView) view.findViewById(R.id.qq_weixin);
        isPrepared = true;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //contentText.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.translate_bottom_to_center));
    }

    @Override
    public void setPresenter(LaunchContract.Presenter presenter) {

    }

    @Override
    public void showContent(String content) {
        //contentText.setText(content);
    }
}
