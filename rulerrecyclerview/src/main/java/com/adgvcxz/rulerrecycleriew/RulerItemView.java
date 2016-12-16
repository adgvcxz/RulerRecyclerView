package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerItemView extends LinearLayout {


    public RulerItemView(Context context) {
        super(context);
    }

    public void set(int number, int offset, int height, int lineWidth, int lineSpacing) {
        for (int i = 0; i < offset; i++) {
            addItemView(lineWidth, height, lineSpacing);
        }
        Context context = getContext();
        View view = new View(context);
        view.setLayoutParams(new LayoutParams(lineWidth, height / 3 * 2));
        view.setBackgroundColor(Color.RED);
        addView(view);
        View spacing = new View(context);
        spacing.setLayoutParams(new LayoutParams(lineSpacing, 1));
        addView(spacing);
        for (int i = offset + 1; i < number; i++) {
            addItemView(lineWidth, height, lineSpacing);
        }
    }

    private void addItemView(int width, int height, int lineSpacing) {
        View view = new View(getContext());
        view.setBackgroundColor(Color.BLUE);
        addView(view, new LayoutParams(width, height / 2));
        View spacing = new View(getContext());
        addView(spacing, new LayoutParams(lineSpacing, 1));
    }
}
