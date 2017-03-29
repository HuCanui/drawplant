package com.hucanhui.drawplant.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.hucanhui.drawplant.R;

/**
 * Created by hucanhui on 2017/3/22.
 */
public class CrayonDrawView extends DrawView implements Runnable {

    private int mStrokerDrawableId = R.drawable.crayon;

    private int mBrushDistance; //画两次bitmap画笔的间隔

    private GestureDetector brushGestureDetector = null;
    private BrushGestureListener brushGestureListener = null;
    private DrawAttribute.DrawStatus drawStatus;

    public CrayonDrawView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        init();
    }

    public CrayonDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mBrushDistance = (int)(mPaintSize * Math.log(mPaintSize) / 7); //间隔默认等于笔画大小

        brushGestureListener = new BrushGestureListener(casualStroke(
                mStrokerDrawableId, mPaintColor, mPaintSize), mBrushDistance, null);

        brushGestureDetector = new GestureDetector(brushGestureListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mBitmapBackground);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        brushGestureDetector.onTouchEvent(event); //触发GestureDetector手势动作

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_UP:

//                mUndoRedo.addBitmap(mBitmap);
                break;
        }
        return true;
    }



    public void setBrushBitmap(DrawAttribute.DrawStatus drawStatus) {
        this.drawStatus = drawStatus;
        Bitmap brushBitmap;
        int brushDistance;
        Paint brushPaint;
        if(drawStatus == DrawAttribute.DrawStatus.CASUAL_WATER) {
            brushBitmap = casualStroke(R.drawable.marker,mPaintColor, mPaintSize);
            brushDistance = 1;
            brushPaint = null;
        }
        else if(drawStatus == DrawAttribute.DrawStatus.CASUAL_CRAYON) {
            brushBitmap = casualStroke(R.drawable.crayon,mPaintColor, mPaintSize);
            brushDistance = brushBitmap.getWidth()/2;
            brushPaint = null;
        }
        else if(drawStatus == DrawAttribute.DrawStatus.CASUAL_COLOR_BIG) {
            brushBitmap = casualStroke(R.drawable.paint,mPaintColor, mPaintSize);
            brushDistance = 2;
            brushPaint = null;
        }
        else
        {
            brushPaint = new Paint();
            brushPaint.setFilterBitmap(true);
            brushPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            brushBitmap = ((BitmapDrawable)this.getResources().
                        getDrawable(R.drawable.eraser)).getBitmap();
            brushDistance = brushBitmap.getWidth()/4;
        }
        brushGestureListener.setBrush(brushBitmap, brushDistance, brushPaint);
    }

    public void setStampBitmaps(DrawAttribute.DrawStatus drawStatus) {
        Bitmap[] brushBitmaps = new Bitmap[4];
        switch(drawStatus) {
            case STAMP_HEART:
                brushBitmaps[0] = casualStroke(R.drawable.stamp0heart,mPaintColor, mPaintSize);
                brushBitmaps[1] = casualStroke(R.drawable.stamp1heart,mPaintColor, mPaintSize);
                brushBitmaps[2] = casualStroke(R.drawable.stamp2heart,mPaintColor, mPaintSize);
                brushBitmaps[3] = casualStroke(R.drawable.stamp3heart,mPaintColor, mPaintSize);
                break;
            case STAMP_STAR:
                brushBitmaps[0] = casualStroke(R.drawable.stamp0star,mPaintColor, mPaintSize);
                brushBitmaps[1] = casualStroke(R.drawable.stamp1star,mPaintColor, mPaintSize);
                brushBitmaps[2] = casualStroke(R.drawable.stamp2star,mPaintColor, mPaintSize);
                brushBitmaps[3] = casualStroke(R.drawable.stamp3star,mPaintColor, mPaintSize);
                break;
            case STAMP_BUBBLE:
                brushBitmaps[0] = casualStroke(R.drawable.stamp0bubble,mPaintColor, mPaintSize);
                brushBitmaps[1] = casualStroke(R.drawable.stamp1bubble,mPaintColor, mPaintSize);
                brushBitmaps[2] = casualStroke(R.drawable.stamp2bubble,mPaintColor, mPaintSize);
                brushBitmaps[3] = casualStroke(R.drawable.stamp3bubble,mPaintColor, mPaintSize);
                break;
            case STAMP_DOTS:
                brushBitmaps[0] = casualStroke(R.drawable.stamp0dots,mPaintColor, mPaintSize);
                brushBitmaps[1] = casualStroke(R.drawable.stamp1dots,mPaintColor, mPaintSize);
                brushBitmaps[2] = casualStroke(R.drawable.stamp2dots,mPaintColor, mPaintSize);
                brushBitmaps[3] = casualStroke(R.drawable.stamp3dots,mPaintColor, mPaintSize);
                break;
        }
//        brushGestureListener.setStampBrush(brushBitmaps);
        this.drawStatus = drawStatus;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            postInvalidate();
        }
    }

    public void cleanPaintBitmap() {
        mCanvas.drawColor(mBitmapBackground, PorterDuff.Mode.DST_OUT);
    }

    public Canvas getPaintCanvas() {
        return mCanvas;
    }

    private Bitmap casualStroke(int drawableId, int color, int size) {
        Bitmap originalMode = ((BitmapDrawable) this.getResources()
                .getDrawable(drawableId)).getBitmap();
        Bitmap newMode = zoomBitmap(originalMode, size, size);

        Bitmap bitmap = newMode.copy(Bitmap.Config.ARGB_8888, true);

        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);

        Paint paintUnder = new Paint();
        paintUnder.setColor(color);
        canvas.drawPaint(paintUnder);

        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        canvas.drawBitmap(newMode, 0, 0, paint);

        return bitmap;
    }

    public void setBrushBitmap(int size, int color) {
        mPaintSize = size;
        mPaintColor = color;
        mBrushDistance = (int)(mPaintSize * Math.log(mPaintSize) / 7);
        setBrushBitmap(drawStatus);
    }

    class BrushGestureListener extends GestureDetector.SimpleOnGestureListener {

        private Bitmap brushBitmap = null;
        private int brushDistance;
        private int halfBrushBitmapWidth;
        private Paint brushPaint = null;


        public BrushGestureListener(Bitmap brushBitmap, int brushDistance,
                                    Paint brushPaint) {
            super();
            setBrush(brushBitmap, brushDistance, brushPaint);
        }

        @Override
        public boolean onDown(MotionEvent e) {

            mCanvas.drawBitmap(brushBitmap, e.getX()
                            - halfBrushBitmapWidth, e.getY() - halfBrushBitmapWidth,
                    brushPaint);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            float beginX = e2.getX();
            float beginY = e2.getY();
            float distance = (float) Math.sqrt(distanceX * distanceX
                    + distanceY * distanceY);
            float x = distanceX / distance, x_ = 0;
            float y = distanceY / distance, y_ = 0;
            while (Math.abs(x_) <= Math.abs(distanceX)
                    && Math.abs(y_) <= Math.abs(distanceY)) {
                x_ += x * brushDistance;
                y_ += y * brushDistance;
                mCanvas.save();
                mCanvas.rotate((float) (Math.random() * 10000), beginX
                        + x_, beginY + y_);
                mCanvas.drawBitmap(brushBitmap, beginX + x_
                        - halfBrushBitmapWidth, beginY + y_
                        - halfBrushBitmapWidth, brushPaint);
                mCanvas.restore();
            }
            CrayonDrawView.this.invalidate();

            return true;
        }

        public void setBrush(Bitmap brushBitmap, int brushDistance,
                             Paint brushPaint) {
            this.brushBitmap = brushBitmap;
            this.brushDistance = brushDistance;
            halfBrushBitmapWidth = brushBitmap.getWidth() / 2;
            this.brushPaint = brushPaint;
        }

        public void freeBitmaps() {
            mBitmap.recycle();
            mBitmap.recycle();
//            mUndoRedo.freeBitmaps();
        }
    }

    public void setPaint(int size, int color){
        setBrushBitmap(size, color);
    }

    public void setPaintSize(int size){
        this.setPaint(size, mPaintColor);
    }
    public void setPaintColor(int color){
        this.setPaint( mPaintSize, color);

    }
}
