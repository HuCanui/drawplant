package com.hucanhui.finaldesign.view;

import com.hucanhui.finaldesign.AboutActivity;
import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.MainActivity;
import com.hucanhui.finaldesign.PreviewActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.WebActivity;
import com.hucanhui.finaldesign.util.DrawAttribute;

import android.R.drawable;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;

public class MainView extends View implements Runnable {
	private Bitmap mBackground = null;
	private Bitmap leftTop = null;
	private Bitmap rightTop = null;
	private Bitmap leftBottom = null;
	private Bitmap rightBottom = null;
	
	private Bitmap leftTopPic = null;
	private Bitmap rightTopPic = null;
	private Bitmap leftBottomPic = null;
	private Bitmap rightBottomPic = null;
	private Bitmap leftTopcPic = null;
	private Bitmap rightTopcPic = null;
	private Bitmap leftBottomcPic = null;
	private Bitmap rightBottomcPic = null;
	private final int DRAWUNIT = DrawAttribute.screenWidth / 9;
	private Point leftTopP,rightTopP,leftBottomP,rightBottomP;
	private Context context;
	
	private Paint textPaint;
	private int textHeight;
	private int textWidth;
	private String text;
	private Bitmap weiboBitmap = null;
	
	
	public MainView(Context context) {	
		super(context);
		this.context = context;
		leftTopP = new Point(DRAWUNIT, DrawAttribute.screenHeight/2 - 7 * DRAWUNIT / 2);
		rightTopP = new Point(5 * DRAWUNIT, DrawAttribute.screenHeight/2 - 7 * DRAWUNIT / 2);
		leftBottomP = new Point(DRAWUNIT, DrawAttribute.screenHeight/2 +  DRAWUNIT / 2);
		rightBottomP = new Point(5 * DRAWUNIT, DrawAttribute.screenHeight/2 +  DRAWUNIT / 2);		
		mBackground = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainbackground)).getBitmap();
		mBackground = Bitmap.createScaledBitmap(mBackground, DrawAttribute.screenWidth, 
				 DrawAttribute.screenWidth* mBackground.getHeight()/mBackground.getWidth(), false);
		
		leftTopPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainlefttop)).getBitmap();
		leftTopPic = Bitmap.createScaledBitmap(leftTopPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		leftTopcPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainlefttopc)).getBitmap();
		leftTopcPic = Bitmap.createScaledBitmap(leftTopcPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		
		rightTopPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainrighttop)).getBitmap();
		rightTopPic = Bitmap.createScaledBitmap(rightTopPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		rightTopcPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainrighttopc)).getBitmap();
		rightTopcPic = Bitmap.createScaledBitmap(rightTopcPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		
		leftBottomPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainleftbottom)).getBitmap();
		leftBottomPic = Bitmap.createScaledBitmap(leftBottomPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		leftBottomcPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainleftbottomc)).getBitmap();
		leftBottomcPic = Bitmap.createScaledBitmap(leftBottomcPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		
		rightBottomPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainrightbottom)).getBitmap();
		rightBottomPic  = Bitmap.createScaledBitmap(rightBottomPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		rightBottomcPic = ((BitmapDrawable)getResources().getDrawable(R.drawable.mainrightbottomc)).getBitmap();
		rightBottomcPic = Bitmap.createScaledBitmap(rightBottomcPic, 3*DRAWUNIT ,3 * DRAWUNIT, false);
		
		leftTop = leftTopPic;
		rightTop = rightTopPic;
		leftBottom = leftBottomPic;
		rightBottom = rightBottomPic;
		
		textPaint = new Paint();
		text = " ������΢��";
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(25);
		FontMetrics fm = textPaint.getFontMetrics();		
		textHeight = (int)Math.ceil(fm.descent - fm.top) + 2;
		float[] widths = new float[text.length()];
		textPaint.getTextWidths(text, widths);
		for(int i = 0; i < text.length(); i++){
			textWidth += (int) (Math.ceil(widths[i]));			
		}
		weiboBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.weibo_icon)).getBitmap();
		weiboBitmap = Bitmap.createScaledBitmap(weiboBitmap, textHeight, textHeight, false);
		
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(mBackground,0,0,null);
		canvas.drawBitmap(leftTop,leftTopP.x,leftTopP.y,null);
		canvas.drawBitmap(leftBottom,leftBottomP.x,leftBottomP.y,null);
		canvas.drawBitmap(rightBottom,rightBottomP.x,rightBottomP.y,null);
		canvas.drawBitmap(rightTop,rightTopP.x,rightTopP.y,null);
		canvas.drawText(text, DrawAttribute.screenWidth - textWidth -10,
				DrawAttribute.screenHeight - textHeight - 22, textPaint);
		canvas.drawBitmap(weiboBitmap,DrawAttribute.screenWidth - textWidth - textHeight - 10,
				DrawAttribute.screenHeight - textHeight * 2 - 13, null);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:pressDown((int)event.getX(),(int)event.getY());break;
		case MotionEvent.ACTION_UP:pressUp((int)event.getX(),(int)event.getY());
		}
		return true;
	}
	
	public void pressDown(int x,int y) {
		if(x > leftTopP.x && y > leftTopP.y && x < (leftTopP.x + 3 * DRAWUNIT) &&		   
				y < (leftTopP.y + 3 * DRAWUNIT)) {
			leftTop = leftTopcPic;
		}
		else if(x > rightTopP.x && y > rightTopP.y && x < (rightTopP.x + 3 * DRAWUNIT) &&								   
				y < (rightTopP.y + 3 * DRAWUNIT)) {			
			rightTop = rightTopcPic;
		}
		else if(x > leftBottomP.x && y > leftBottomP.y && x < (leftBottomP.x + 3 * DRAWUNIT) &&				  
				y < (leftBottomP.y + 3 * DRAWUNIT)) {	
			leftBottom = leftBottomcPic;	
		}
		else if(x > rightBottomP.x && y > rightBottomP.y && x < (rightBottomP.x + 3 * DRAWUNIT) &&				   
				y < (rightBottomP.y + 3 * DRAWUNIT)) {			
			rightBottom = rightBottomcPic;
		}
		else if(x > DrawAttribute.screenWidth - textWidth - textHeight - 10
				&& y > DrawAttribute.screenHeight - textHeight * 2 - 13) {			
			textPaint.setColor(Color.LTGRAY);
		}
	}
	
	public void pressUp(int x,int y) {
		if(x > leftTopP.x && y > leftTopP.y && x < (leftTopP.x + 3 * DRAWUNIT) &&
		   y < (leftTopP.y + 3 * DRAWUNIT)) {
			leftTop = leftTopPic;
			Context context = getContext();
			Intent i = new Intent(context, DrawActivity.class);
			context.startActivity(i);
			((MainActivity) context).finish();
		}
		else if(x > rightTopP.x && y > rightTopP.y && x < (rightTopP.x + 3 * DRAWUNIT) &&			   
				y < (rightTopP.y + 3 * DRAWUNIT)) {
			rightTop = rightTopPic;
			Intent intent = new Intent();
    		intent.setClass(context,PreviewActivity.class);
    		context.startActivity(intent);
    		((MainActivity) context).finish();
		}
		else if(x > leftBottomP.x && y > leftBottomP.y && x < (leftBottomP.x + 3 * DRAWUNIT) &&				   
				y < (leftBottomP.y + 3 * DRAWUNIT)) {
			leftBottom = leftBottomPic;
			AlertDialog.Builder builder = new AlertDialog.Builder(context)
	   		 .setIcon(drawable.ic_dialog_alert).setMessage("ȷ���˳�?")
	   		 .setPositiveButton("OK", new DialogInterface.OnClickListener() { 					
	   			 @Override					
	   			 public void onClick(DialogInterface dialog, int which) {
	   				 System.exit(0);			   			
	   			 }
	   		 }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {					
	   			 @Override
	   			 public void onClick(DialogInterface dialog, int which) {	
	   				 //dialog.cancel();
	   				 dialog.dismiss();		   				 
	   			 }				
	   		 });
			builder.create().show();
		}
		else if(x > rightBottomP.x && y > rightBottomP.y && x < (rightBottomP.x + 3 * DRAWUNIT) &&				   
				y < (rightBottomP.y + 3 * DRAWUNIT)) {
			Context context = getContext();
			Intent i = new Intent(context, AboutActivity.class);
			context.startActivity(i);
			((MainActivity) context).finish();
		}
		else if(x > DrawAttribute.screenWidth - textWidth - textHeight - 10
				&& y > DrawAttribute.screenHeight - textHeight * 2 - 13) {			
			textPaint.setColor(Color.BLACK);
			Context context = getContext();
			Intent i = new Intent(context, WebActivity.class);
			context.startActivity(i);
		}
	}
	
	public void freeBitmaps() {
		mBackground.recycle();
		leftTopPic.recycle();
		rightTopPic.recycle();
		leftBottomPic.recycle();
		rightBottomPic.recycle();
		leftTopcPic.recycle();
		rightTopcPic.recycle();
		leftBottomcPic.recycle();
		rightBottomcPic.recycle();
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
			    Thread.sleep(100);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}
	}
}
