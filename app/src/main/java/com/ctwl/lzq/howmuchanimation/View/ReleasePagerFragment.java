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
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.ctwl.lzq.howmuchanimation.Activity.SelecteImgActivity;
import com.ctwl.lzq.howmuchanimation.Adapter.AddImageViewAdapter;
import com.ctwl.lzq.howmuchanimation.Callback.LeancloudSaveCallback;
import com.ctwl.lzq.howmuchanimation.Contract.SendContract;
import com.ctwl.lzq.howmuchanimation.Diy.DividerGridItemDecoration;
import com.ctwl.lzq.howmuchanimation.Presenter.SendMsgPresenter;
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
public class ReleasePagerFragment extends Fragment implements View.OnClickListener,SendContract.View{

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
    @BindView(R.id.send_status)
    TextView mSendStatusTextView;

    private View mView;
    List<String> mList;
    AddImageViewAdapter mAddImageViewAdapter;
    View footView;
    SendMsgPresenter mSendMsgPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_release_pager,container,false);
        footView = inflater.inflate(R.layout.item_image_add,container,false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((ScreenUtils.getScreenWidth(getActivity())-16)/4
                ,(ScreenUtils.getScreenWidth(getActivity())-16)/4);
        footView.setLayoutParams(layoutParams);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSendMsgPresenter = new SendMsgPresenter(this);
        mFloatingActionButton.setOnClickListener(this);
        mLocationRelativeLayout.setOnClickListener(this);
        footView.setOnClickListener(this);
        mList = new ArrayList<>();
        mAddImageViewAdapter = new AddImageViewAdapter(getActivity(),mList);
        mAddImageViewAdapter.setFootView(footView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAddImageViewAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m_localtion_rl:
                break;
            case R.id.send_fab:
                animateRevealClose();
                break;
            default:
                Intent intent = new Intent(getActivity(), SelecteImgActivity.class);
                intent.putExtra("type","114");
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    public void sendSuccess() {
        mSendStatusTextView.setText("发送成功");
        animateRevealShow();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void setPresenter(SendContract.Presenter presenter) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("ReleasePagerFragment",data.getStringArrayListExtra("select_img").get(0));
        if (data.getStringArrayListExtra("select_img").size()!=0){
            mList.addAll(data.getStringArrayListExtra("select_img"));
            mAddImageViewAdapter.notifyDataSetChanged();
        }
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
                mSendMsgPresenter.send(mContentEditView.getText().toString(),mList);
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
}
