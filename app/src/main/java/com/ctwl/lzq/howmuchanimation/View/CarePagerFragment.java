package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Adapter.CarePageAdapter;
import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class CarePagerFragment extends Fragment{
    private View mView;
    @BindView(R.id.care_page_recyclerview)
    RecyclerView mRecyclerView;

    CarePageAdapter mCarePageAdapter;
    List<String> mList;
    View mHeadView;
    View mFootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_care_pager,container,false);
        mHeadView = inflater.inflate(R.layout.item_care_head,container,false);
        mFootView = inflater.inflate(R.layout.item_care_foot,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList = Arrays.asList("国内焦点","国际焦点","军事焦点","财经焦点");
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //设置移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        mCarePageAdapter = new CarePageAdapter(mList,getActivity());
        mCarePageAdapter.setHeadView(mHeadView);
        mCarePageAdapter.setFootView(mFootView);
        mRecyclerView.setAdapter(mCarePageAdapter);
    }
}
