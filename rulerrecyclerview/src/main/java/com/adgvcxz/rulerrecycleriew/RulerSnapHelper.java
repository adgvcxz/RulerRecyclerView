package com.adgvcxz.rulerrecycleriew;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerSnapHelper extends RecyclerView.OnScrollListener {

    private RecyclerView mRecyclerView;
    private boolean mScrolled = false;
    private int mScaleWidth;

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView, int scaleWidth) {
        if (recyclerView != null) {
            mRecyclerView = recyclerView;
            recyclerView.addOnScrollListener(this);
            mScaleWidth = scaleWidth;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
            mScrolled = false;
            int childCount = mRecyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = mRecyclerView.getChildAt(i);
                if (view.getRight() >= mRecyclerView.getWidth() / 2) {
                    int offset = (mRecyclerView.getWidth() / 2 - view.getLeft()) % mScaleWidth;
                    if (offset != 0) {
                        if (offset < mScaleWidth / 2) {
                            mRecyclerView.smoothScrollBy(-offset, 0);
                        } else {
                            mRecyclerView.smoothScrollBy(mScaleWidth - offset, 0);
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
}