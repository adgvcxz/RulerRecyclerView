package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/17.
 */

public class RulerBaseItemView extends LinearLayout {

    protected LinearLayout mScaleLeftLayout;
    protected RulerScaleView mMiddleScaleView;
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
        mMiddleScaleView = new RulerScaleView(context);
        topLinearLayout.addView(mMiddleScaleView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

        //初始化右边的刻度
        mScaleRightLayout = new LinearLayout(context);
        mScaleRightLayout.setOrientation(HORIZONTAL);
        mScaleRightLayout.setBackgroundColor(Color.DKGRAY);
        topLinearLayout.addView(mScaleRightLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

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

    protected int getScaleWidth(int lineWidth, int lineSpacing) {
        return lineWidth + lineSpacing / 2 * 2;
    }

    protected RulerScaleView generateNormalView(int lineWidth, int lineSpacing) {
        RulerScaleView view = new RulerScaleView(getContext());
        view.setProportion(0.5f);
        view.setBackgroundColor(Color.BLUE);
        view.setLayoutParams(generateLayoutParams(lineWidth, lineSpacing / 2));
        return view;
    }
}
