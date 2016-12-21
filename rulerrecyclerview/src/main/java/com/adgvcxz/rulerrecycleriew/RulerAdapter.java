package com.adgvcxz.rulerrecycleriew;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerAdapter extends RecyclerView.Adapter {

    private static final int LINE = 1;
    private static final int LEFT_SPACING = 5;
    private static final int RIGHT_SPACING = 6;

    private OnRulerScrollListener mOnRulerScrollListener;

    /**
     * 刻度线的宽度
     */
    private int mLineWidth = 6;

    /**
     * 刻度的宽度
     */
    private int mScaleWidth = 18;

    /**
     * 每组有多少个刻度
     */
    private int mGroupNumber = 10;

    /**
     * 一共多少刻度
     */
    private int mScaleNumber = 100;

    /**
     * 左边的刻度数量
     */
    private int mLeftNumber;

    /**
     * 右边的刻度数量
     */
    private int mRightNumber;

    /**
     * 刻度线颜色
     */
    private int mScaleLineColor = Color.GRAY;

    /**
     * 是否显示边界
     */
    private boolean mShowEdge;

    /**
     * 初始化位置
     */
    private int mStartScaleLine = 0;

    /**
     * 带有数字的刻度长度
     */
    private float mMiddleLine = 1.0f;

    /**
     * 常规刻度线的长度
     */
    private float mNormalLine = 0.5f;

    /**
     * 刻度文字的大小
     */
    private float mTextSize = 12;

    /**
     * 刻度文字的颜色
     */
    private int mTextColor = Color.GRAY;

    private RulerAdapter() {
        mLeftNumber = (int) (Math.ceil((float) (mGroupNumber - 1) / 2));
        mRightNumber = (int) (Math.floor((float) (mGroupNumber - 1) / 2));
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        RulerBaseItemView view;

        if (viewType == LINE) {
            view = new RulerItemView(parent.getContext(), mScaleLineColor, mLineWidth, mScaleWidth, mMiddleLine, mNormalLine);
            view.setTextSizeAndColor(mTextSize, mTextColor);
            ((RulerItemView) view).init(mGroupNumber, mLeftNumber);
        } else if (viewType == RIGHT_SPACING) {
            view = new RulerRightView(parent.getContext(), mScaleLineColor, mLineWidth, mScaleWidth, mMiddleLine, mNormalLine);
            view.setTextSizeAndColor(mTextSize, mTextColor);
            int remind = (mScaleNumber - mRightNumber) % mGroupNumber;
            remind = remind == 0 ? mGroupNumber : remind;
            if (mShowEdge) {
                ((RulerRightView) view).initWhenEdge(parent.getWidth() / 2 - parent.getPaddingRight(), mLeftNumber, mRightNumber, mScaleNumber, remind);
            } else {
                ((RulerRightView) view).init(parent.getWidth() / 2 - parent.getPaddingRight(), mLeftNumber, remind);
            }
            if (remind >= mRightNumber && mOnRulerScrollListener != null) {
                view.adjustTextView(mOnRulerScrollListener.getScaleValue((getItemCount() - 1) * mGroupNumber));
            }
        } else {
            view = new RulerLeftView(parent.getContext(), mScaleLineColor, mLineWidth, mScaleWidth, mMiddleLine, mNormalLine);
            view.setTextSizeAndColor(mTextSize, mTextColor);
            if (mShowEdge) {
                parent.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("zhaow", parent.getWidth() + "   " + parent.getPaddingLeft());
                    }
                });
                ((RulerLeftView) view).initWhenEdge(parent.getWidth() / 2 - parent.getPaddingLeft(), parent.getWidth() / 2 - parent.getPaddingRight(),
                        mLeftNumber, mRightNumber, mScaleNumber);
            } else {
                ((RulerLeftView) view).init(parent.getWidth() / 2 - parent.getPaddingLeft(), parent.getWidth() / 2 - parent.getPaddingRight(), mRightNumber, mScaleNumber);
            }
            if (mOnRulerScrollListener != null) {
                view.adjustTextView(mOnRulerScrollListener.getScaleValue(0));
            }
        }
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("zhaow", (holder.itemView).getWidth() + "abcd");
        if (mOnRulerScrollListener != null && getItemViewType(position) == LINE) {
            String str = mOnRulerScrollListener.getScaleValue(position * mGroupNumber);
            ((RulerItemView) holder.itemView).adjustTextView(str);
        }
        holder.itemView.requestLayout();
    }

    @Override
    public int getItemCount() {
        if (mLeftNumber > 0) {
            if (mRightNumber >= mScaleNumber) {
                return 1;
            } else {
                if (mGroupNumber + mRightNumber + 1 >= mScaleNumber) {
                    return 2;
                } else {
                    int number = mScaleNumber - mRightNumber - 1;
                    return 2 + number / mGroupNumber;
                }
            }
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return LEFT_SPACING;
        }
        if (position == getItemCount() - 1) {
            return RIGHT_SPACING;
        }
        return LINE;
    }

    public static class Builder {

        private RulerAdapter mRulerAdapter;
        private RulerSnapHelper mRulerSnapHelper;
        private RecyclerView mRecyclerView;

        public Builder(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
            mRulerAdapter = new RulerAdapter();
            mRulerSnapHelper = new RulerSnapHelper();
            mRulerSnapHelper.attachToRecyclerView(recyclerView, mRulerAdapter.mLineWidth
                    , mRulerAdapter.mScaleWidth, mRulerAdapter.mLeftNumber, mRulerAdapter.mRightNumber);
        }

        public Builder setLineAndScale(int lineWidth, int scaleWidth) {
            mRulerAdapter.mLineWidth = lineWidth;
            mRulerAdapter.mScaleWidth = scaleWidth;
            mRulerSnapHelper.setLineAndWidth(lineWidth, scaleWidth);
            return this;
        }

        public Builder setNumberAndGroup(int number, int groupNumber) {
            mRulerAdapter.mScaleNumber = number;
            mRulerAdapter.mGroupNumber = groupNumber;
            mRulerAdapter.mLeftNumber = (int) (Math.ceil((float) (groupNumber - 1) / 2));
            mRulerAdapter.mRightNumber = (int) (Math.floor((float) (groupNumber - 1) / 2));
            mRulerSnapHelper.setLeftAndRight(mRulerAdapter.mLeftNumber, mRulerAdapter.mRightNumber);
            return this;
        }

        public Builder setOnRulerScrollListener(OnRulerScrollListener listener) {
            mRulerAdapter.mOnRulerScrollListener = listener;
            mRulerSnapHelper.setOnRulerScrollListener(listener);
            return this;
        }

        public Builder setScaleLineColor(int color) {
            mRulerAdapter.mScaleLineColor = color;
            return this;
        }

        public Builder setEdge(boolean show) {
            mRulerAdapter.mShowEdge = show;
            return this;
        }

        public Builder setStartScaleLine(int startScaleLine) {
            mRulerAdapter.mStartScaleLine = startScaleLine;
            return this;
        }

        public Builder setLineLength(float middle, float normal) {
            mRulerAdapter.mMiddleLine = middle;
            mRulerAdapter.mNormalLine = normal;
            return this;
        }

        public Builder setTextSizeAndColor(float size, int color) {
            mRulerAdapter.mTextSize = size;
            mRulerAdapter.mTextColor = color;
            return this;
        }

        public RulerAdapter build() {
            if (mRulerAdapter.mStartScaleLine > 0) {
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        int startScaleLine = mRulerAdapter.mStartScaleLine;
                        int scaleWidth = mRulerAdapter.mScaleWidth / 2 * 2 + mRulerAdapter.mLineWidth;
                        mRecyclerView.scrollBy(startScaleLine * scaleWidth, 0);
                    }
                });
            }
            return mRulerAdapter;
        }
    }
}
