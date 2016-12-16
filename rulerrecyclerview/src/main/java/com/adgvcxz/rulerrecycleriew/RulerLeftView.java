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


    public RulerLeftView(Context context) {
        super(context);
    }

    public void init(int width, int height, int number, int lineWidth, int lineSpacing) {
        Context context = getContext();
        View leftView = new View(context);
        addView(leftView, new LayoutParams(width, 1));
        View view = new View(context);
        view.setBackgroundColor(Color.RED);
        addView(view, new LayoutParams(lineWidth, height));
        View spacing = new View(context);
        addView(spacing, new LayoutParams(lineSpacing, 1));
        for (int i = 0; i < number; i++) {
            view = new View(context);
            view.setLayoutParams(new LayoutParams(lineWidth, height / 2));
            view.setBackgroundColor(Color.BLUE);
            addView(view);
            spacing = new View(context);
            spacing.setLayoutParams(new LayoutParams(lineSpacing, 1));
            addView(spacing);
        }
        setBackgroundColor(Color.DKGRAY);
    }
}
