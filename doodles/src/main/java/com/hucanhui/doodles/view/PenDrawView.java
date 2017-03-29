package com.hucanhui.doodles.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * Created by hucanhui on 2017/3/20.
 * 毛笔
 */
public class PenDrawView extends DrawView{
    public PenDrawView(Context context, int width, int height, int size, int color) {
        super(context, width, height, size, color);
        BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(1.0F, BlurMaskFilter.Blur.SOLID);
        this.mPaint.setMaskFilter(localBlurMaskFilter);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setDither(true);
        mPath = new Path();
    }

    private Path mPath;
    private float mX, mY;
    private VelocityTracker velocityTracker;

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(velocityTracker==null){
                    //初始化velocityTracker的对象 vt 用来监测motionevent的动作
                    velocityTracker=VelocityTracker.obtain();
                }else{
                    velocityTracker.clear();
                }
                velocityTracker.addMovement(event);
                onTouchDown(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                //代表的是监测每1000毫秒手指移动的距离（像素）即m/px，这是用来控制vt的单位，若括号内为1，则代表1毫秒手指移动过的像素数即ms/px
                velocityTracker.computeCurrentVelocity(10);
                controlPaint(Math.sqrt(velocityTracker.getXVelocity()*velocityTracker.getXVelocity() + velocityTracker.getYVelocity()*velocityTracker.getYVelocity()));
                onTouchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.recycle();
                velocityTracker= null;
                onTouchUp(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                velocityTracker.recycle();
                velocityTracker= null;
                break;
            default:
                break;
        }
        return true;
    }

    private void onTouchDown(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void onTouchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx > 0 || dy > 0) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        } else if (dx == 0 || dy == 0) {
            mPath.quadTo(mX, mY, (x + 1 + mX) / 2, (y + 1 + mY) / 2);
            mX = x + 1;
            mY = y + 1;
        }
        mCanvas.drawPath(mPath, mPaint);
    }

    private void onTouchUp(float x, float y) {
        mPath.reset();

        mUndoRedo.addBitmap(mBitmap);
    }

    public void setPaint(int size, int color) {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(size);
        mPaint.setColor(color);
    }

    public void setPaintColor(int color){
        mPaintColor = color;
        mPaint.setColor(color);
    }

    public void setPaintSize(int size){
        mPaintSize = size;
        mPaint.setStrokeWidth(size);
    }

    public void freeBitmaps() {
        mBitmap.recycle();
        mUndoRedo.freeBitmaps();
    }


    private final float KEY_PAINT_WIDTH=2.5f;
    private float controlPaint(double v){
        //余弦函数
        //y=0.5*[cos(x*PI)+1]
        float result=KEY_PAINT_WIDTH*mPaintSize;
        if(v<0){
        }else if(v<1){
            result=(float) (0.5*mPaintSize*KEY_PAINT_WIDTH*(Math.cos(v*Math.PI)+1));
        }else{
            result=((float) (mPaintSize/v>0.1?mPaintSize/v:0.1));
        }
        mPaint.setStrokeWidth(result);
        return result;
    }
}
