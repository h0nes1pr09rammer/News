package com.ctwl.lzq.howmuchanimation.Activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.LeanCloudUtils;
import com.ctwl.lzq.howmuchanimation.View.CarePagerFragment;
import com.ctwl.lzq.howmuchanimation.View.CollectionPagerFragment;
import com.ctwl.lzq.howmuchanimation.View.DiscoverPagerFragment;
import com.ctwl.lzq.howmuchanimation.View.DraftPagerFragment;
import com.ctwl.lzq.howmuchanimation.View.HomePagerFragment;
import com.ctwl.lzq.howmuchanimation.View.ReleasePagerFragment;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/5.
 */
public class JokeActivity extends AppCompatActivity {

    private final int JOKE_PAGE_ONE = 1;
    private final int JOKE_PAGE_TWO = 2;
    private final int JOKE_PAGE_THREE = 3;
    private final int JOKE_PAGE_FORE = 4;
    private final int JOKE_PAGE_FIVE = 5;
    private final int JOKE_PAGE_ZERO = 0;

    @BindView(R.id.joke_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.id_joke_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.id_joke_nv_menu)
    NavigationView mNavigationView;

    private ImageView mImageView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private int pagerNumber;
    private FragmentManager mFragmentManager;

    HomePagerFragment mHomePagerFragment;
    DiscoverPagerFragment mDiscoverPagerFragment;
    CollectionPagerFragment mCollectionPagerFragment;
    DraftPagerFragment mDraftPagerFragment;
    ReleasePagerFragment mReleasePagerFragment;
    CarePagerFragment mCarePagerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);
        initView();
        initToolbar();
        initNavigationView();
        initActionBarDrawerToggle();
        initFragment();
    }

    private void initFragment() {
        if (mDiscoverPagerFragment == null) {
            mDiscoverPagerFragment = new DiscoverPagerFragment();
        }
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fragment_content, mDiscoverPagerFragment).show(mDiscoverPagerFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mImageView = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.head_img);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JokeActivity.this, PersonInfoActivity.class), ActivityOptions.makeSceneTransitionAnimation(JokeActivity.this, mImageView, "mybtn").toBundle());
                mDrawerLayout.closeDrawers();
            }
        });
    }

    /**
     * 初始化ActionBarDrawerToggle
     */
    private void initActionBarDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    /**
     * 初始化NavigationView
     */
    private void initNavigationView() {
        mNavigationView.inflateMenu(R.menu.navigation_menu_login);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getTitle().toString().equals("首页")) {
                    showDiscoverPage(item);
                }
                if (item.getTitle().toString().equals("网友爆料")) {
                    showHomePage(item);
                }
                if (item.getTitle().toString().equals("我的关注")) {
                    showCarePage(item);
                }
                if (item.getTitle().toString().equals("我的收藏")) {
                    showCollectionPage(item);
                }
                if (item.getTitle().toString().equals("反馈")) {
                    showDraftPage(item);
                }
                if (item.getTitle().toString().equals("发布动态")) {
                    showReleasePage(item);
                }
                if (item.getTitle().toString().equals("切换主题")) {
                    showChagePage();
                }
                if (item.getTitle().toString().equals("设置")) {
                    showSetupPage();
                }
                return false;
            }
        });
    }

    /**
     * 设置Toolbar样式
     *
     * @param item
     */
    private void setToolbar(MenuItem item, int pageNumber) {
        item.setChecked(true);
        mToolbar.setTitle(item.getTitle());
        this.pagerNumber = pageNumber;
        invalidateOptionsMenu();
        mDrawerLayout.closeDrawers();
    }

    /**
     * 设置
     */
    private void showSetupPage() {
        startActivity(new Intent(JokeActivity.this, SetupPageActivity.class));
    }

    /**
     * 切换主题
     */
    private void showChagePage() {
        startActivity(new Intent(JokeActivity.this, SelecteImgActivity.class));
    }

    /**
     * 发布页面
     *
     * @param item
     */
    private void showReleasePage(MenuItem item) {
        setToolbar(item, JOKE_PAGE_FIVE);
        if (mReleasePagerFragment == null) {
            mReleasePagerFragment = new ReleasePagerFragment();
        }
        showFragment(mReleasePagerFragment);
    }

    /**
     * 显示草稿页面
     *
     * @param item
     */
    private void showDraftPage(MenuItem item) {
        setToolbar(item, JOKE_PAGE_FORE);
        if (mDraftPagerFragment == null) {
            mDraftPagerFragment = new DraftPagerFragment();
        }
        showFragment(mDraftPagerFragment);
    }

    /**
     * 显示收藏页面
     *
     * @param item
     */
    private void showCollectionPage(MenuItem item) {
        setToolbar(item, JOKE_PAGE_THREE);
        if (mCollectionPagerFragment == null) {
            mCollectionPagerFragment = new CollectionPagerFragment();
        }
        showFragment(mCollectionPagerFragment);
    }

    /**
     * 显示关注页面
     *
     * @param item
     */
    private void showCarePage(MenuItem item) {
        setToolbar(item, JOKE_PAGE_TWO);
        if (mCarePagerFragment == null) {
            mCarePagerFragment = new CarePagerFragment();
        }
        showFragment(mCarePagerFragment);
    }

    /**
     * 显示发现页面
     *
     * @param item
     */
    private void showDiscoverPage(MenuItem item) {
        setToolbar(item, JOKE_PAGE_ONE);
        if (mDiscoverPagerFragment == null) {
            mDiscoverPagerFragment = new DiscoverPagerFragment();
        }
        showFragment(mDiscoverPagerFragment);
    }

    /**
     * 显示首页
     *
     * @param item
     */
    private void showHomePage(MenuItem item) {
        setToolbar(item, JOKE_PAGE_ZERO);
        if (mHomePagerFragment == null) {
            mHomePagerFragment = new HomePagerFragment();
        }
        showFragment(mHomePagerFragment);
    }

    /**
     * 显示选中Fragment
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        FragmentTransaction mShowFragmentTransaction = mFragmentManager.beginTransaction();
        if (!mFragmentManager.getFragments().contains(fragment)) {
            mShowFragmentTransaction.add(R.id.fragment_content, fragment);
        }
        for (Fragment currentFragment : mFragmentManager.getFragments()) {
            mShowFragmentTransaction.hide(currentFragment);
        }
        mShowFragmentTransaction.show(fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.find) {

        }
        if (item.getItemId() == R.id.message) {

        }
        if (item.getItemId() == R.id.about) {

        }
        if (item.getItemId() == R.id.logout) {
            LeanCloudUtils.logOut();
        }
        if (item.getItemId() == R.id.shuffle) {

        }
        if (item.getItemId() == R.id.share) {

        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (pagerNumber) {
            case 0:
                menu.findItem(R.id.share).setVisible(false);
                menu.findItem(R.id.shuffle).setVisible(false);
                break;
            case 1:
                menu.findItem(R.id.share).setVisible(false);
                menu.findItem(R.id.shuffle).setVisible(true);
                break;
            case 2:
                menu.findItem(R.id.share).setVisible(false);
                menu.findItem(R.id.shuffle).setVisible(false);
                break;
            case 3:
                menu.findItem(R.id.share).setVisible(false);
                menu.findItem(R.id.shuffle).setVisible(false);
                break;
            case 4:
                menu.findItem(R.id.share).setVisible(false);
                menu.findItem(R.id.shuffle).setVisible(false);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
