package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.R;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class HomePagerFragment extends Fragment{
    private View view;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new MyAdapter());
    }
    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(getActivity().getLayoutInflater().inflate(R.layout.ry_item_news,parent,false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ((MyViewHolder)holder).textView.setText("1111");
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.news_tv);
        }
    }
}
