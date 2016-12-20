package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerItemView extends RulerBaseItemView {

    private int mLineWidth;
    private int mLineSpacing;


    public RulerItemView(Context context) {
        super(context);
    }


    public void init(int number, int offset, int lineWidth, int lineSpacing) {
        mLineWidth = lineWidth;
        mLineSpacing = lineSpacing;
        int margin = lineSpacing / 2;
        LayoutParams lp;
        for (int i = 0; i < offset; i++) {
            mScaleLeftLayout.addView(generateNormalView(lineWidth, lineSpacing));
        }
        lp = generateLayoutParams(lineWidth, margin);
        mMiddleScaleView.setLayoutParams(lp);
        mMiddleScaleView.setBackgroundColor(Color.RED);
        for (int i = offset + 1; i < number; i++) {
            mScaleRightLayout.addView(generateNormalView(lineWidth, lineSpacing));
        }
    }

    public void adjustTextView(String str) {
        int leftWidth = ((mLineSpacing / 2) * 2 + mLineWidth) * mScaleLeftLayout.getChildCount() + mLineWidth / 2 + mLineSpacing / 2;
        float width = mScaleTextPaint.measureText(str);
        LayoutParams lp = (LayoutParams) mScaleTextView.getLayoutParams();
        lp.leftMargin = (int) (leftWidth - width / 2);
        mScaleTextView.setLayoutParams(lp);
        mScaleTextView.setText(str);
    }


}
