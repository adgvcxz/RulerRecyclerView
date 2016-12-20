package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
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

public class RulerBaseItemView extends LinearLayout {

    protected LinearLayout mScaleLeftLayout;
    protected View mMiddleScaleView;
    protected LinearLayout mScaleRightLayout;
    protected TextView mScaleTextView;
    protected TextPaint mScaleTextPaint;

    public RulerBaseItemView(Context context) {
        super(context);
        init(context);
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
        mMiddleScaleView = new View(context);
        topLinearLayout.addView(mMiddleScaleView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

        //初始化右边的刻度
        mScaleRightLayout = new LinearLayout(context);
        mScaleRightLayout.setOrientation(HORIZONTAL);
        topLinearLayout.addView(mScaleRightLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
        mScaleRightLayout.setBackgroundColor(Color.DKGRAY);

        mScaleTextView = new TextView(context);
        mScaleTextView.setTextSize(24);
        mScaleTextView.setGravity(Gravity.CENTER);
        mScaleTextView.setText("60.0");
        LayoutParams textLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mScaleTextView, textLp);
        mScaleTextPaint = mScaleTextView.getPaint();
    }

    protected LinearLayout.LayoutParams generateLayoutParams(int width, int margin) {
        LayoutParams lp = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
        lp.leftMargin = margin;
        lp.rightMargin = margin;
        return lp;
    }
}
