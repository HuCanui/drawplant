package com.hucanhui.doodles.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v4.graphics.ColorUtils;

/**
 * Created by hucanhui on 2017/3/20.
 */
public class WaterColorDrawView extends LineDrawView {
    public WaterColorDrawView(Context context, int width, int height, int size, int color) {
        super(context, width, height, size, color);
        mPaint.setAlpha(150);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }
}
