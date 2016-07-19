package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class DiscoverPagerFragment extends Fragment{
    private View mView;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_discover_pager,container,false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_recyclerview);
        return mView;
    }
}
