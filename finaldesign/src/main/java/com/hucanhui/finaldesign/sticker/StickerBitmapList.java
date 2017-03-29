package com.hucanhui.finaldesign.sticker;

import com.hucanhui.finaldesign.view.DrawView;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;

public class StickerBitmapList {
	private StickerBitmap[] stickerBitmaps;
	private int capacity;
	private int size;
	
	private int onTouchStickerBitmapIndex;
	
	private StickerTools stickerTools;
	private boolean isStickerToolsDraw;
	private final int TOOLSTOTALDRAWTIME = 75;
	private int stickerToolsDrawTime;
	
	private DrawView container;
	
	public StickerBitmapList(DrawView container) {
		capacity = 15;
		size = 0;
		onTouchStickerBitmapIndex = -1;
		stickerBitmaps = new StickerBitmap[capacity];
		stickerTools = new StickerTools(container,this);
		this.container = container;
		isStickerToolsDraw = false;
		stickerToolsDrawTime = 0;
	}
	
	//��list�������ͼ
	public boolean addStickerBitmap(StickerBitmap stickerBitmap) {
		if(capacity > size) {
			stickerBitmaps[size++] = stickerBitmap;
			return true;
		}
		return false;
	}
	
	//���������
	public void setOnTouchStickerBitmapLock() {
		stickerBitmaps[onTouchStickerBitmapIndex].setLock();
	}
	
	//����ͼ�ᵽ��ǰ
	public void bringOnTouchStickerBitmapToFront() {
		StickerBitmap onTouchStickerBitmap = stickerBitmaps[onTouchStickerBitmapIndex];
		for(int i = onTouchStickerBitmapIndex; i < size - 1 ; i++) {
			stickerBitmaps[i] = stickerBitmaps[i+1];
		}
		stickerBitmaps[size - 1] = onTouchStickerBitmap;
	}
	
	//ˮƽ��ת
	public void mirrorStickerBitmap() {
		stickerBitmaps[onTouchStickerBitmapIndex].mirrorTheBitmap();
	}
	
	//����ͼ�����ڻ�����
	public void drawOnTouchStickerBitmapInCanvas() {
		stickerBitmaps[onTouchStickerBitmapIndex].drawBitmap(container.getPaintCanvas());
		deleteOnTouchStickerBitmap();
	}
	
	//ɾ����ͼ
	public void deleteOnTouchStickerBitmap() {
		for(int i = onTouchStickerBitmapIndex; i < size - 1 ; i++) {
			stickerBitmaps[i] = stickerBitmaps[i+1];
		}
		size--;
		isStickerToolsDraw = false;
	}
	
	//����������ͼ�͹���
	public void drawStickerBitmapList(Canvas canvas) {
		for(int i = 0; i < size; i++) {
			stickerBitmaps[i].drawBitmap(canvas);
		}
		if(isStickerToolsDraw) {
			stickerTools.drawTools(canvas, stickerBitmaps[onTouchStickerBitmapIndex].isLock());
			stickerToolsDrawTime++;
			if(stickerToolsDrawTime >= TOOLSTOTALDRAWTIME) {
				stickerToolsDrawTime = 0;
				isStickerToolsDraw = false;
			}
		}
	}
	
	//�����Ƿ���ƹ���
	public void setIsStickerToolsDraw(boolean isStickerToolsDraw, PointF leftTopPoint) {
		this.isStickerToolsDraw = isStickerToolsDraw;
		if(isStickerToolsDraw) {
			stickerTools.setStartLeftTop(leftTopPoint);
			stickerToolsDrawTime = 0;
		}
	}

	//����onTouch�¼�
	public void onTouchEvent(MotionEvent event) {
		stickerBitmaps[onTouchStickerBitmapIndex].onTouchEvent(event);
	}
	
    //���ص����ͼ���±�,1��ʾ��ͼ������,0��ʾ��ͼ,-1��ʾ����
	public int getOnTouchType(float x, float y) {
		if(isStickerToolsDraw && stickerTools.setOnTouchEvent(x, y)) {
			return 1;
		}
		for(int i = size - 1; i >=0; i--) {
			if(stickerBitmaps[i].isPointInsideBitmap(x, y)){
				onTouchStickerBitmapIndex = i;
				return 0;		
			}
		}
		return -1;
	}
	
	public void freeBitmaps() {
		for(int i = 0; i < size; i++) {
			stickerBitmaps[i].bitmap.recycle();
		}
	}
}