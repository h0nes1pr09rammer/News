package com.ctwl.lzq.howmuchanimation.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ctwl.lzq.howmuchanimation.Contract.MainContract;
import com.ctwl.lzq.howmuchanimation.Dialog.LoginDialog;
import com.ctwl.lzq.howmuchanimation.Presenter.MainPresenter;
import com.ctwl.lzq.howmuchanimation.Presenter.NewsPresenter;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.LogUtils;
import com.ctwl.lzq.howmuchanimation.Utils.UpdataUtils;
import com.ctwl.lzq.howmuchanimation.View.NewsFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.View{

    private ViewPager viewpager;
    private AppBarLayout appBarLayout;
    private CoordinatorLayout coordinatorLayout;
    private TabLayout tabLayout;
    private MainPagerAdapter mMainPagerAdapter;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MainPresenter newsPresenter;
    private List<NewsFragment> newsFragments;
    private LoginDialog mLoginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdataUtils.loadLib();
        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Slide());
        initPresenter();
        initView();
        initToolbar();
        initNavigationView();
        initActionBarDrawerToggle();
        newsPresenter.loadingData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initView() {
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewpager = (ViewPager) findViewById(R.id.viewPager);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("随便逛逛");
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager.setOffscreenPageLimit(4);
        tabLayout.setTabTextColors(getResources().getColor(R.color.whiteTrans80), getResources().getColor(R.color.white));
    }

    private void initNavigationView() {
        mNavigationView.inflateMenu(R.menu.navigation_menu);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                String itemTitle = item.getTitle().toString();
                if (itemTitle.equals("登录或注册")){
                    if (mLoginDialog == null){
                        mLoginDialog = new LoginDialog();
                    }
                    mLoginDialog.show(getSupportFragmentManager(),null);
                }else if (itemTitle.equals("QQ登录")){
                }else if (itemTitle.equals("微博登录")){
                }else if (itemTitle.equals("微信登录")){
                }else if (itemTitle.equals("切换主题")){

                }else if (itemTitle.equals("设置")){

                }
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void initActionBarDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }


    private void initPresenter() {
        newsPresenter = new MainPresenter(MainActivity.this,this);
    }


    @Override
    public void loadingDataSuccess() {
        viewpager.setAdapter(mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void showErrorMsg(String error) {
//        Snackbar.make(getCurrentFocus(),error,Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    public class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
            newsFragments = new ArrayList<NewsFragment>();
            for (int i = 0;i<newsPresenter.newsTypeNumber();i++){
                NewsFragment newsFragment = new NewsFragment(i,newsPresenter.getNewsType(i).getChannelId());
                NewsPresenter newsPresenter = new NewsPresenter(newsFragment,MainActivity.this);
                newsFragments.add(i,newsFragment);
            }
        }

        @Override
        public Fragment getItem(int position) {
            return newsFragments.get(position);
        }

        @Override
        public String getPageTitle(int position) {
            return newsPresenter.getNewsType(position).getName();
        }

        @Override
        public int getCount() {
            return newsPresenter.newsTypeNumber();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return super.isViewFromObject(view, object);
        }
    }

}
