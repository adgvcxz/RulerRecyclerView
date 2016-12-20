package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerLeftView extends RulerBaseItemView {


    public RulerLeftView(Context context) {
        super(context);
    }

    public void init(int leftWidth, int number, int lineWidth, int lineSpacing) {
        int margin = lineSpacing / 2;
        LinearLayout.LayoutParams lp = (LayoutParams) mScaleLeftLayout.getLayoutParams();
        lp.width = (leftWidth - lineWidth / 2) - margin;
        mScaleLeftLayout.setLayoutParams(lp);
        lp = generateLayoutParams(lineWidth, margin);
        mMiddleScaleView.setLayoutParams(lp);
        mMiddleScaleView.setBackgroundColor(Color.RED);
        for (int i = 0; i < number - 1; i++) {
            View view = new View(getContext());
            lp = generateLayoutParams(lineWidth, margin);
            lp.weight = 0.5f;
            mScaleRightLayout.addView(view, lp);
            view.setBackgroundColor(Color.BLUE);
        }
        adjustLeftTextView(leftWidth, "100");
    }

    public void adjustLeftTextView(int leftMargin, String str) {
        float width = mScaleTextPaint.measureText(str);
        LayoutParams lp = (LayoutParams) mScaleTextView.getLayoutParams();
        lp.leftMargin = (int) (leftMargin - width / 2);
        mScaleTextView.setLayoutParams(lp);
        mScaleTextView.setText(str);
    }
}
