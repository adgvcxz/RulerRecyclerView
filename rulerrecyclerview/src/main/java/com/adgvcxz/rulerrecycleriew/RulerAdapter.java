package com.adgvcxz.rulerrecycleriew;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

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

    private RulerLeftView mRulerLeftView;
    private RulerRightView mRulerRightView;

    private int mLeftWidth;
    private int mRightWidth;

    private RulerAdapter(final RecyclerView recyclerView) {
        mLeftNumber = (int) (Math.ceil((float) (mGroupNumber - 1) / 2));
        mRightNumber = (int) (Math.floor((float) (mGroupNumber - 1) / 2));
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerView.getWidth() > 0) {
                    mLeftWidth = recyclerView.getWidth() / 2 - recyclerView.getPaddingLeft();
                    mRightWidth = recyclerView.getWidth() / 2 - recyclerView.getPaddingRight();
                    int number = (mScaleNumber - mRightNumber) % mGroupNumber;
                    final int remind = number == 0 ? mGroupNumber : number;
                    if (mShowEdge) {
                        if (mRulerLeftView != null) {
                            mRulerLeftView.initWhenEdge(mLeftWidth, mRightNumber, mLeftNumber, mRightNumber, mScaleNumber);
                            if (mOnRulerScrollListener != null) {
                                mRulerLeftView.adjustTextView(mOnRulerScrollListener.getScaleValue(0));
                            }
                            scrollOriginDistance(recyclerView);
                        }
                        if (mRulerRightView != null) {
                            mRulerRightView.initWhenEdge(mRightWidth, mLeftNumber, mRightNumber, mScaleNumber, remind);
                            if (remind >= mRightNumber && mOnRulerScrollListener != null) {
                                mRulerRightView.adjustTextView(mOnRulerScrollListener.getScaleValue((getItemCount() - 1) * mGroupNumber));
                            }
                        }
                    } else {
                        if (mRulerLeftView != null) {
                            mRulerLeftView.init(mLeftWidth, mRightWidth, mRightNumber, mScaleNumber);
                            if (mOnRulerScrollListener != null) {
                                mRulerLeftView.adjustTextView(mOnRulerScrollListener.getScaleValue(0));
                            }
                            scrollOriginDistance(recyclerView);
                        }
                        if (mRulerRightView != null) {
                            mRulerRightView.init(mRightWidth, mLeftNumber, remind);
                            if (remind >= mRightNumber && mOnRulerScrollListener != null) {
                                mRulerRightView.adjustTextView(mOnRulerScrollListener.getScaleValue((getItemCount() - 1) * mGroupNumber));
                            }
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    recyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (viewType == LINE) {
            final RulerItemView view = new RulerItemView(parent.getContext(), mScaleLineColor, mLineWidth, mScaleWidth, mMiddleLine, mNormalLine);
            view.setTextSizeAndColor(mTextSize, mTextColor);
            view.init(mGroupNumber, mLeftNumber);
            return new RecyclerView.ViewHolder(view) {
            };
        } else if (viewType == RIGHT_SPACING) {
            mRulerRightView = new RulerRightView(parent.getContext(), mScaleLineColor, mLineWidth, mScaleWidth, mMiddleLine, mNormalLine);
            mRulerRightView.setTextSizeAndColor(mTextSize, mTextColor);
            if (!mRulerRightView.isInit()) {
                int number = (mScaleNumber - mRightNumber) % mGroupNumber;
                final int remind = number == 0 ? mGroupNumber : number;
                if (mShowEdge) {
                    mRulerRightView.initWhenEdge(mRightWidth, mLeftNumber, mRightNumber, mScaleNumber, remind);
                    if (remind >= mRightNumber && mOnRulerScrollListener != null) {
                        mRulerRightView.adjustTextView(mOnRulerScrollListener.getScaleValue((getItemCount() - 1) * mGroupNumber));
                    }
                } else {
                    mRulerRightView.init(mRightWidth, mLeftNumber, remind);
                    if (remind >= mRightNumber && mOnRulerScrollListener != null) {
                        mRulerRightView.adjustTextView(mOnRulerScrollListener.getScaleValue((getItemCount() - 1) * mGroupNumber));
                    }
                }
            }
            return new RecyclerView.ViewHolder(mRulerRightView) {
            };
        } else {
            mRulerLeftView = new RulerLeftView(parent.getContext(), mScaleLineColor, mLineWidth, mScaleWidth, mMiddleLine, mNormalLine);
            mRulerLeftView.setTextSizeAndColor(mTextSize, mTextColor);
            return new RecyclerView.ViewHolder(mRulerLeftView) {
            };
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mOnRulerScrollListener != null && getItemViewType(position) == LINE) {
            String str = mOnRulerScrollListener.getScaleValue(position * mGroupNumber);
            ((RulerItemView) holder.itemView).adjustTextView(str);
        }
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

    private void scrollOriginDistance(final RecyclerView recyclerView) {
        int scaleWidth = mScaleWidth / 2 * 2 + mLineWidth;
        final int distance = mStartScaleLine * scaleWidth;
        if (mStartScaleLine > 0) {
            mRulerLeftView.post(new Runnable() {
                @Override
                public void run() {
                    recyclerView.scrollBy(distance, 0);
                }
            });
        }
    }


    public static class Builder {

        private RulerAdapter mRulerAdapter;
        private RulerSnapHelper mRulerSnapHelper;

        public Builder(final RecyclerView recyclerView) {
            mRulerAdapter = new RulerAdapter(recyclerView);
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
            return mRulerAdapter;
        }
    }
}
