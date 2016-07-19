package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.Callback.JsonCallBack;
import com.ctwl.lzq.howmuchanimation.Model.ApiDataRepository;
import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/7/5.
 */
public class LaunchConstellationFragment extends Fragment {
    private View view;
    private TextView setUpBirthTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_launch_constellation,container,false);
        setUpBirthTextView = (TextView) view.findViewById(R.id.set_up_birth_day);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpBirthTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiDataRepository.getInstance().loadConstellData(new JsonCallBack() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFaile(int errorNo, String strMsg) {

                    }
                });
            }
        });
    }
}
