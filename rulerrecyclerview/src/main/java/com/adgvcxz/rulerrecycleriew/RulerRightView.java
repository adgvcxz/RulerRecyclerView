package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerRightView extends RulerBaseItemView {

    private int mRightWidth;


    public RulerRightView(Context context) {
        super(context);
    }

    public void init(int rightWidth, int number, int offset, int lineWidth, int lineSpacing) {
        int margin = lineSpacing / 2;
        LayoutParams lp;
        int i = 0;
        for (; i < offset && i < number; i++) {
            mScaleLeftLayout.addView(generateNormalView(lineWidth, lineSpacing));
            mRightWidth += (lineWidth + margin * 2);
        }
        if (i < offset) {
            mMiddleScaleView.setVisibility(View.VISIBLE);
            lp = generateLayoutParams(lineWidth, margin);
            mMiddleScaleView.setLayoutParams(lp);
            mMiddleScaleView.setBackgroundColor(Color.RED);
            i++;
            mRightWidth += (margin + lineWidth / 2);
        } else {
            mMiddleScaleView.setVisibility(View.GONE);
        }
        for (; i < offset; i++) {
            mScaleRightLayout.addView(generateNormalView(lineWidth, lineSpacing));
        }
        lp = new LayoutParams((rightWidth - lineWidth / 2) - margin, 1);
        View view = new View(getContext());
        mScaleRightLayout.addView(view, lp);
    }

    public void adjustRightTextView(String str) {
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
