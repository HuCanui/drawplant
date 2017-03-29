//! \file DynDrawSfView.java
//! \brief 基于SurfaceView的动态绘图视图类
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package com.hucanhui.vgandroid.testview.view;

import rhcad.touchvg.core.GiCoreView;
import rhcad.touchvg.core.GiView;
import rhcad.touchvg.view.CanvasAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//! 基于SurfaceView的动态绘图视图类
public class DynDrawSfView extends SurfaceView implements DynDrawView {
    private static final String TAG = "vgtest";
    private CanvasAdapter mCanvasAdapter;
    private GiView mViewAdapter;
    private GiCoreView mCoreView;
    private long mDrawnTime;
    private long mEndPaintTime;
    private long mBeginTime;

    public DynDrawSfView(Context context) {
        super(context);
        mCanvasAdapter = new CanvasAdapter(this);

        getHolder().addCallback(new SurfaceCallback());
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void setCoreView(GiView viewAdapter, GiCoreView coreView) {
        mViewAdapter = viewAdapter;
        mCoreView = coreView;
    }

    public long getDrawnTime() {
        return mDrawnTime;
    }

    public long getEndPaintTime() {
        return mEndPaintTime;
    }

    public boolean isDrawing() {
        return mCanvasAdapter != null && mCanvasAdapter.isDrawing();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCanvasAdapter.beginPaint(canvas)) {
            canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
            mCoreView.dynDraw(mViewAdapter, mCanvasAdapter);
            mCanvasAdapter.endPaint();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mCanvasAdapter != null) {
            mCanvasAdapter.delete();
            mCanvasAdapter = null;
        }
        mCoreView = null;
        mViewAdapter = null;
        super.onDetachedFromWindow();
    }

    public void doDraw() {
        if (!mCanvasAdapter.isDrawing()) {
            mBeginTime = android.os.SystemClock.uptimeMillis();
            new Thread(new DrawThread()).start();
        }
    }

    private class SurfaceCallback implements SurfaceHolder.Callback {
        public void surfaceCreated(SurfaceHolder holder) {
            doDraw();
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG, "surfaceChanged");
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG, "surfaceDestroyed");
        }
    }

    private class DrawThread implements Runnable {
        public void run() {
            Canvas canvas = null;
            try {
                mEndPaintTime = Integer.MAX_VALUE;
                canvas = getHolder().lockCanvas();
                if (canvas != null) {
                    draw(canvas);
                }
            } catch (Exception e) {
                Log.d(TAG, "DrawThread", e);
            } finally {
                if (canvas != null) {
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }
            mEndPaintTime = android.os.SystemClock.uptimeMillis();
            mDrawnTime = mEndPaintTime - mBeginTime;
        }
    }
}
