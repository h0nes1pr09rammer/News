package com.ctwl.lzq.howmuchanimation.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.Dialog.LoginDialog;
import com.ctwl.lzq.howmuchanimation.Diy.ViewPagerIndicate;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.View.LaunchComicFragment;
import com.ctwl.lzq.howmuchanimation.View.LaunchConstellationFragment;
import com.ctwl.lzq.howmuchanimation.View.LaunchDialogueFragment;
import com.ctwl.lzq.howmuchanimation.View.LaunchFateFragment;
import com.ctwl.lzq.howmuchanimation.ViewPageTransformer.DepthPagetransformer;
import com.ctwl.lzq.howmuchanimation.main.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by B41-80 on 2016/6/27.
 */
public class LaunchActivity extends AppCompatActivity implements ViewPagerIndicate.OnSelectedPageListener{
    LaunchFateFragment launchFragment;
    ViewPager viewpager;
    LaunchComicFragment launchComicFragment;
    Button mGoMainBt;
    Button login;
    LoginDialog loginDialog;
    ViewPagerIndicate viewPagerIndicate;
    TextView contentTextView;
    LaunchDialogueFragment launchDialogueFragment;
    LaunchConstellationFragment launchConstellationFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initView();
    }

    private void initView() {
        launchFragment = new LaunchFateFragment();
        viewPagerIndicate = (ViewPagerIndicate) findViewById(R.id.id_viewpagerindicate);
        viewpager = (ViewPager) findViewById(R.id.viewpager_launch);
        mGoMainBt = (Button) findViewById(R.id.to_mainactivity);
        login = (Button) findViewById(R.id.login);
        contentTextView = (TextView) findViewById(R.id.content);
        viewPagerIndicate.addTabItem();
        viewPagerIndicate.setViewPager(viewpager,contentTextView);
        viewpager.setCurrentItem(1);
        viewpager.setPageTransformer(true, new DepthPagetransformer());
        viewpager.setAdapter(new LaunchViewPagerAdaper(getSupportFragmentManager()));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginDialog==null){
                    loginDialog = new LoginDialog();
                }
                loginDialog.show(getSupportFragmentManager(),"");
            }
        });
        mGoMainBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                startActivity(new Intent(LaunchActivity.this,MainActivity.class), ActivityOptions.makeSceneTransitionAnimation(LaunchActivity.this).toBundle());
            }
        });
        viewPagerIndicate.setOnSelectedPageListener(this);
    }

    @Override
    public void onSelected(int position) {

    }

    @Override
    public void onScroll(int position, float positionOffset, int positionOffsetPixels) {
    }

    public class LaunchViewPagerAdaper extends FragmentPagerAdapter{

        public LaunchViewPagerAdaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if (launchFragment==null){
                        launchFragment = new LaunchFateFragment();
                    }
                    return launchFragment;
                case 3:
                    if (launchComicFragment==null){
                        launchComicFragment = new LaunchComicFragment();
                    }
                    return launchComicFragment;
                case 2:
                    if (launchDialogueFragment==null){
                        launchDialogueFragment = new LaunchDialogueFragment();
                    }
                    return launchDialogueFragment;
                case 1:
                    if (launchConstellationFragment == null){
                        launchConstellationFragment = new LaunchConstellationFragment();
                    }
                    return launchConstellationFragment;
            }
            return launchFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
