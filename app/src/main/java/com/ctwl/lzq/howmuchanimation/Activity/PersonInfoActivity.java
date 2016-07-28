package com.ctwl.lzq.howmuchanimation.Activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.LogUtils;
import com.ctwl.lzq.howmuchanimation.View.PersonDataFragment;
import com.ctwl.lzq.howmuchanimation.View.PersonLogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/14.
 */
public class PersonInfoActivity extends AppCompatActivity{

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.person_info_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.person_info_viewpager)
    ViewPager mViewPager;

    private PersonDataFragment mPersonDataFragment;
    private PersonLogFragment mPersonLogFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setOverflowIcon(getDrawable(R.mipmap.home_page_white));
        mToolbar.setNavigationIcon(R.mipmap.back);
        mCollapsingToolbarLayout.setTitle("个人信息");
        //通过CollapsingToolbarLayout修改字体颜色
        //mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setAdapter(new MyAdapter());
        mViewPager.setAdapter(new PersonerViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setTabTextColors(getResources().getColor(R.color.whiteTrans80), getResources().getColor(R.color.orange));
        mTabLayout.setupWithViewPager(mViewPager);
    }


    public class PersonerViewPagerAdapter extends FragmentPagerAdapter {

        public PersonerViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position==0){
                if (mPersonDataFragment == null){
                    mPersonDataFragment = new PersonDataFragment();
                }
                return mPersonDataFragment;
            }else {
                if (mPersonLogFragment == null){
                    mPersonLogFragment = new PersonLogFragment();
                }
                return mPersonLogFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String mPageTitle;
            if (position==0){
                mPageTitle = "个人信息";
            }else{
                mPageTitle = "个人动态";
            }
            return mPageTitle;
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
