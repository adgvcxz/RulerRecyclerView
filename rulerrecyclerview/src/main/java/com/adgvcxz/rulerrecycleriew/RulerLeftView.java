package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerLeftView extends LinearLayout {

    private View mMaxView;
    private View[] mMiddleView;
    private View[] mMinView;

    public RulerLeftView(Context context) {
        super(context);
    }

    public void init(int width, int height, int number, int lineWidth, int lineSpacing) {
        Context context = getContext();
        View leftView = new View(context);
        addView(leftView, new LayoutParams(width, 1));
        mMaxView = new View(context);
        mMaxView.setBackgroundColor(Color.RED);
        addView(mMaxView, new LayoutParams(lineWidth, height));
        View spacing = new View(context);
        addView(spacing, new LayoutParams(lineSpacing, 1));
        mMinView = new View[number - 1];
        for (int i = 0; i < number - 1; i++) {
            mMinView[i] = new View(context);
            mMinView[i].setLayoutParams(new LayoutParams(lineWidth, height / 2));
            mMinView[i].setBackgroundColor(Color.BLUE);
            addView(mMinView[i]);
            spacing = new View(context);
            spacing.setLayoutParams(new LayoutParams(lineSpacing, 1));
            addView(spacing);
        }
    }
}
