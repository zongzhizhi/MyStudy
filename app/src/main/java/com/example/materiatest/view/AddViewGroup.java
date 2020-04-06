package com.example.materiatest.view;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.materiatest.R;

public class AddViewGroup extends FrameLayout implements View.OnClickListener {

    public AddViewGroup(Context context) {
        this(context, null);
    }

    public AddViewGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddViewGroup(Context context, @Nullable AttributeSet attrs,
                        int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AddViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        setOrientation(VERTICAL);
//        setAddAnimation();
    }

    ObjectAnimator animator;

    private void setAddAnimation() {
//        LayoutTransition mLayoutTransition = new LayoutTransition();
//        //设置每个动画持续的时间
//        mLayoutTransition.setStagger(LayoutTransition.APPEARING, 3000);
//        mLayoutTransition.setStagger(LayoutTransition.DISAPPEARING, 50);
//        PropertyValuesHolder appearingScaleY = PropertyValuesHolder.ofFloat("translationY", 0f,
//                1.0f);
//        PropertyValuesHolder appearingAlpha = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1f);
//        ObjectAnimator mAnimatorAppearing = ObjectAnimator.ofPropertyValuesHolder(this,
//                appearingAlpha, appearingScaleY);
//        //为LayoutTransition设置动画及动画类型
//        mLayoutTransition.setAnimator(LayoutTransition.APPEARING, mAnimatorAppearing);
//        mLayoutTransition.setDuration(3000);
//        setLayoutTransition(mLayoutTransition);


    }

    private View view;

    public float getOffsetDistance() {
        return offsetDistance;
    }

    private static final String TAG = "123";
    private  float mfoodTop;

    public void setOffsetDistance(float offsetDistance) {
        Log.i(TAG, "setOffsetDistance: " + offsetDistance);
        this.offsetDistance = offsetDistance;
        LayoutParams layoutParams = (LayoutParams) footLayout.getLayoutParams();
        layoutParams.topMargin = (int) (offsetDistance + mfoodTop);
        footLayout.setLayoutParams(layoutParams);
        invalidate();
    }

    private float offsetDistance;


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view = findViewById(R.id.view_head);
        view.setOnClickListener(this);
        footLayout = findViewById(R.id.group_food);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_head:
                addViewFoot();
                break;
        }
    }

    private View footLayout;

    private void addViewFoot() {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.group_foot, null, false);
//        addView(view);
//        footLayout = view;

        Log.i(TAG, "addViewFoot: " + getHeight());
        mfoodTop = view.getHeight() - footLayout.getHeight();
        LayoutParams layoutParams = (LayoutParams) footLayout.getLayoutParams();
        layoutParams.topMargin = (int) mfoodTop;
        footLayout.setLayoutParams(layoutParams);
        animator = ObjectAnimator.ofFloat(this, "offsetDistance", 0, footLayout.getHeight());
        animator.setDuration(3000);
        animator.start();

    }
}
