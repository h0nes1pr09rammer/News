package com.ctwl.lzq.howmuchanimation.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctwl.lzq.howmuchanimation.Contract.MainContract;
import com.ctwl.lzq.howmuchanimation.Presenter.MainPresenter;
import com.ctwl.lzq.howmuchanimation.Presenter.NewsPresenter;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class DiscoverPagerFragment extends Fragment implements MainContract.View{

    private View mView;
    private MainPresenter mPresenter;
    private List<NewsFragment> mNewsFragments;

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
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setTabTextColors(getResources().getColor(R.color.qianorange), getResources().getColor(R.color.orange));
        mPresenter = new MainPresenter(getActivity(),this);
        mPresenter.loadingData();
    }


    @Override
    public void loadingDataSuccess() {
        mViewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showErrorMsg(String msg) {

    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
            mNewsFragments = new ArrayList<NewsFragment>();
            for (int i = 0;i<mPresenter.newsTypeNumber();i++){
                NewsFragment newsFragment = new NewsFragment(i,mPresenter.getNewsType(i).getChannelId());
                NewsPresenter newsPresenter = new NewsPresenter(newsFragment,getActivity());
                mNewsFragments.add(i,newsFragment);
            }
        }

        @Override
        public Fragment getItem(int position) {

            if (mNewsFragments.get(position)!=null){
                return mNewsFragments.get(position);
            }else{
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPresenter.getNewsType(position).getName();
        }

        @Override
        public int getCount() {
            return mPresenter.newsTypeNumber();
        }
    }
}
