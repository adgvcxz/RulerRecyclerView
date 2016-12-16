package com.adgvcxz.rulerrecycleriew;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/15.
 */

public class RulerAdapter extends RecyclerView.Adapter {

    private static final int LINE = 1;
    private static final int LEFT_SPACING = 5;
    private static final int RIGHT_SPACING = 6;


    private int mLineWidth;
    private int mLineSpacing;

    private LayoutInflater mInflater;
    private float mStartValue = 0;
    private float mEndValue = 100;
    private float mPerValue;
    private int mMax = 9;
    private int mLineNumber = 19;
    private int mLeftNumber;
    private int mRightNumber;

    private RulerAdapter() {

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == LINE) {
            RulerItemView view = new RulerItemView(parent.getContext());
            view.set(mMax, mLeftNumber, parent.getHeight() / 2, mLineWidth, mLineSpacing);
            return new RecyclerView.ViewHolder(view) {
            };
        } else if (viewType == RIGHT_SPACING) {
            RulerRightView view = new RulerRightView(parent.getContext());
            int remind = (mLineNumber + 1) % mMax + mMax - mRightNumber - 1;
            view.init(parent.getWidth() / 2 - parent.getPaddingLeft(), parent.getHeight() / 2, mRightNumber, remind, mLineWidth, mLineSpacing);
            return new RecyclerView.ViewHolder(view) {
            };
        } else {
            RulerLeftView view = new RulerLeftView(parent.getContext());
            view.init(parent.getWidth() / 2 - parent.getPaddingLeft(), parent.getHeight() / 2, mLeftNumber, mLineWidth, mLineSpacing);
            return new RecyclerView.ViewHolder(view) {
            };
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return (int) Math.ceil((mLineNumber + 1) / (float) mMax);
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
            new RulerSnapHelper().attachToRecyclerView(recyclerView, line + spacing);
            return this;
        }

        public Builder setNumberAndGroup(int number, int perGroup) {
            mRulerAdapter.mLineNumber = number;
            mRulerAdapter.mMax = perGroup;
            mRulerAdapter.mLeftNumber = (int) (Math.ceil((float)perGroup - 1) / 2);
            mRulerAdapter.mRightNumber = (int) (Math.floor((float)perGroup - 1) / 2);
            return this;
        }

        public RulerAdapter build() {
            return mRulerAdapter;
        }
    }
}
