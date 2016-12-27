package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
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
                view.setProportion(mMiddleLength);
            } else {
                view.setProportion(mNormalLength);
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
                    view.setProportion(mMiddleLength);
                } else {
                    view.setProportion(mNormalLength);
                }
                linearLayout.addView(view);
            }
        }
    }

    public void updateSecondScale(int group, float lineLength) {
        int leftNumber = mScaleLeftLayout.getChildCount();
        for (int i = leftNumber - 1; i >= 0; i--) {
            RulerScaleView rulerScaleView = ((RulerScaleView) mScaleLeftLayout.getChildAt(i));
            if ((leftNumber - i) % group == 0 && rulerScaleView.getProportion() != mMiddleLength) {
                rulerScaleView.setProportion(lineLength);
            }
        }
        int rightNumber = mScaleRightLayout.getChildCount();
        for (int i = 0; i < rightNumber; i++) {
            View view = mScaleRightLayout.getChildAt(i);
            if (view instanceof LinearLayout) {
                LinearLayout layout = (LinearLayout) view;
                for (; i < layout.getChildCount() + rightNumber - 1; i++) {
                    RulerScaleView rulerScaleView = ((RulerScaleView) layout.getChildAt(i - rightNumber + 1));
                    if ((i + 1) % group == 0 && rulerScaleView.getProportion() == mNormalLength) {
                        rulerScaleView.setProportion(lineLength);
                    }
                }
            } else if (view instanceof RulerScaleView) {
                RulerScaleView rulerScaleView = (RulerScaleView) view;
                if ((i + 1) % group == 0 && rulerScaleView.getProportion() == mNormalLength) {
                    ((RulerScaleView) view).setProportion(lineLength);
                }
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
