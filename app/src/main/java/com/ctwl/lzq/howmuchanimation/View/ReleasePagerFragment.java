package com.ctwl.lzq.howmuchanimation.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.ctwl.lzq.howmuchanimation.Activity.SelecteImgActivity;
import com.ctwl.lzq.howmuchanimation.Adapter.AddImageViewAdapter;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudSaveCallback;
import com.ctwl.lzq.howmuchanimation.Diy.DividerGridItemDecoration;
import com.ctwl.lzq.howmuchanimation.R;
import com.ctwl.lzq.howmuchanimation.Utils.BitmapUtils;
import com.ctwl.lzq.howmuchanimation.Utils.ImageLoaderUtils;
import com.ctwl.lzq.howmuchanimation.Utils.LeanCloudUtils;
import com.ctwl.lzq.howmuchanimation.Utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by B41-80 on 2016/7/15.
 */
public class ReleasePagerFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.add_img_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.send_fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.care_page_card_view)
    CardView mCardView;
    @BindView(R.id.m_gif_iv)
    GifImageView mGifImageView;
    @BindView(R.id.content_et)
    EditText mContentEditView;
    @BindView(R.id.m_localtion_rl)
    RelativeLayout mLocationRelativeLayout;

    private View mView;
    List<String> mList;
    AddImageViewAdapter mAddImageViewAdapter;
    View footView;
    boolean isFinish;
    List<AVFile> mAvFiles;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("ReleasePagerFragment",data.getStringArrayListExtra("select_img").get(0));
        if (data.getStringArrayListExtra("select_img").size()!=0){
            mList.addAll(data.getStringArrayListExtra("select_img"));
            mAddImageViewAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_release_pager,container,false);
        footView = inflater.inflate(R.layout.item_image_add,container,false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((ScreenUtils.getScreenWidth(getActivity())-16)/4
                ,(ScreenUtils.getScreenWidth(getActivity())-16)/4);
        footView.setLayoutParams(layoutParams);
        footView.setOnClickListener(this);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAvFiles = new ArrayList<>();
        mFloatingActionButton.setOnClickListener(this);
        mLocationRelativeLayout.setOnClickListener(this);
        mList = new ArrayList<>();
        mAddImageViewAdapter = new AddImageViewAdapter(getActivity(),mList);
        mAddImageViewAdapter.setFootView(footView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAddImageViewAdapter);
//        final MediaController mc = new MediaController(getActivity());
//        mc.setMediaPlayer((GifDrawable)mGifImageView.getDrawable());
//        mc.setAnchorView(mGifImageView);
//        mc.show();
//        ((GifDrawable) mGifImageView.getDrawable()).stop();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCardView, mCardView.getWidth()/2,0, mFloatingActionButton.getWidth() / 2, mCardView.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mCardView.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCardView,mCardView.getWidth()/2,0, mCardView.getHeight(), mFloatingActionButton.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCardView.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
//                fab.setImageResource(R.drawable.plus);
//                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m_localtion_rl:
                break;
            case R.id.send_fab:
                if (isFinish){
                    animateRevealShow();
                    isFinish = false;
                }else{
                    animateRevealClose();
                    isFinish = true;
                    for (String s:mList){
                        AVFile avFile = new AVFile(""+System.currentTimeMillis(), BitmapUtils.Bitmap2Bytes(ImageLoaderUtils.getInstance().decodeSampledBitmapFromNative(s,100,100)));
                        mAvFiles.add(avFile);
                    }
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put("content",mContentEditView.getText().toString());
                    map.put("name", AVUser.getCurrentUser().getString("name"));
                    map.put("username", AVUser.getCurrentUser().getUsername());
                    map.put("imgList", mAvFiles);
                    LeanCloudUtils.saveInBackground("share", map, new LeancloudSaveCallback() {
                        @Override
                        public void onSaveSuccess() {

                        }

                        @Override
                        public void onSaveFaile(String eor) {

                        }
                    });
                }
                break;
            default:
                Intent intent = new Intent(getActivity(), SelecteImgActivity.class);
                intent.putExtra("type","114");
                startActivityForResult(intent,1);
                break;
        }
    }
}
