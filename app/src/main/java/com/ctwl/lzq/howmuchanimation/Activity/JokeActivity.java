package com.ctwl.lzq.howmuchanimation.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.View.HomePagerFragment;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/7/5.
 */
public class JokeActivity extends AppCompatActivity{
    private final int JOKE_PAGE_ONE = 1;
    private final int JOKE_PAGE_TWO = 2;
    private final int JOKE_PAGE_THREE = 3;
    private final int JOKE_PAGE_FORE = 4;
    private final int JOKE_PAGE_FIVE = 5;
    private final int JOKE_PAGE_ZERO = 0;
    @BindView(R.id.joke_toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_joke_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.id_joke_nv_menu)
    NavigationView mNavigationView;
    ImageView mImageView;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    int pagerNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);
        initView();
        initToolbar();
        initNavigationView();
        initActionBarDrawerToggle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mImageView = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.head_img);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(JokeActivity.this,PersonInfoActivity.class));
                startActivity(new Intent(JokeActivity.this,PersonInfoActivity.class), ActivityOptions.makeSceneTransitionAnimation(JokeActivity.this,mImageView,"mybtn").toBundle());
                mDrawerLayout.closeDrawers();
            }
        });
        HomePagerFragment mHomePagerFragment = new HomePagerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_content,mHomePagerFragment).show(mHomePagerFragment).commit();
    }
    private void initActionBarDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initNavigationView() {
        mNavigationView.inflateMenu(R.menu.navigation_menu_login);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getTitle().toString()){
                    case "首页":
                        showHomePage(item);
                        break;
                    case "发现":
                        showDiscoverPage(item);
                        break;
                    case "关注":
                        showCarePage(item);
                        break;
                    case "收藏":
                        showCollectionPage(item);
                        break;
                    case "草稿":
                        showDraftPage(item);
                        break;
                    case "发布":
                        showReleasePage(item);
                        break;
                    case "切换主题":
                        showChagePage(item);
                        break;
                    case "设置":
                        showSetupPage(item);
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 设置Toolbar样式
     * @param item
     * @param visible
     */
    private void setToolbar(MenuItem item,int visible,int pageNumber) {
        item.setChecked(true);
        toolbar.setTitle(item.getTitle());
        this.pagerNumber = pageNumber;
        invalidateOptionsMenu();
        mDrawerLayout.closeDrawers();
    }

    private void showSetupPage(MenuItem item) {
    }

    private void showChagePage(MenuItem item) {
    }

    /**
     * 发布页面
     * @param item
     */
    private void showReleasePage(MenuItem item) {
        setToolbar(item, View.GONE,JOKE_PAGE_FIVE);
    }

    private void showDraftPage(MenuItem item) {
        setToolbar(item,View.GONE,JOKE_PAGE_FORE);
    }

    private void showCollectionPage(MenuItem item) {
        setToolbar(item,View.GONE,JOKE_PAGE_THREE);
    }

    private void showCarePage(MenuItem item) {
        setToolbar(item,View.GONE,JOKE_PAGE_TWO);
    }

    private void showDiscoverPage(MenuItem item) {
        setToolbar(item,View.GONE,JOKE_PAGE_ONE);
    }

    private void showHomePage(MenuItem item) {
        setToolbar(item,View.GONE,JOKE_PAGE_ZERO);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.i(item.getTitle().toString(),"");
        switch (item.getItemId()){
            case R.id.find:
                Snackbar.make(getCurrentFocus(),"搜索",Snackbar.LENGTH_LONG).show();
                break;
            case R.id.message:
                Snackbar.make(getCurrentFocus(),"消息",Snackbar.LENGTH_LONG).show();
                break;
            case R.id.about:
                Snackbar.make(getCurrentFocus(),"关于",Snackbar.LENGTH_LONG).show();
                break;
            case R.id.logout:
                Snackbar.make(getCurrentFocus(),"登出",Snackbar.LENGTH_LONG).show();
                break;
            case R.id.shuffle:
                Snackbar.make(getCurrentFocus(),"随机",Snackbar.LENGTH_LONG).show();
                break;
            case R.id.share:
                Snackbar.make(getCurrentFocus(),"分享",Snackbar.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (pagerNumber){
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
}
