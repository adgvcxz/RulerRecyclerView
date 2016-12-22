package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */
class RulerLeftView extends RulerBaseItemView {

    private int mLeftMargin;

    public RulerLeftView(Context context) {
        super(context);
    }

    public RulerLeftView(Context context, int color, int lineWidth, int scaleWidth, float middle, float normal) {
        super(context, color, lineWidth, scaleWidth, middle, normal);
    }


    public void init(int leftWidth, int rightWidth, int rightNumber, int count) {
        mLeftMargin = leftWidth;
        int margin = mScaleWidth / 2;
        LinearLayout.LayoutParams lp = (LayoutParams) mScaleLeftLayout.getLayoutParams();
        lp.width = (leftWidth - mLineWidth / 2) - margin;
        mScaleLeftLayout.setLayoutParams(lp);
        mScaleLeftLayout.setGravity(Gravity.RIGHT);
        lp = generateLayoutParams(mLineWidth, margin);
        mMiddleScaleView.setLayoutParams(lp);
        for (int i = 0; i < rightNumber && i < count; i++) {
            mScaleRightLayout.addView(generateNormalView());
        }
        if (rightNumber >= count) {
            lp = new LayoutParams((rightWidth - mLineWidth / 2) - margin, ViewGroup.LayoutParams.MATCH_PARENT);
            LinearLayout view = new LinearLayout(getContext());
            mScaleRightLayout.addView(view, lp);
        }
    }

    public void initWhenEdge(int leftWidth, int rightWidth, int leftNumber, int rightNumber, int count) {
        init(leftWidth, rightWidth, rightNumber, count);
        LinearLayout.LayoutParams lp = (LayoutParams) mScaleLeftLayout.getLayoutParams();
        int leftMargin = lp.width;
        int number = (int) Math.ceil((float) leftMargin / getScaleWidth());
        int group = leftNumber + rightNumber + 1;
        int offset = number % group;
        for (int i = 0; i < number; i++) {
            RulerScaleView view;
            view = generateNormalView();
            if (i % group == offset) {
                view.setProportion(1);
            } else {
                view.setProportion(0.5f);
            }
            mScaleLeftLayout.addView(view);
        }
        if (rightNumber >= count) {
            LinearLayout linearLayout = (LinearLayout) mScaleRightLayout.getChildAt(mScaleRightLayout.getChildCount() - 1);
            int rightMargin = linearLayout.getLayoutParams().width;
            number = (int) Math.ceil((float) rightMargin / getScaleWidth());
            group = leftNumber + rightNumber + 1;
            for (int i = 0; i < number; i++) {
                RulerScaleView view = generateNormalView();
                if ((i + count + 1) % group == 0) {
                    view.setProportion(1);
                } else {
                    view.setProportion(0.5f);
                }
                linearLayout.addView(view);
            }
        }
    }

    public void adjustTextView(String str) {
        float width = mScaleTextPaint.measureText(str);
        LayoutParams lp = (LayoutParams) mScaleTextView.getLayoutParams();
        lp.leftMargin = (int) (mLeftMargin - width / 2);
        mScaleTextView.setLayoutParams(lp);
        mScaleTextView.setText(str);
    }
}
