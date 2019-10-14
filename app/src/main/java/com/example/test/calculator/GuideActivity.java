package com.example.test.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.test.utils.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private Button mStart;
    private LinearLayout mDotLL;
    private int[] mImages = new int[]{R.drawable.guide_1, R.drawable.guide_3, R.drawable.guide_4};
    private List<ImageView> mImageViews;
    private int mDotWidth;
    private ImageView mDot;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        mDotLL=findViewById(R.id.ll_dot);
        mViewPager=findViewById(R.id.vp_guide);
        mStart=findViewById(R.id.bt_start);
        mDot=findViewById(R.id.iv_dot);
        initData();
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mViewPagerAdapter=new ViewPagerAdapter(this);
        if (mImageViews != null) {
            mViewPagerAdapter.setImageViews(mImageViews);
        }
        mViewPager.setAdapter(mViewPagerAdapter);
        mDot.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mDot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mDotWidth = mDotLL.getChildAt(1).getLeft()-
                                mDotLL.getChildAt(0).getLeft();
                    }
                });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {
                int leftMargin = (int) (position * mDotWidth + mDotWidth * positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                        mDot.getLayoutParams();
                params.leftMargin = leftMargin;
                mDot.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {
                if (i == mImages.length-1){
                    AlphaAnimation aniAlpha = new AlphaAnimation(0.0f,1.0f);
                    aniAlpha.setDuration(2000);
                    aniAlpha.setRepeatMode(Animation.REVERSE);
                    aniAlpha.setRepeatCount(1);
                    mStart.startAnimation(aniAlpha);
                    mStart.setVisibility(View.VISIBLE);
                }else{
                    mStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initData(){
        mImageViews=new ArrayList<>();
        if(mImages.length>0){
            for (int i=0;i<mImages.length;i++){
                ImageView imageView=new ImageView(this);
                imageView.setBackgroundResource(mImages[i]);
                mImageViews.add(imageView);

                ImageView dotView = new ImageView(this);
                dotView.setBackgroundResource(R.drawable.guide_circle_shape_select);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                if (i>0){
                    params.leftMargin = 8;
                }
                dotView.setLayoutParams(params);
                mDotLL.addView(dotView);
            }
        }
    }

}
