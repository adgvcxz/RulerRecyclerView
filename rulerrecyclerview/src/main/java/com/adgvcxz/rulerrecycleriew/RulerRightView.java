package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerRightView extends RulerBaseItemView {


    public RulerRightView(Context context) {
        super(context);
    }

    public void init(int width, int height, int number, int remind, int lineWidth, int lineSpacing) {
        Context context = getContext();
        int i = 0;
        for (; i < number; i++) {
            View view = new View(context);
            view.setLayoutParams(new LayoutParams(lineWidth, height / 2));
            view.setBackgroundColor(Color.BLUE);
            addView(view);
            if (i < remind - 1) {
                View spacing = new View(context);
                spacing.setLayoutParams(new LayoutParams(lineSpacing, 1));
                addView(spacing);
            } else {
                break;
            }
        }
        if (i < remind) {
            View view = new View(context);
            view.setBackgroundColor(Color.RED);
            addView(view, new LayoutParams(lineWidth, height));
            if (i < remind - 1) {
                View spacing = new View(context);
                spacing.setLayoutParams(new LayoutParams(lineSpacing, 1));
                addView(spacing);
            }
            i++;
            for (; i < remind; i++) {
                view = new View(context);
                view.setLayoutParams(new LayoutParams(lineWidth, height / 2));
                view.setBackgroundColor(Color.BLUE);
                addView(view);
                if (i < remind - 1) {
                    View spacing = new View(context);
                    spacing.setLayoutParams(new LayoutParams(lineSpacing, 1));
                    addView(spacing);
                }
            }
        }
        View leftView = new View(context);
        addView(leftView, new LayoutParams(width, 1));
        setBackgroundColor(Color.DKGRAY);
    }

    public void init(int rightWidth, int number, int offset, int lineWidth, int lineSpacing) {
        int margin = lineSpacing / 2;
        LayoutParams lp;
        int i = 0;
        int perWidth = 0;
        for (; i < offset && i < number; i++) {
            View view = new View(getContext());
            lp = generateLayoutParams(lineWidth, margin);
            lp.weight = 0.5f;
            mScaleLeftLayout.addView(view, lp);
            view.setBackgroundColor(Color.BLUE);
            perWidth += (lineWidth + margin * 2);
        }
        if (i < offset) {
            mMiddleScaleView.setVisibility(View.VISIBLE);
            lp = generateLayoutParams(lineWidth, margin);
            mMiddleScaleView.setLayoutParams(lp);
            mMiddleScaleView.setBackgroundColor(Color.RED);
            i++;
            perWidth += (margin + lineWidth / 2);
        } else {
            mMiddleScaleView.setVisibility(View.GONE);
        }
        for (; i < offset; i++) {
            View view = new View(getContext());
            lp = generateLayoutParams(lineWidth, margin);
            lp.weight = 0.5f;
            mScaleRightLayout.addView(view, lp);
            view.setBackgroundColor(Color.BLUE);
        }
        lp = new LayoutParams((rightWidth - lineWidth / 2) - margin, 1);
        View view = new View(getContext());
        mScaleRightLayout.addView(view, lp);
        adjustRightTextView(perWidth, "150");
    }

    public void adjustRightTextView(int leftWidth, String str) {
        mScaleTextView.setVisibility(mMiddleScaleView.getVisibility());
        if (mMiddleScaleView.getVisibility() == View.VISIBLE) {
            float width = mScaleTextPaint.measureText(str);
            LayoutParams lp = (LayoutParams) mScaleTextView.getLayoutParams();
            lp.leftMargin = (int) (leftWidth - width / 2);
            mScaleTextView.setLayoutParams(lp);
            mScaleTextView.setText(str);
        }
    }
}
