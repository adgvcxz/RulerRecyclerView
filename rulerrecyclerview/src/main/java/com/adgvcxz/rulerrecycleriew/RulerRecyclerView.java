package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerRecyclerView extends RecyclerView {

    private int mLineWidth;
    private int mLineSpacing;

    public RulerRecyclerView(Context context) {
        super(context);
        init();
    }

    public RulerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RulerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addOnScrollListener(new OnScrollListener() {
            boolean mScrolled = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
                    mScrolled = false;
                    int childCount = getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View view = getChildAt(i);
                        if (view.getRight() >= getWidth() / 2) {
                            int offset = (getWidth() / 2 - view.getLeft()) % (mLineWidth + mLineSpacing);
                            if (offset != 0) {
                                if (offset < (mLineWidth + mLineSpacing) / 2) {
                                    smoothScrollBy(-offset, 0);
                                } else {
                                    smoothScrollBy((mLineWidth + mLineSpacing) - offset, 0);
                                }
                            }
                            break;
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx != 0 || dy != 0) {
                    mScrolled = true;
                }
            }
        });
    }

    public void setLineWidthAndSpacing(int width, int spacing) {
        mLineWidth = width;
        mLineSpacing = spacing;
        setAdapter(new RulerAdapter.Builder().setWidthAndSpacing(width, spacing).build());
    }

}
