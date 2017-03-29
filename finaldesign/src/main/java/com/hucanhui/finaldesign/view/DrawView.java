package com.hucanhui.finaldesign.view;

import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.geometry.BasicGeometry;
import com.hucanhui.finaldesign.sticker.StickerBitmap;
import com.hucanhui.finaldesign.sticker.StickerBitmapList;
import com.hucanhui.finaldesign.undoandredo.UndoAndRedo;
import com.hucanhui.finaldesign.util.DrawAttribute;
import com.hucanhui.finaldesign.util.StorageInSDCard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DrawView extends View implements Runnable 
{
	private final int VISIBLE_BTN_WIDTH = 60;
	private final int VISIBLE_BTN_HEIGHT = 40;
	
	private enum TouchLayer{GEOMETRY_LAYER, PAINT_LAYER, STICKER_BITMAP, STICKER_TOOL, VISIBLE_BTN};
	private Bitmap backgroundBitmap = null;
	private PointF backgroundBitmapLeftTopP = null;
	private Bitmap paintBitmap = null;
	private Canvas paintCanvas = null;
	private BasicGeometry basicGeometry = null;
	private StickerBitmapList stickerBitmapList = null;
	private Bitmap visibleBtnBitmap = null;
	private GestureDetector brushGestureDetector = null;
	private BrushGestureListener brushGestureListener = null;
	private DrawAttribute.DrawStatus drawStatus;
	private TouchLayer touchLayer;	
	private UndoAndRedo undoAndRedo;
	private Context context;
	
	public DrawView(Context context,AttributeSet attributeSet) {
		super(context,attributeSet);	
		this.context = context;
		backgroundBitmap = DrawAttribute.getImageFromAssetsFile(context,"bigpaper00.jpg",true);
		backgroundBitmapLeftTopP = new PointF(0,0);
		paintBitmap = Bitmap.createBitmap(DrawAttribute.screenWidth,
				      DrawAttribute.screenHeight, Bitmap.Config.ARGB_8888);
		paintCanvas = new Canvas(paintBitmap);
		paintCanvas.drawARGB(0, 255, 255, 255);
		stickerBitmapList = new StickerBitmapList(this);
		this.drawStatus = DrawAttribute.DrawStatus.CASUAL_WATER;
		undoAndRedo = new UndoAndRedo();
		visibleBtnBitmap = ((BitmapDrawable)getResources().
				getDrawable(R.drawable.drawvisiblebtn)).getBitmap();
		brushGestureListener = new BrushGestureListener
		                        (casualStroke(R.drawable.marker,Color.BLACK),2,null);
	    brushGestureDetector = new GestureDetector(brushGestureListener);   
		new Thread(this).start();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(backgroundBitmap, backgroundBitmapLeftTopP.x,
				backgroundBitmapLeftTopP.y, null);
		canvas.drawBitmap(paintBitmap, 0,0, null);
		if(basicGeometry != null) {
			basicGeometry.drawGraphic(canvas);
		}
		stickerBitmapList.drawStickerBitmapList(canvas);
		canvas.drawBitmap(visibleBtnBitmap, DrawAttribute.screenWidth - VISIBLE_BTN_WIDTH, 0, null);
	}	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			float y = event.getY();
			//�ж��Ƿ��������ΰ�ť
			if(isClickOnVisibleBtn(x, y)) {
				touchLayer = TouchLayer.VISIBLE_BTN;
				((DrawActivity)context).setUpAndButtomBarVisible(true);
				return true;
			}
			else {
				((DrawActivity)context).setUpAndButtomBarVisible(false);
			}
			int touchType = stickerBitmapList.getOnTouchType(x, y);
			switch(touchType) {
			case 1:touchLayer = TouchLayer.STICKER_TOOL;return true;//�������ͼ�Ĺ���
			case 0:touchLayer = TouchLayer.STICKER_BITMAP;break;//�������ͼ
			case -1:
				if(basicGeometry != null && basicGeometry.isPointInsideGeometry
						(event.getX(),event.getY())) {
					touchLayer = TouchLayer.GEOMETRY_LAYER;//������Ǽ���ͼ��				
				}
				else {
					touchLayer = TouchLayer.PAINT_LAYER;//������ǻ�ͼ��
				}
			}
		}	
		//��ͼ��ļ���
		if(touchLayer == TouchLayer.PAINT_LAYER) {
			brushGestureDetector.onTouchEvent(event);
			if(event.getAction() == MotionEvent.ACTION_UP) {
				undoAndRedo.addBitmap(paintBitmap);
			}
		}
		//����ͼ�εļ���
		else if(touchLayer == TouchLayer.GEOMETRY_LAYER) {
			basicGeometry.onTouchEvent(event);
		}
		//��ͼ�ļ���
		else if(touchLayer == TouchLayer.STICKER_BITMAP) {
			stickerBitmapList.onTouchEvent(event);
		}
		return true;
	}
	
	private boolean isClickOnVisibleBtn(float x, float y) {
		if(x > DrawAttribute.screenWidth - VISIBLE_BTN_WIDTH && x < DrawAttribute.screenWidth
				&& y < VISIBLE_BTN_HEIGHT) {
			return true;
		}
		return false;
	}
	
	@Override
	public void run()
	{
		while (!Thread.currentThread().isInterrupted())
		{
			try
			{
			    Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}
	}  
	
	public void setBackgroundBitmap(Bitmap bitmap, boolean isFromSystem) {
		if(isFromSystem) {
			backgroundBitmap = bitmap;
			backgroundBitmapLeftTopP.set(0, 0);
		}
		else {
			float scaleWidth = bitmap.getWidth() * 1.0f / DrawAttribute.screenWidth;
		    float scaleHeight = bitmap.getHeight()* 1.0f / DrawAttribute.screenHeight;
		    float scale = scaleWidth > scaleHeight?scaleWidth : scaleHeight;
		    if(scale > 1.01)
		    	backgroundBitmap = Bitmap.createScaledBitmap(bitmap,
		    			(int)(bitmap.getWidth()/scale),  (int)(bitmap.getHeight()/scale), false);
		    else {
		    	backgroundBitmap = bitmap;
		    }
		    backgroundBitmapLeftTopP.x = (DrawAttribute.screenWidth - backgroundBitmap.getWidth())/2;
		    backgroundBitmapLeftTopP.y = (DrawAttribute.screenHeight - backgroundBitmap.getHeight())/2;
		    
		}
	}
	
	public void cleanPaintBitmap() {
		paintCanvas.drawColor(0xffffffff, Mode.DST_OUT);
	}
	
	public void recordPaintBitmap(Bitmap bitmap) {
		undoAndRedo.addBitmap(bitmap);
	}
	
	public void undo() {
		if(!undoAndRedo.currentIsFirst()) {
			undoAndRedo.undo(paintBitmap);
		}
	}
	public void redo() {
		if(!undoAndRedo.currentIsLast()) {
			undoAndRedo.redo(paintBitmap);
		}
	}
	
	public void setBasicGeometry(BasicGeometry geometry) {
		if(basicGeometry != null) {
			basicGeometry.drawGraphic(paintCanvas);
		}
		this.basicGeometry = geometry;
	}
	
	public void addStickerBitmap(Bitmap bitmap) {
		stickerBitmapList.setIsStickerToolsDraw(false,null);
		if(!stickerBitmapList.addStickerBitmap(new StickerBitmap(this, stickerBitmapList, bitmap))){
			Toast.makeText(context, "��ͼ̫���ˣ�", Toast.LENGTH_SHORT).show();
		}
	}
	
	public Canvas getPaintCanvas() {
		return paintCanvas;
	}
	
	private Bitmap casualStroke(int drawableId,int color) {
		Bitmap mode = ((BitmapDrawable)this.getResources().getDrawable(drawableId)).getBitmap();		
		Bitmap bitmap = mode.copy(Bitmap.Config.ARGB_8888, true);		
		Canvas canvas = new Canvas();
	    canvas.setBitmap(bitmap);	
		Paint paintUnder = new Paint();
		paintUnder.setColor(color);    
	    canvas.drawPaint(paintUnder);	    
	    Paint paint = new Paint();
	    paint.setFilterBitmap(true);
	    paint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
	    canvas.drawBitmap(mode, 0, 0, paint);
		return bitmap;
    }
	
	public void setBrushBitmap(DrawAttribute.DrawStatus drawStatus,int extraData) {
		this.drawStatus = drawStatus;
		Bitmap brushBitmap;
		int brushDistance;
		Paint brushPaint;
		if(drawStatus == DrawAttribute.DrawStatus.CASUAL_WATER) {
			brushBitmap = casualStroke(R.drawable.marker,extraData);
			brushDistance = 1;
			brushPaint = null;
		}
		else if(drawStatus == DrawAttribute.DrawStatus.CASUAL_CRAYON) {
			brushBitmap = casualStroke(R.drawable.crayon,extraData);
			brushDistance = brushBitmap.getWidth()/2;
			brushPaint = null;
		}
		else if(drawStatus == DrawAttribute.DrawStatus.CASUAL_COLOR_SMALL) {
			brushBitmap = casualStroke(R.drawable.paintcopy,extraData);	
			brushDistance = 3;
			brushPaint = null;
		}
		else if(drawStatus == DrawAttribute.DrawStatus.CASUAL_COLOR_BIG) {
			brushBitmap = casualStroke(R.drawable.paint,extraData);	
			brushDistance = 2;
			brushPaint = null;
		}
		else //if(drawStatus == DrawAttribute.DrawStatus.ERASER) 
		{
			brushPaint = new Paint();
			brushPaint.setFilterBitmap(true);
			brushPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
			switch(extraData) {
			case 0:brushBitmap = ((BitmapDrawable)this.getResources().
					              getDrawable(R.drawable.eraser)).getBitmap();break;
			case 1:brushBitmap = ((BitmapDrawable)this.getResources().
		              getDrawable(R.drawable.eraser1)).getBitmap();break;
			default:brushBitmap = ((BitmapDrawable)this.getResources().
		              getDrawable(R.drawable.eraser2)).getBitmap();break;
			}
			brushDistance = brushBitmap.getWidth()/4;
		}
		brushGestureListener.setBrush(brushBitmap, brushDistance, brushPaint);	
	}
	
	public void setStampBitmaps(DrawAttribute.DrawStatus drawStatus, int color) {
    	Bitmap[] brushBitmaps = new Bitmap[4];
    	switch(drawStatus) {
    	case STAMP_HEART:
    		brushBitmaps[0] = casualStroke(R.drawable.stamp0heart,color);
    		brushBitmaps[1] = casualStroke(R.drawable.stamp1heart,color);
    		brushBitmaps[2] = casualStroke(R.drawable.stamp2heart,color);
    		brushBitmaps[3] = casualStroke(R.drawable.stamp3heart,color);	
    		break;
    	case STAMP_STAR:
    		brushBitmaps[0] = casualStroke(R.drawable.stamp0star,color);
    		brushBitmaps[1] = casualStroke(R.drawable.stamp1star,color);
    		brushBitmaps[2] = casualStroke(R.drawable.stamp2star,color);
    		brushBitmaps[3] = casualStroke(R.drawable.stamp3star,color);
    		break;
    	case STAMP_BUBBLE:
    		brushBitmaps[0] = casualStroke(R.drawable.stamp0bubble,color);
    		brushBitmaps[1] = casualStroke(R.drawable.stamp1bubble,color);
    		brushBitmaps[2] = casualStroke(R.drawable.stamp2bubble,color);
    		brushBitmaps[3] = casualStroke(R.drawable.stamp3bubble,color);
    		break;
    	case STAMP_DOTS:
    		brushBitmaps[0] = casualStroke(R.drawable.stamp0dots,color);
    		brushBitmaps[1] = casualStroke(R.drawable.stamp1dots,color);
    		brushBitmaps[2] = casualStroke(R.drawable.stamp2dots,color);
    		brushBitmaps[3] = casualStroke(R.drawable.stamp3dots,color);
    		break;
    	}
    	brushGestureListener.setStampBrush(brushBitmaps);
    	this.drawStatus = drawStatus;
	}	    
	
	class BrushGestureListener extends GestureDetector.SimpleOnGestureListener {
		
		private Bitmap brushBitmap = null;
		private int brushDistance;
		private int halfBrushBitmapWidth;
		private Paint brushPaint = null;		
		//ӡ����bitmap
		private Bitmap[] stampBrushBitmaps = null;
		private boolean isStamp;


		public BrushGestureListener(Bitmap brushBitmap, int brushDistance, Paint brushPaint) {
			super();
			setBrush(brushBitmap,brushDistance,brushPaint);
			isStamp = false;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			switch(drawStatus) {
			case CASUAL_WATER:
			case CASUAL_CRAYON:
			case CASUAL_COLOR_SMALL:
			case CASUAL_COLOR_BIG:
			case ERASER:isStamp = false;break;
			case STAMP_BUBBLE:
			case STAMP_DOTS:
			case STAMP_HEART:
			case STAMP_STAR:isStamp = true;break;
			}
			if(!isStamp) {
				paintCanvas.drawBitmap(brushBitmap,e.getX() - halfBrushBitmapWidth, 
					               e.getY() - halfBrushBitmapWidth, brushPaint);
			}
			else {
				paintSingleStamp(e.getX(), e.getY());
			}
			return true;  
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {	
			if(!isStamp) {
				float beginX = e2.getX();
				float beginY = e2.getY();
				float distance = (float)Math.sqrt(distanceX * distanceX + distanceY * distanceY);
				float x = distanceX/distance, x_ = 0;
				float y = distanceY/distance, y_ = 0;
				while(Math.abs(x_)<= Math.abs(distanceX) && Math.abs(y_) <= Math.abs(distanceY)) {
					x_ += x * brushDistance;
					y_ += y * brushDistance;
					paintCanvas.save();
					paintCanvas.rotate((float)(Math.random()*10000),beginX + x_ ,beginY + y_ );
					paintCanvas.drawBitmap(brushBitmap, beginX + x_ - halfBrushBitmapWidth, 
							               beginY + y_ - halfBrushBitmapWidth, brushPaint);
					paintCanvas.restore();
				}	
			}
			else {
				paintSingleStamp(e2.getX(), e2.getY());	
			}
			return true;
		}
		
		public void setBrush(Bitmap brushBitmap, int brushDistance, Paint brushPaint) {
			this.brushBitmap = brushBitmap;
			this.brushDistance = brushDistance;
			halfBrushBitmapWidth = brushBitmap.getWidth()/2;
			this.brushPaint = brushPaint;
		}
		
		public void setStampBrush(Bitmap[] brushBitmaps) {
			this.stampBrushBitmaps = brushBitmaps;
			halfBrushBitmapWidth = brushBitmaps[0].getWidth()/2;
		}
		
		private void paintSingleStamp(float x,float y) {
			if(Math.random() > 0.1) {
				paintCanvas.drawBitmap(stampBrushBitmaps[0],x - halfBrushBitmapWidth,
						y - halfBrushBitmapWidth, null);
			}
			for(int i = 1; i < stampBrushBitmaps.length; i++)
				if(Math.random() > 0.3) {
					paintCanvas.drawBitmap(stampBrushBitmaps[i],x - halfBrushBitmapWidth, 
							y - halfBrushBitmapWidth, null);
				}
		}	
	}

	public void saveBitmap() {	
		Bitmap bitmap = Bitmap.createBitmap(DrawAttribute.screenWidth,
			      DrawAttribute.screenHeight, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(backgroundBitmap, backgroundBitmapLeftTopP.x,
				backgroundBitmapLeftTopP.y, null);
		canvas.drawBitmap(paintBitmap, 0,0, null);
		if(basicGeometry != null) {
			basicGeometry.drawGraphic(canvas);
		}
		stickerBitmapList.drawStickerBitmapList(canvas);
		StorageInSDCard.saveBitmapInExternalStorage(bitmap, context);
	}	
	
	public void freeBitmaps() {
		backgroundBitmap.recycle();
		paintBitmap.recycle();
		stickerBitmapList.freeBitmaps();
		undoAndRedo.freeBitmaps();
	}
}
