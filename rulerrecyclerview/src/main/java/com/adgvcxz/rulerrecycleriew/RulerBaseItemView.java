package com.adgvcxz.rulerrecycleriew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/17.
 */

class RulerBaseItemView extends LinearLayout {

    protected LinearLayout mScaleLeftLayout;
    protected RulerScaleView mMiddleScaleView;
    protected LinearLayout mScaleRightLayout;
    protected TextView mScaleTextView;
    protected TextPaint mScaleTextPaint;
    protected int mColor;
    protected int mLineWidth;
    protected int mScaleWidth;
    protected float mMiddleLength;
    protected float mNormalLength;
    protected float mTextSize;
    protected int mTextColor;

    public RulerBaseItemView(Context context) {
        super(context);
    }

    public RulerBaseItemView(Context context, int color, int lineWidth, int scaleWidth, float middle, float normal) {
        super(context);
        mColor = color;
        mLineWidth = lineWidth;
        mScaleWidth = scaleWidth;
        mMiddleLength = middle;
        mNormalLength = normal;
        init(context);
        setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setTextSizeAndColor(float textSize, int textColor) {
        mTextSize = textSize;
        mTextColor = textColor;
        if (mScaleTextView != null) {
            mScaleTextView.setTextSize(mTextSize);
            mScaleTextView.setTextColor(mTextColor);
            mScaleTextPaint = mScaleTextView.getPaint();
        }
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, 0);
        lp.weight = 1;

        LinearLayout topLinearLayout = new LinearLayout(context);
        topLinearLayout.setOrientation(HORIZONTAL);
        addView(topLinearLayout, lp);


        //初始化左边的刻度
        mScaleLeftLayout = new LinearLayout(context);
        mScaleLeftLayout.setOrientation(HORIZONTAL);
        topLinearLayout.addView(mScaleLeftLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

        //初始化中间长的刻度
        mMiddleScaleView = generateMiddleView();
        topLinearLayout.addView(mMiddleScaleView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

        //初始化右边的刻度
        mScaleRightLayout = new LinearLayout(context);
        mScaleRightLayout.setOrientation(HORIZONTAL);
        topLinearLayout.addView(mScaleRightLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

        mScaleTextView = new TextView(context);
        mScaleTextView.setTextSize(mTextSize);
        mScaleTextView.setGravity(Gravity.CENTER);
        mScaleTextView.setTextColor(mTextColor);
        LayoutParams textLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mScaleTextView, textLp);
        mScaleTextPaint = mScaleTextView.getPaint();
    }

    protected LinearLayout.LayoutParams generateLayoutParams(int width, int margin) {
        LayoutParams lp = new LayoutParams(width, LayoutParams.MATCH_PARENT);
        lp.leftMargin = margin;
        lp.rightMargin = margin;
        return lp;
    }

    protected int getScaleWidth() {
        return mLineWidth + mScaleWidth / 2 * 2;
    }

    protected RulerScaleView generateNormalView() {
        RulerScaleView view = new RulerScaleView(getContext());
        view.setProportion(mNormalLength);
        view.setBackgroundColor(mColor);
        view.setLayoutParams(generateLayoutParams(mLineWidth, mScaleWidth / 2));
        return view;
    }

    protected RulerScaleView generateMiddleView() {
        RulerScaleView view = new RulerScaleView(getContext());
        view.setBackgroundColor(mColor);
        view.setProportion(mMiddleLength);
        view.setLayoutParams(generateLayoutParams(mLineWidth, mScaleWidth / 2));
        return view;
    }

    public void adjustTextView(String str) {

    }
}
