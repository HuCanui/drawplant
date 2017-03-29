package com.hucanhui.drawplant.draw.sticker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.hucanhui.drawplant.R;
import com.hucanhui.drawplant.draw.DrawAttribute;

class StickerTools {
	private Bitmap lockBitmap;
	private Bitmap unlockBitmap;
	private Bitmap mirrorBitmap;
	private Bitmap bringToFrontBitmap;
	private Bitmap stampBitmap;
	private Bitmap trashBitmap;
	private StickerBitmapList stickerBitmapList;
	private float startLeftTopX;
	private float startLeftTopY;
	private int bitmapWidth;
	private int bitmapHeight;
	private final int toolsNum = 5;
	
	public StickerTools(View container, StickerBitmapList stickerBitmapList) {
		lockBitmap = ((BitmapDrawable)container.getResources().
				getDrawable(R.drawable.toolslock)).getBitmap();
		unlockBitmap = ((BitmapDrawable)container.getResources().
				getDrawable(R.drawable.toolsunlock)).getBitmap();
		mirrorBitmap = ((BitmapDrawable)container.getResources().
				getDrawable(R.drawable.toolsmirror)).getBitmap();
		bringToFrontBitmap = ((BitmapDrawable)container.getResources().
				getDrawable(R.drawable.toolsbringtofront)).getBitmap();
		stampBitmap = ((BitmapDrawable)container.getResources().
				getDrawable(R.drawable.toolsstamp)).getBitmap();
		trashBitmap = ((BitmapDrawable)container.getResources().
				getDrawable(R.drawable.toolstrash)).getBitmap();
		this.stickerBitmapList = stickerBitmapList;
		startLeftTopX = 0;
		startLeftTopY = 0;
		bitmapWidth = lockBitmap.getWidth();
		bitmapHeight = lockBitmap.getHeight();
	}
	
	public void drawTools(Canvas canvas, boolean isLock) {
		if(isLock) {
			canvas.drawBitmap(lockBitmap, startLeftTopX, startLeftTopY, null);
		}
		else {
			canvas.drawBitmap(unlockBitmap, startLeftTopX, startLeftTopY, null);
		}
		canvas.drawBitmap(bringToFrontBitmap, startLeftTopX+bitmapWidth * 1, startLeftTopY, null);
		canvas.drawBitmap(mirrorBitmap, startLeftTopX + bitmapWidth * 2, startLeftTopY, null);
		canvas.drawBitmap(stampBitmap, startLeftTopX + bitmapWidth * 3, startLeftTopY, null);
		canvas.drawBitmap(trashBitmap, startLeftTopX + bitmapWidth * 4, startLeftTopY, null);
	}
	
	public boolean setOnTouchEvent(float touchPointX, float touchPointY) {
		if(touchPointY >= startLeftTopY && touchPointY < startLeftTopY + bitmapHeight) {
			if(touchPointX >= startLeftTopX && touchPointX < startLeftTopX + bitmapWidth) {
				stickerBitmapList.setOnTouchStickerBitmapLock();
				return true;
			}
			//����ͼ�ᵽ������
			else if(touchPointX >= startLeftTopX + bitmapWidth &&
					touchPointX < startLeftTopX + bitmapWidth * 2) {
				stickerBitmapList.bringOnTouchStickerBitmapToFront();
				return true;
			}
			//ˮƽ��ת
			else if(touchPointX >= startLeftTopX + bitmapWidth * 2 &&
					touchPointX < startLeftTopX + bitmapWidth * 3) {
				stickerBitmapList.mirrorStickerBitmap();
				return true;
			}
			//����İ�ťΪ������ͼ���ڻ����ϡ�
			else if(touchPointX >= startLeftTopX + bitmapWidth * 3 &&
					touchPointX < startLeftTopX + bitmapWidth * 4) {
				stickerBitmapList.drawOnTouchStickerBitmapInCanvas();
				return true;
			}
			//�����ɾ����ť
			else if(touchPointX >= startLeftTopX + bitmapWidth * 4 &&
					touchPointX < startLeftTopX + bitmapWidth * 5) {
				stickerBitmapList.deleteOnTouchStickerBitmap();
				return true;
			}
		}
		return false;
	}
	
	public void setStartLeftTop(PointF leftBottomPoint) {
		startLeftTopX = leftBottomPoint.x;
		startLeftTopY = leftBottomPoint.y - bitmapHeight;	
		if(startLeftTopX < 0) startLeftTopX = 0;
		if(startLeftTopY < 0) startLeftTopY = 0;
		if(startLeftTopX + bitmapWidth * toolsNum  > DrawAttribute.screenWidth) 
			startLeftTopX = DrawAttribute.screenWidth - bitmapWidth * toolsNum;
		if(startLeftTopY + bitmapHeight > DrawAttribute.screenHeight) 
			startLeftTopX = DrawAttribute.screenHeight - bitmapHeight;
	}
	
}
