package com.adgvcxz.rulerrecycleriew;

import android.content.Context;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

class RulerItemView extends RulerBaseItemView {

    public RulerItemView(Context context) {
        super(context);
    }

    public RulerItemView(Context context, int color, int lineWidth, int scaleWidth, float middle, float normal) {
        super(context, color, lineWidth, scaleWidth, middle, normal);
    }

    public void init(int number, int leftNumber) {
        int margin = mScaleWidth / 2;
        LayoutParams lp;
        for (int i = 0; i < leftNumber; i++) {
            mScaleLeftLayout.addView(generateNormalView());
        }
        lp = generateLayoutParams(mLineWidth, margin);
        mMiddleScaleView.setLayoutParams(lp);
        mMiddleScaleView.setBackgroundColor(mColor);
        for (int i = leftNumber + 1; i < number; i++) {
            mScaleRightLayout.addView(generateNormalView());
        }
    }

    public void adjustTextView(String str) {
        if (mScaleLeftLayout != null) {
            int leftWidth = ((mScaleWidth / 2) * 2 + mLineWidth) * mScaleLeftLayout.getChildCount() + mLineWidth / 2 + mScaleWidth / 2;
            float width = mScaleTextPaint.measureText(str);
            LayoutParams lp = (LayoutParams) mScaleTextView.getLayoutParams();
            lp.leftMargin = (int) (leftWidth - width / 2);
            mScaleTextView.setLayoutParams(lp);
            mScaleTextView.setText(str);
        }
    }
}
