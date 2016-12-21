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

public class RulerRightView extends RulerBaseItemView {

    private int mRightWidth;


    public RulerRightView(Context context) {
        super(context);
    }

    public void init(int rightWidth, int leftNumber, int remind, int lineWidth, int lineSpacing) {
        int margin = lineSpacing / 2;
        LayoutParams lp;
        int i = 0;
        for (; i < remind && i < leftNumber; i++) {
            mScaleLeftLayout.addView(generateNormalView(lineWidth, lineSpacing));
            mRightWidth += (lineWidth + margin * 2);
        }
        if (i < remind) {
            mMiddleScaleView.setVisibility(View.VISIBLE);
            lp = generateLayoutParams(lineWidth, margin);
            mMiddleScaleView.setLayoutParams(lp);
            mMiddleScaleView.setBackgroundColor(Color.RED);
            i++;
            mRightWidth += (margin + lineWidth / 2);
        } else {
            mMiddleScaleView.setVisibility(View.GONE);
        }
        for (; i < remind; i++) {
            mScaleRightLayout.addView(generateNormalView(lineWidth, lineSpacing));
        }
        lp = new LayoutParams((rightWidth - lineWidth / 2) - margin, LayoutParams.MATCH_PARENT);
        LinearLayout view = new LinearLayout(getContext());
        mScaleRightLayout.addView(view, lp);
    }

    public void initWhenEdge(int rightWidth, int leftNumber, int rightNumber, int count, int remind, int lineWidth, int lineSpacing) {
        init(rightWidth, leftNumber, remind, lineWidth, lineSpacing);
        LinearLayout linearLayout = (LinearLayout) mScaleRightLayout.getChildAt(mScaleRightLayout.getChildCount() - 1);
        int rightMargin = linearLayout.getLayoutParams().width;
        int number = (int) Math.ceil((float) rightMargin / getScaleWidth(lineWidth, lineSpacing));
        int group = leftNumber + rightNumber + 1;
        for (int i = 0; i < number; i++) {
            RulerScaleView view;
            if (i == number - 1) {
                view = new RulerScaleView(getContext());
                LayoutParams lp = generateLayoutParams(lineWidth, lineSpacing / 2);
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
