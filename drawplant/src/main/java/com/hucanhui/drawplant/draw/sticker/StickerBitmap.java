package com.hucanhui.drawplant.draw.sticker;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import com.hucanhui.drawplant.draw.DrawAttribute;

public class StickerBitmap {
	private static final int NONE = 0; 
    private static final int DRAG = 1; 
    private static final int ZOOM = 2; 	 
    public Bitmap bitmap;
    private Matrix bitmapMatrix;	    
    private int mode;
    private float lastXDrag; 
    private float lastYDrag;
    private PointF onDownZoomMidPoint; 
    private float onDownZoomDist;
    private float onDownZoomRotation = 0; 
    private Matrix onDownMatrix; 
    private Matrix onMoveMatrix; 
    private boolean isLock;
    
    private View containter;
    private StickerBitmapList stickerBitmapList;
     
    public StickerBitmap(View containter, StickerBitmapList stickerBitmapList, Bitmap bitmap) {
    	this.bitmap = bitmap;
    	bitmapMatrix = new Matrix(); 
    	onDownMatrix = new Matrix();
    	onMoveMatrix = new Matrix();
    	onDownZoomMidPoint = new PointF();
    	this.containter = containter;
    	this.stickerBitmapList = stickerBitmapList;
    	this.isLock = false;
    }
    
    public void drawBitmap(Canvas canvas) {
    	canvas.drawBitmap(bitmap, bitmapMatrix,null); 	
    }
    
	public boolean onTouchEvent(MotionEvent event) { 	
        switch (event.getAction() & MotionEvent.ACTION_MASK) { 
        case MotionEvent.ACTION_DOWN: 
        	if(!isLock) {
	        	stickerBitmapList.setIsStickerToolsDraw(false,null);
	            mode = DRAG; 
	            lastXDrag = event.getX(); 
	            lastYDrag = event.getY(); 
	            onDownMatrix.set(bitmapMatrix); 
        	}
            break; 
        case MotionEvent.ACTION_POINTER_DOWN:
        	if(!isLock) {
		        mode = ZOOM; 
		        onDownZoomDist = spacing(event); 
		        onDownZoomRotation = rotation(event); 
		        onDownMatrix.set(bitmapMatrix); 
		        midPoint(onDownZoomMidPoint, event);
        	}
            break; 
        case MotionEvent.ACTION_MOVE: 
        	if(!isLock) {
	            if (mode == ZOOM) { 
	            	onMoveMatrix.set(onDownMatrix); 
	                float rotation = rotation(event) - onDownZoomRotation; 
	                float newDist = spacing(event); 
	                float scale = newDist / onDownZoomDist; 
	                onMoveMatrix.postScale(scale, scale, onDownZoomMidPoint.x, onDownZoomMidPoint.y); 
	                onMoveMatrix.postRotate(rotation, onDownZoomMidPoint.x, onDownZoomMidPoint.y);
	                //if (matrixCheck(onMoveMatrix) == false) { 
	                bitmapMatrix.set(onMoveMatrix); 
	                containter.postInvalidate();
	                //} 
	            } else if (mode == DRAG) { 
	            	onMoveMatrix.set(onDownMatrix); 
	            	onMoveMatrix.postTranslate(event.getX()-lastXDrag, event.getY()-lastYDrag);
	                //if (matrixCheck(onMoveMatrix) == false) { 
	                bitmapMatrix.set(onMoveMatrix); 
	                containter.postInvalidate();
	                //} 
	            } 
        	}
            break; 
        case MotionEvent.ACTION_UP: 
        case MotionEvent.ACTION_POINTER_UP: 
        	PointF leftTopPoint = new PointF();
        	this.getLeftTopPointF(leftTopPoint);       	
        	stickerBitmapList.setIsStickerToolsDraw(true,leftTopPoint);
        	mode = NONE; break; 
        } 
        return true;        
    } 
	
    /*private boolean matrixCheck(Matrix matrix) { 
        float[] f = new float[9]; 
        matrix.getValues(f); 
        int screenWidth = DrawAttribute.screenWidth;
        int screenHeight = DrawAttribute.screenHeight;
        // ͼƬ4����������� 
        float x1 = f[0] * 0 + f[1] * 0 + f[2]; 
        float y1 = f[3] * 0 + f[4] * 0 + f[5]; 
        float x2 = f[0] * bitmap.getWidth() + f[1] * 0 + f[2]; 
        float y2 = f[3] * bitmap.getWidth() + f[4] * 0 + f[5]; 
        float x3 = f[0] * 0 + f[1] * bitmap.getHeight() + f[2]; 
        float y3 = f[3] * 0 + f[4] * bitmap.getHeight() + f[5]; 
        float x4 = f[0] * bitmap.getWidth() + f[1] * bitmap.getHeight() + f[2]; 
        float y4 = f[3] * bitmap.getWidth() + f[4] * bitmap.getHeight() + f[5]; 
        // ͼƬ�ֿ�� 
        double width = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); 
        // ���ű����ж� 
        if (width < screenWidth / 3 || width > screenWidth * 3) { 
            return true; 
        } 
        // �����ж� 
        if ((x1 < screenWidth / 3 && x2 < screenWidth / 3 
                && x3 < screenWidth / 3 && x4 < screenWidth / 3) 
                || (x1 > screenWidth * 2 / 3 && x2 > screenWidth * 2 / 3 
                        && x3 > screenWidth * 2 / 3 && x4 > screenWidth * 2 / 3) 
                || (y1 < screenHeight / 3 && y2 < screenHeight / 3 
                        && y3 < screenHeight / 3 && y4 < screenHeight / 3) 
                || (y1 > screenHeight * 2 / 3 && y2 > screenHeight * 2 / 3 
                        && y3 > screenHeight * 2 / 3 && y4 > screenHeight * 2 / 3)) { 
            return true; 
        } 
        return false; 
    }*/
	
	public void mirrorTheBitmap() {
		Matrix matrix = new Matrix();
		matrix.preScale(-1, 1);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
				matrix, true);
        containter.postInvalidate();
	}
	
	public boolean isLock() {
		return isLock;
	}

	public void setLock() {
		if(isLock)isLock = false;
		else isLock = true;
	}

	//�жϵ��ǲ�����λͼ��
	public boolean isPointInsideBitmap(float x, float y) {
		float[] points = new float[8];
		points[0] = 0;points[1] = 0;
		points[2] = 0;points[3] = bitmap.getHeight();
		points[4] = bitmap.getWidth();points[5] = bitmap.getHeight();
		points[6] = bitmap.getWidth();points[7] = 0;
		bitmapMatrix.mapPoints(points);
		float A,B,C;		
		for(int i = 0; i < 4; i++) {
			A = -(points[(3+2*i)%8] - points[1+2*i]);
			B = points[2*(i+1)%8] - points[2*i];
			C = -(A * points[2*i] + B * points[1+2*i]);
			if (A * x + B * y + C > 0)return false;
		}
		return true;
	}
	
	//������ͼ����С���ε����Ͻ�
	public void getLeftTopPointF(PointF leftTopPoint) {
		float[] points = new float[8];
		points[0] = 0;points[1] = 0;
		points[2] = 0;points[3] = bitmap.getHeight();
		points[4] = bitmap.getWidth();points[5] = bitmap.getHeight();
		points[6] = bitmap.getWidth();points[7] = 0;
		bitmapMatrix.mapPoints(points);
		float leftTopX = points[0];
		float leftTopY = points[1];
		for(int i = 2; i < 8; i += 2) {
			if(points[i] < leftTopX)leftTopX = points[i];
		}
		for(int i = 1; i < 8; i += 2) {
			if(points[i] < leftTopY)leftTopY = points[i];
		}
		leftTopPoint.set(leftTopX, leftTopY);
	}
 
    // ������������ 
    private float spacing(MotionEvent event) { 
        float x = event.getX(0) - event.getX(1); 
        float y = event.getY(0) - event.getY(1); 
        return (float)Math.sqrt(x * x + y * y); 
    } 
     
    // ȡ�������ĵ� 
    private void midPoint(PointF point, MotionEvent event) { 
        float x = event.getX(0) + event.getX(1); 
        float y = event.getY(0) + event.getY(1); 
        point.set(x / 2, y / 2); 
    } 
 
    // ȡ��ת�Ƕ� 
    private float rotation(MotionEvent event) { 
        double delta_x = (event.getX(0) - event.getX(1)); 
        double delta_y = (event.getY(0) - event.getY(1)); 
        double radians = Math.atan2(delta_y, delta_x); 
        return (float) Math.toDegrees(radians); 
    } 
 
    // ���ƶ��������Լ���ת���ͼ�㱣��Ϊ��ͼƬ 
    public Bitmap CreatNewPhoto() { 
        Bitmap bitmap = Bitmap.createBitmap(DrawAttribute.screenWidth, 
        		DrawAttribute.screenHeight, Config.ARGB_8888); // ����ͼƬ 
        Canvas canvas = new Canvas(bitmap); // �½����� 
        canvas.drawBitmap(bitmap, bitmapMatrix, null); // ��ͼƬ 
        canvas.save(Canvas.ALL_SAVE_FLAG); // ���滭�� 
        canvas.restore(); 
        return bitmap; 
    }
}