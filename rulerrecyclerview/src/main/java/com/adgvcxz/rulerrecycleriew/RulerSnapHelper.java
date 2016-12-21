package com.adgvcxz.rulerrecycleriew;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerSnapHelper extends RecyclerView.OnScrollListener {

    private RecyclerView mRecyclerView;
    private boolean mScrolled = false;
    private int mScaleWidth;
    private int mLeftNumber;
    private int mRightNumber;
    private OnRulerScrollListener mOnRulerScrollListener;
    private int mLastNumber = -1;

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView, int lineWidth, int scaleWidth, int leftNumber, int rightNumber) {
        if (recyclerView != null) {
            mRecyclerView = recyclerView;
            recyclerView.addOnScrollListener(this);
            mLeftNumber = leftNumber;
            mRightNumber = rightNumber;
            mScaleWidth = lineWidth + scaleWidth / 2 * 2;
        }
    }

    public void setLineAndWidth(int lineWidth, int scaleWidth) {
        mScaleWidth = lineWidth + scaleWidth / 2 * 2;
    }

    public void setLeftAndRight(int left, int right) {
        mLeftNumber = left;
        mRightNumber = right;
    }

    public void setOnRulerScrollListener(OnRulerScrollListener onRulerScrollListener) {
        this.mOnRulerScrollListener = onRulerScrollListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
            mScrolled = false;
            int childCount = mRecyclerView.getChildCount();
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            for (int i = 0; i < childCount; i++) {
                View view = mRecyclerView.getChildAt(i);
                if (view.getRight() >= mRecyclerView.getWidth() / 2) {
                    int offset;
                    int position = layoutManager.getPosition(view);
                    if (position == 0) {
                        offset = ((-view.getLeft() + recyclerView.getPaddingLeft()) % mScaleWidth);
                    } else {
                        offset = (mRecyclerView.getWidth() / 2 - mScaleWidth / 2 - view.getLeft()) % mScaleWidth;
                    }
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
            if (mOnRulerScrollListener != null) {
                int childCount = mRecyclerView.getChildCount();
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                for (int i = 0; i < childCount; i++) {
                    View view = mRecyclerView.getChildAt(i);
                    if (view.getRight() >= mRecyclerView.getWidth() / 2) {
                        int position = layoutManager.getPosition(view);
                        int number;
                        if (position == 0) {
                            number = (-view.getLeft() + recyclerView.getPaddingLeft() + mScaleWidth / 2) / mScaleWidth;
                        } else {
                            number = (mRecyclerView.getWidth() / 2 - view.getLeft()) / mScaleWidth + mRightNumber + 1 + (position - 1) * (mRightNumber + mLeftNumber + 1);
                        }
                        if (number != mLastNumber && mOnRulerScrollListener != null) {
                            mOnRulerScrollListener.onScaleChange(number);
                            mLastNumber = number;
                        }
                        break;
                    }
                }
            }
        }
    }
}