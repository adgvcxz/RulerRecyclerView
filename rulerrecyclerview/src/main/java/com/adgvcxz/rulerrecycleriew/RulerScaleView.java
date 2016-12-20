package com.adgvcxz.rulerrecycleriew;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/20.
 */

public class RulerScaleView extends View {

    private float mProportion;
    private boolean mMeasure;

    public RulerScaleView(Context context) {
        super(context);
    }

    public void setProportion(float proportion) {
        mProportion = proportion;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mProportion != 0 && getParent() != null) {
            int parentHeight = ((ViewGroup) getParent()).getHeight();
            if (parentHeight > 0) {
                mMeasure = true;
                heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (parentHeight * mProportion), MeasureSpec.EXACTLY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!mMeasure) {
            post(new Runnable() {
                @Override
                public void run() {
                    requestLayout();
                }
            });
        }
    }
}
