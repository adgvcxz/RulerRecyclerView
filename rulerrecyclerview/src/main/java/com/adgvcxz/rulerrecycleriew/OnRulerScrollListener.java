package com.adgvcxz.rulerrecycleriew;

/**
 * zhaowei
 * Created by zhaowei on 2016/12/20.
 */

public interface OnRulerScrollListener {

    /**
     * 根据刻度返回刻度值
     */
    String getScaleValue(int scale);

    /**
     * 刻度发生变化
     */
    void onScaleChange(int scale);

}
