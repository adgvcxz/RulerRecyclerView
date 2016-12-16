package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerLayoutManager extends LinearLayoutManager {
    public RulerLayoutManager(Context context) {
        super(context);
    }

    public RulerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public RulerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
