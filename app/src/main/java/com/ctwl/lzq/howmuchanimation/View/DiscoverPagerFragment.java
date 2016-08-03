package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class DiscoverPagerFragment extends Fragment{
    private View mView;
    private List<String> mHomeTabTitle = new ArrayList<>(Arrays.asList("热门推荐","热门收藏","本月热榜","今日热榜"));
    DiscoverStPagerFragment mDiscoverStPagerFragment;

    @BindView(R.id.home_page_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.home_page_tab_layout)
    TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_discover_pager,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }
    public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position==0){

            }else if (position==1){

            }else if (position==2){

            }else{

            }
            return new DiscoverStPagerFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mHomeTabTitle.get(position);
        }

        @Override
        public int getCount() {
            return mHomeTabTitle.size();
        }
    }
}
