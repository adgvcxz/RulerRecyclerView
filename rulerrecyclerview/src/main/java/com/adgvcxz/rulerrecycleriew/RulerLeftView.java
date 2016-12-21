package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerLeftView extends RulerBaseItemView {

    private int mLeftMargin;

    public RulerLeftView(Context context) {
        super(context);
    }

    public void init(int leftWidth, int rightWidth, int rightNumber, int count, int lineWidth, int lineSpacing) {
        mLeftMargin = leftWidth;
        int margin = lineSpacing / 2;
        LinearLayout.LayoutParams lp = (LayoutParams) mScaleLeftLayout.getLayoutParams();
        Log.e("zhaow", leftWidth + "   " + lineWidth + "   " + margin);
        lp.width = (leftWidth - lineWidth / 2) - margin;
        mScaleLeftLayout.setLayoutParams(lp);
        Log.e("zhaow", lp.width + "   1");
        mScaleLeftLayout.setBackgroundColor(Color.GRAY);
        lp = generateLayoutParams(lineWidth, margin);
        mMiddleScaleView.setLayoutParams(lp);
        mMiddleScaleView.setBackgroundColor(Color.RED);
        for (int i = 0; i < rightNumber && i < count; i++) {
            mScaleRightLayout.addView(generateNormalView(lineWidth, lineSpacing));
        }
        if (rightNumber >= count) {
            lp = new LayoutParams((rightWidth - lineWidth / 2) - margin, ViewGroup.LayoutParams.MATCH_PARENT);
            LinearLayout view = new LinearLayout(getContext());
            mScaleRightLayout.addView(view, lp);
        }
    }

    public void initWhenEdge(int leftWidth, int rightWidth, int leftNumber, int rightNumber, int count, int lineWidth, int lineSpacing) {
        init(leftWidth, rightWidth, rightNumber, count, lineWidth, lineSpacing);
        LinearLayout.LayoutParams lp = (LayoutParams) mScaleLeftLayout.getLayoutParams();
        int leftMargin = lp.width;
        int number = (int) Math.ceil((float) leftMargin / getScaleWidth(lineWidth, lineSpacing));
        int group = leftNumber + rightNumber + 1;
        int offset = number % group;
        for (int i = 0; i < number; i++) {
            RulerScaleView view;
            if (i == 0) {
                view = new RulerScaleView(getContext());
                lp = new LayoutParams(lineWidth, LayoutParams.WRAP_CONTENT);
                lp.leftMargin = leftMargin - number * getScaleWidth(lineWidth, lineSpacing);
                lp.width = lineWidth;
                lp.rightMargin = lineSpacing / 2;
                view.setLayoutParams(lp);
            } else {
                view = generateNormalView(lineWidth, lineSpacing);
            }
            if (i % group == offset) {
                view.setProportion(1);
                view.setBackgroundColor(Color.RED);
            } else {
                view.setProportion(0.5f);
                view.setBackgroundColor(Color.BLUE);
            }
            mScaleLeftLayout.addView(view);
        }
        if (rightNumber >= count) {
            LinearLayout linearLayout = (LinearLayout) mScaleRightLayout.getChildAt(mScaleRightLayout.getChildCount() - 1);
            int rightMargin = linearLayout.getLayoutParams().width;
            number = (int) Math.ceil((float) rightMargin / getScaleWidth(lineWidth, lineSpacing));
            group = leftNumber + rightNumber + 1;
            for (int i = 0; i < number; i++) {
                RulerScaleView view;
                if (i == number - 1) {
                    view = new RulerScaleView(getContext());
                    lp = new LayoutParams(lineWidth, LayoutParams.WRAP_CONTENT);
                    lp.rightMargin = rightMargin - number * getScaleWidth(lineWidth, lineSpacing);
                    view.setLayoutParams(lp);
                } else {
                    view = generateNormalView(lineWidth, lineSpacing);
                }
                if ((i + count + 1) % group == 0) {
                    view.setProportion(1);
                    view.setBackgroundColor(Color.RED);
                } else {
                    view.setProportion(0.5f);
                    view.setBackgroundColor(Color.BLUE);
                }
                linearLayout.addView(view);
            }
        }
    }

    public void adjustLeftTextView(String str) {
        float width = mScaleTextPaint.measureText(str);
        LayoutParams lp = (LayoutParams) mScaleTextView.getLayoutParams();
        lp.leftMargin = (int) (mLeftMargin - width / 2);
        mScaleTextView.setLayoutParams(lp);
        mScaleTextView.setText(str);
    }
}
