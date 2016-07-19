package com.ctwl.lzq.howmuchanimation.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.Diy.DividerItemDecoration;
import com.ctwl.lzq.howmuchanimation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/14.
 */
public class PersonInfoActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        //Window.setSharedElementEnterTransition(new AutoTransition();//设置共享元素的进入动画
        //Window.setSharedElementExitTransition();//设置共享元素的退出动画
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mAppBarLayout.addOnOffsetChangedListener(this);
        mCollapsingToolbarLayout.setTitle("个人信息");
        //通过CollapsingToolbarLayout修改字体颜色
        //mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter());
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset==0){
            mToolbar.setOverflowIcon(getDrawable(R.mipmap.home_page_yellow));
            mToolbar.setNavigationIcon(R.mipmap.back_yellow);
        }else{
            mToolbar.setOverflowIcon(getDrawable(R.mipmap.home_page_white));
            mToolbar.setNavigationIcon(R.mipmap.back);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(getLayoutInflater().inflate(R.layout.ry_item_news,parent,false));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_person_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_page:
                Snackbar.make(getCurrentFocus(),"首页",Snackbar.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
