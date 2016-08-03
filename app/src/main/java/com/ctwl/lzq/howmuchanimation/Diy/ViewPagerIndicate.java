package com.ctwl.lzq.howmuchanimation.Diy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctwl.lzq.howmuchanimation.R;

import java.util.logging.Logger;

/**
 * Created by B41-80 on 2016/7/4.
 */
public class ViewPagerIndicate extends FrameLayout implements ViewPager.OnPageChangeListener{
    /**
     *圆点数量
     */
    private int mTabCount;
    private Paint mPaint;
    private Context mContext;
    private int mInitTranslationX;
    private float mTranslationX;
    private TextView noteTextView;
    ImageView orageImageView;
    private Animation animation;
    public ViewPagerIndicate(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ViewPagerIndicate);
        mTabCount = a.getInt(R.styleable.ViewPagerIndicate_item_count,2);
        if (mTabCount<=0){
            mTabCount = 2;
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
        mPaint.setPathEffect(new CornerPathEffect(3));
    }
    public ViewPagerIndicate(Context context) {
        super(context);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mInitTranslationX = getWidth() / mTabCount / 2 - 8
                / 2;
    }

    public void setViewPager(ViewPager viewPager, TextView noteTextView){
        viewPager.setOnPageChangeListener(this);
        this.noteTextView = noteTextView;
    }
    public void addTabItem(){
        LinearLayout linearLayout = new LinearLayout(mContext);
        FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(fLayoutParams);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (int i=0;i<mTabCount;i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8,8,8,8);
            ImageView imageView = new ImageView(mContext);
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.yuandian_white));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);
        }
        orageImageView = new ImageView(mContext);
        orageImageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.yuandian_orange));
        orageImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        FrameLayout.LayoutParams f1LayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        f1LayoutParams.setMargins(8,8,8,8);
        orageImageView.setLayoutParams(f1LayoutParams);
        addView(linearLayout);
        addView(orageImageView);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mTranslationX = getWidth() / mTabCount * (position + positionOffset);
        orageImageView.setTranslationX(mTranslationX);
        if (onSelectedPageListener != null){
            onSelectedPageListener.onScroll(position,positionOffset,positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.i("ViewPagerIndicate",""+position);
        if (onSelectedPageListener != null){
            onSelectedPageListener.onSelected(position);
        }
        final String content;
        if (position==0){
            content = "第一页";
        }else{
            content = "第二页";
        }
        animation = AnimationUtils.loadAnimation(mContext,R.anim.note_animation);
        noteTextView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                noteTextView.setText(content);
                noteTextView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.scale_launch));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    OnSelectedPageListener onSelectedPageListener;
    public interface OnSelectedPageListener{
        void onSelected(int position);
        void onScroll(int position, float positionOffset, int positionOffsetPixels);
    }
    public void setOnSelectedPageListener(OnSelectedPageListener onSelectedPageListener){
        this.onSelectedPageListener = onSelectedPageListener;
    }
}
