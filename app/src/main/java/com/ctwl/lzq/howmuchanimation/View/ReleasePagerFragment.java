package com.ctwl.lzq.howmuchanimation.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ctwl.lzq.howmuchanimation.Activity.SelecteImgActivity;
import com.ctwl.lzq.howmuchanimation.Adapter.AddImageViewAdapter;
import com.ctwl.lzq.howmuchanimation.Diy.DividerGridItemDecoration;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class ReleasePagerFragment extends Fragment{

    @BindView(R.id.add_img_rv)
    RecyclerView mRecyclerView;

    List<String> mList;
    AddImageViewAdapter mAddImageViewAdapter;
    View footView;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("ReleasePagerFragment",data.getStringArrayListExtra("select_img").get(0));
        mList.addAll(data.getStringArrayListExtra("select_img"));
        mAddImageViewAdapter.notifyDataSetChanged();
    }

    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_release_pager,container,false);
        footView = inflater.inflate(R.layout.item_image,container,false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(getActivity())/4
                ,ScreenUtils.getScreenWidth(getActivity())/4);

        footView.setLayoutParams(layoutParams);
        footView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), SelecteImgActivity.class),1);
            }
        });
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList = new ArrayList<>();
        mAddImageViewAdapter = new AddImageViewAdapter(getActivity(),mList);
        mAddImageViewAdapter.setFootView(footView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAddImageViewAdapter);
    }
}
