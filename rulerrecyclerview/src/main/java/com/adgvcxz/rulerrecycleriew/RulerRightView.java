package com.adgvcxz.rulerrecycleriew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

@SuppressLint("ViewConstructor")
class RulerRightView extends RulerBaseItemView {

    private int mRightWidth;

    public RulerRightView(Context context, int color, int lineWidth, int scaleWidth, float middle, float normal) {
        super(context, color, lineWidth, scaleWidth, middle, normal);
    }


    public void init(int rightWidth, int leftNumber, int remind) {
        int margin = mScaleWidth / 2;
        LayoutParams lp;
        int i = 0;

        for (; i < remind && i < leftNumber; i++) {
            mScaleLeftLayout.addView(generateNormalView());
            mRightWidth += (mLineWidth + margin * 2);
        }
        if (i < remind) {
            mMiddleScaleView.setVisibility(View.VISIBLE);
            lp = generateLayoutParams(mLineWidth, margin);
            mMiddleScaleView.setLayoutParams(lp);
            i++;
            mRightWidth += (margin + mLineWidth / 2);
        } else {
            mMiddleScaleView.setVisibility(View.GONE);
        }
        for (; i < remind; i++) {
            mScaleRightLayout.addView(generateNormalView());
        }
        lp = new LayoutParams((rightWidth - mLineWidth / 2) - margin, LayoutParams.MATCH_PARENT);
        LinearLayout view = new LinearLayout(getContext());
        mScaleRightLayout.addView(view, lp);
    }

    public void initWhenEdge(int rightWidth, int leftNumber, int rightNumber, int count, int remind) {
        init(rightWidth, leftNumber, remind);
        LinearLayout linearLayout = (LinearLayout) mScaleRightLayout.getChildAt(mScaleRightLayout.getChildCount() - 1);
        int rightMargin = linearLayout.getLayoutParams().width;
        int number = (int) Math.ceil((float) rightMargin / getScaleWidth());
        int group = leftNumber + rightNumber + 1;
        for (int i = 0; i < number; i++) {
            RulerScaleView view;
            if (i == number - 1) {
                view = new RulerScaleView(getContext());
                LayoutParams lp = generateLayoutParams(mLineWidth, mScaleWidth / 2);
                lp.rightMargin = rightMargin - number * getScaleWidth();
                view.setLayoutParams(lp);
            } else {
                view = generateNormalView();
            }
            if ((i + count + 1) % group == 0) {
                view.setProportion(1);
            } else {
                view.setProportion(0.5f);
            }
            linearLayout.addView(view);
        }
    }

    public void adjustTextView(String str) {
        if (mMiddleScaleView.getVisibility() == View.VISIBLE) {
            mScaleTextView.setVisibility(View.VISIBLE);
            float width = mScaleTextPaint.measureText(str);
            LayoutParams lp = (LayoutParams) mScaleTextView.getLayoutParams();
            lp.leftMargin = (int) (mRightWidth - width / 2);
            mScaleTextView.setLayoutParams(lp);
            mScaleTextView.setText(str);
        } else {
            mScaleTextView.setVisibility(View.INVISIBLE);
        }
    }
}
