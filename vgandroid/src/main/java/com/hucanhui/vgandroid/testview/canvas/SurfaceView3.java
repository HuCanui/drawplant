//! \file SurfaceView3.java
//! \brief Drawing view class with drawing thread based on SurfaceView
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package com.hucanhui.vgandroid.testview.canvas;

import rhcad.touchvg.core.TestCanvas;
import rhcad.touchvg.view.CanvasAdapter;
import com.hucanhui.vgandroid.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

//! Drawing view class with drawing thread based on SurfaceView
public class SurfaceView3 extends SurfaceView {
    private static final String TAG = "vgtest";
    protected CanvasAdapter mCanvas;
    protected int mCreateFlags;
    private long mDrawnTime;
    private long mDrawTimes = 0;
    private float lastX = 50;
    private float lastY = 50;
    private float mPhase = 0;

    public SurfaceView3(Context context) {
        super(context);
        mCanvas = new CanvasAdapter(this);
        mCreateFlags = ((Activity) context).getIntent().getExtras().getInt("flags");
        initCanvas();
        getHolder().addCallback(new SurfaceCallback());

        setZOrderOnTop(true);
        if ((mCreateFlags & 0x1000) == 0) {
            getHolder().setFormat(PixelFormat.TRANSLUCENT);
        }

        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                showTime(mDrawnTime);
                return true;
            }
        });
    }

    private void initCanvas() {
        CanvasAdapter.setHandleImageIDs(new int[] { R.drawable.vgdot1, R.drawable.vgdot2,
                R.drawable.vgdot3, R.drawable.vg_lock, R.drawable.vg_unlock });
    }

    @Override
    public void setBackgroundColor(int color) {
        mCanvas.setBackgroundColor(color);
    }

    protected void showTime(long ms) {
        Activity activity = (Activity) this.getContext();
        String title = activity.getTitle().toString();
        int pos = title.indexOf(' ');
        if (pos >= 0) {
            title = title.substring(0, pos);
        }
        activity.setTitle(title + " - " + Long.toString(ms) + " ms, " + Long.toString(mDrawTimes));
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mCanvas != null) {
            mCanvas.delete();
            mCanvas = null;
        }
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long ms = SystemClock.currentThreadTimeMillis();
        if (mCanvas.beginPaint(canvas)) {
            if ((mCreateFlags & 0x1000) == 0) {
                canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
            } else {
                canvas.drawColor(Color.WHITE);
            }
            TestCanvas.test(mCanvas, mCreateFlags);
            dynDraw(mCanvas);
            mCanvas.endPaint();
        }
        mDrawnTime = SystemClock.currentThreadTimeMillis() - ms;
        mDrawTimes++;
    }

    private void dynDraw(CanvasAdapter canvas) {
        mPhase += 1;
        canvas.setPen(0, 0, 1, mPhase, 0);
        canvas.setBrush(0x22005500, 0);
        mCanvas.drawEllipse(lastX - 50, lastY - 50, 100, 100, true, true);
    }

    class SurfaceCallback implements SurfaceHolder.Callback {
        public void surfaceCreated(SurfaceHolder holder) {
            new Thread(new DrawThread()).start();
            // Start drawing thread will delay due to the failure of lockCanvas().
            // The drawing thread should be started in surfaceCreated().
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG, "surfaceChanged");
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG, "surfaceChanged");
        }
    }

    class DrawThread implements Runnable {
        public void run() {
            while (mCanvas != null) {
                if (!mCanvas.isDrawing()) {
                    Canvas canvas = null;
                    try {
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
                }
            }
        }
    }
}
