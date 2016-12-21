package com.adgvcxz.rulerrecycleriew;

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


    private int mLineWidth;
    private int mLineSpacing;

    private int mMax = 9;
    private int mLineNumber = 17;
    private int mLeftNumber;
    private int mRightNumber;

    private RulerAdapter() {

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == LINE) {
            RulerItemView view = new RulerItemView(parent.getContext());
            view.init(mMax, mLeftNumber, mLineWidth, mLineSpacing);
            view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new RecyclerView.ViewHolder(view) {
            };
        } else if (viewType == RIGHT_SPACING) {
            RulerRightView view = new RulerRightView(parent.getContext());
            int remind = (mLineNumber - mRightNumber) % mMax;
            remind = remind == 0 ? mMax : remind;
            view.initWhenEdge(parent.getWidth() / 2 - parent.getPaddingRight(), mLeftNumber, mRightNumber, mLineNumber, remind, mLineWidth, mLineSpacing);
            if (remind >= mRightNumber && mOnRulerScrollListener != null) {
                view.adjustRightTextView(mOnRulerScrollListener.getScaleStr((getItemCount() - 1) * mMax));
            }
            view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new RecyclerView.ViewHolder(view) {
            };
        } else {
            RulerLeftView view = new RulerLeftView(parent.getContext());
            view.initWhenEdge(parent.getWidth() / 2 - parent.getPaddingLeft(), parent.getWidth() / 2 - parent.getPaddingRight(),
                    mLeftNumber, mRightNumber, mLineNumber, mLineWidth, mLineSpacing);
            if (mOnRulerScrollListener != null) {
                view.adjustLeftTextView(mOnRulerScrollListener.getScaleStr(0));
            }
            view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new RecyclerView.ViewHolder(view) {
            };
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mOnRulerScrollListener != null && getItemViewType(position) == LINE) {
            String str = mOnRulerScrollListener.getScaleStr(position * mMax);
            ((RulerItemView) holder.itemView).adjustTextView(str);
        }
        holder.itemView.requestLayout();
    }

    @Override
    public int getItemCount() {
        if (mLeftNumber > 0) {
            if (mRightNumber >= mLineNumber) {
                return 1;
            } else {
                if (mMax + mRightNumber + 1 >= mLineNumber) {
                    return 2;
                } else {
                    int number = mLineNumber - mRightNumber - 1;
                    return 2 + number / mMax;
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

        public Builder() {
            mRulerAdapter = new RulerAdapter();
        }

        public Builder setWidthAndSpacing(RecyclerView recyclerView, int line, int spacing) {
            mRulerAdapter.mLineWidth = line;
            mRulerAdapter.mLineSpacing = spacing;
            new RulerSnapHelper().attachToRecyclerView(recyclerView, line, spacing);
            return this;
        }

        public Builder setNumberAndGroup(int number, int perGroup) {
            mRulerAdapter.mLineNumber = number;
            mRulerAdapter.mMax = perGroup;
            mRulerAdapter.mLeftNumber = (int) (Math.ceil((float) (perGroup - 1) / 2));
            mRulerAdapter.mRightNumber = (int) (Math.floor((float) (perGroup - 1) / 2));
            return this;
        }

        public Builder setOnRulerScrollListener(OnRulerScrollListener listener) {
            mRulerAdapter.mOnRulerScrollListener = listener;
            return this;
        }

        public RulerAdapter build() {
            return mRulerAdapter;
        }
    }
}
