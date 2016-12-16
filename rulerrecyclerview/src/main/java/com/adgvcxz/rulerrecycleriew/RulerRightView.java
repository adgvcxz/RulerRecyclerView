package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerRightView extends LinearLayout {


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
}
