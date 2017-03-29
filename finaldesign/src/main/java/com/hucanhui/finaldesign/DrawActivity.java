package com.hucanhui.finaldesign;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.hucanhui.finaldesign.util.BackgroundUtil;
import com.hucanhui.finaldesign.util.CasualColorUtil;
import com.hucanhui.finaldesign.util.CasualCrayonUtil;
import com.hucanhui.finaldesign.util.CasualWaterUtil;
import com.hucanhui.finaldesign.util.DrawAttribute;
import com.hucanhui.finaldesign.util.EraserUtil;
import com.hucanhui.finaldesign.util.GeometryUtil;
import com.hucanhui.finaldesign.util.PicUtil;
import com.hucanhui.finaldesign.util.StamppenUtil;
import com.hucanhui.finaldesign.util.StickerUtil;
import com.hucanhui.finaldesign.view.DrawView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DrawActivity extends Activity {	
	private DrawView drawView;
	private LinearLayout topBar;
    private LinearLayout bottomBar;
    public int brushDrawableId;
    private HorizontalScrollView markerList;
    private HorizontalScrollView crayonList;
    private HorizontalScrollView colorList;
    private HorizontalScrollView stamppenList;
    private HorizontalScrollView backgroundList;
    private HorizontalScrollView eraserList;
    private LinearLayout stickerList;
    private LinearLayout geometryTool;
    private LinearLayout picTool; 
    private LinearLayout wordTool; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.draw);        
	    //����Ĭ��ѡ���ɫˮ��
		ImageView imageView = (ImageView)findViewById(R.id.marker01);
	    imageView.scrollTo(0, 0);
	    brushDrawableId = R.id.marker01;
	    topBar = (LinearLayout)this.findViewById(R.id.drawtop);
	    bottomBar = (LinearLayout)this.findViewById(R.id.drawbottom);
	    //�˴����ô�������ɾ��
	    topBar.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {		
	    	}
	    });
	    //�˴����ô�������ɾ��
	    bottomBar.setOnClickListener(new OnClickListener() {
	    	@Override
			public void onClick(View v) {
			}	        		    	
	    });
	    drawView = (DrawView)this.findViewById(R.id.drawview);	    
	    Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		if(bundle != null) {
			String backgroundBitmapPath = 
				bundle.getString("com.example.finaldesign.BackgroundBitmapPath");
			Bitmap bitmap = BitmapFactory.decodeFile(backgroundBitmapPath);
//			drawView.setBackgroundBitmap(bitmap, false);
		}
		markerList = (HorizontalScrollView)this.findViewById(R.id.markerlist);
		crayonList = (HorizontalScrollView)this.findViewById(R.id.crayonlist);
		colorList = (HorizontalScrollView)this.findViewById(R.id.colorlist);
		geometryTool = (LinearLayout)this.findViewById(R.id.geometrytool);
		stamppenList = (HorizontalScrollView)this.findViewById(R.id.stamppenlist);
		backgroundList = (HorizontalScrollView)this.findViewById(R.id.backgroundlist);
		stickerList = (LinearLayout)this.findViewById(R.id.stickerlist);
		eraserList = (HorizontalScrollView)this.findViewById(R.id.eraserlist);	
		picTool = (LinearLayout)this.findViewById(R.id.pictool);
		wordTool = (LinearLayout)this.findViewById(R.id.wordtool);
		markerList.setVisibility(View.VISIBLE);
		crayonList.setVisibility(View.INVISIBLE);
		colorList.setVisibility(View.INVISIBLE);
		geometryTool.setVisibility(View.INVISIBLE);
		stamppenList.setVisibility(View.INVISIBLE);
		backgroundList.setVisibility(View.INVISIBLE);
		stickerList.setVisibility(View.INVISIBLE);
		eraserList.setVisibility(View.INVISIBLE);
		picTool.setVisibility(View.INVISIBLE);
		wordTool.setVisibility(View.INVISIBLE);
		//����Ĳ˵�
	    ImageView casualMarkerButton = (ImageView)this.findViewById(R.id.drawcasualmarkerbtn);
	    ImageView casualCrayonButton = (ImageView)this.findViewById(R.id.drawcasualcrayonbtn);
	    ImageView casualColorButton = (ImageView)this.findViewById(R.id.drawcasualcolorbtn);
	    ImageView drawGraphicButton = (ImageView)this.findViewById(R.id.drawgraphicbtn);
	    ImageView stamppenButton = (ImageView)this.findViewById(R.id.stamppenbtn);
	    ImageView backgroundButton = (ImageView)this.findViewById(R.id.drawbackgroundbtn);
	    ImageView stickerButton = (ImageView)this.findViewById(R.id.stickerbtn);
	    ImageView eraserButton = (ImageView)this.findViewById(R.id.draweraserbtn);
	    ImageView picButton = (ImageView)this.findViewById(R.id.drawpicbtn);
	    ImageView wordsButton = (ImageView)this.findViewById(R.id.drawwordsbtn);
	    //����Ĳ˵�
	    ImageButton drawbackButton = (ImageButton)this.findViewById(R.id.drawbackbtn);
	    ImageButton saveButton = (ImageButton)this.findViewById(R.id.drawsavebtn);
	    ImageButton visibleButton = (ImageButton)this.findViewById(R.id.drawvisiblebtn);
	    ImageView undoButton = (ImageView)this.findViewById(R.id.drawundobtn);
	    ImageView redoButton = (ImageView)this.findViewById(R.id.drawredobtn);
	    //ѡ��ˮ�ʱʰ�ť
	    casualMarkerButton.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.VISIBLE);					
		    		crayonList.setVisibility(View.INVISIBLE);				
		    		colorList.setVisibility(View.INVISIBLE);					
		    		geometryTool.setVisibility(View.INVISIBLE);				
		    		stamppenList.setVisibility(View.INVISIBLE);	    		
		    		backgroundList.setVisibility(View.INVISIBLE);					
		    		stickerList.setVisibility(View.INVISIBLE);					
		    		eraserList.setVisibility(View.INVISIBLE);					
		    		picTool.setVisibility(View.INVISIBLE);
		    		wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        	
	    });	        
	    //ѡ�����ʰ�ť        
	    casualCrayonButton.setOnTouchListener(new OnTouchListener(){				

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);				
		    		crayonList.setVisibility(View.VISIBLE);				
		    		colorList.setVisibility(View.INVISIBLE);					
		    		geometryTool.setVisibility(View.INVISIBLE);				
		    		stamppenList.setVisibility(View.INVISIBLE);					
		    		backgroundList.setVisibility(View.INVISIBLE);				
		    		stickerList.setVisibility(View.INVISIBLE);					
		    		eraserList.setVisibility(View.INVISIBLE);				
		    		picTool.setVisibility(View.INVISIBLE);
		    		wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        
	    });	        
	    //ѡ�����ϱʰ�ť	        
	    casualColorButton.setOnTouchListener(new OnTouchListener(){				

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);					
		    		crayonList.setVisibility(View.INVISIBLE);
		    		colorList.setVisibility(View.VISIBLE);
		    		geometryTool.setVisibility(View.INVISIBLE);
		    		stamppenList.setVisibility(View.INVISIBLE);					
		    		backgroundList.setVisibility(View.INVISIBLE);
		    		stickerList.setVisibility(View.INVISIBLE);
		    		eraserList.setVisibility(View.INVISIBLE);	    		
		    		picTool.setVisibility(View.INVISIBLE);
		    		wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        	
	    });
	    //ѡ����ͼ�ΰ�ť
	    drawGraphicButton.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);
		    		crayonList.setVisibility(View.INVISIBLE);
		    		colorList.setVisibility(View.INVISIBLE);
		    		geometryTool.setVisibility(View.VISIBLE);
		    		stamppenList.setVisibility(View.INVISIBLE);
		    		backgroundList.setVisibility(View.INVISIBLE);
		    		stickerList.setVisibility(View.INVISIBLE);
		    		eraserList.setVisibility(View.INVISIBLE);
		    		picTool.setVisibility(View.INVISIBLE);
		    		wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				//flawOperation();
				return true;
			}  		    	
	    });
	    //ѡ��ӡ����ť        
	    stamppenButton.setOnTouchListener(new OnTouchListener(){				

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);
		    		crayonList.setVisibility(View.INVISIBLE);
		    		colorList.setVisibility(View.INVISIBLE);
		    		geometryTool.setVisibility(View.INVISIBLE);
		    		stamppenList.setVisibility(View.VISIBLE);
		    		backgroundList.setVisibility(View.INVISIBLE);
		    		stickerList.setVisibility(View.INVISIBLE);
		    		eraserList.setVisibility(View.INVISIBLE);
		    		picTool.setVisibility(View.INVISIBLE);
		    		wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        		    	
	    });
	    //ѡ���ֽ��ť
	    backgroundButton.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);
					crayonList.setVisibility(View.INVISIBLE);
					colorList.setVisibility(View.INVISIBLE);
					geometryTool.setVisibility(View.INVISIBLE);
					stamppenList.setVisibility(View.INVISIBLE);
					backgroundList.setVisibility(View.VISIBLE);
					stickerList.setVisibility(View.INVISIBLE);
					eraserList.setVisibility(View.INVISIBLE);
					picTool.setVisibility(View.INVISIBLE);	
					wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        		        
	    });   
	    //ѡ���زİ�ť
	    stickerButton.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);
					crayonList.setVisibility(View.INVISIBLE);
					colorList.setVisibility(View.INVISIBLE);
					geometryTool.setVisibility(View.INVISIBLE);
					stamppenList.setVisibility(View.INVISIBLE);
					backgroundList.setVisibility(View.INVISIBLE);
					stickerList.setVisibility(View.VISIBLE);
					eraserList.setVisibility(View.INVISIBLE);
					picTool.setVisibility(View.INVISIBLE);	
					wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        		       
	    });	        
	    //ѡ����Ƥ��ť
	    eraserButton.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);
					crayonList.setVisibility(View.INVISIBLE);
					colorList.setVisibility(View.INVISIBLE);
					geometryTool.setVisibility(View.INVISIBLE);
					stamppenList.setVisibility(View.INVISIBLE);
					backgroundList.setVisibility(View.INVISIBLE);
					stickerList.setVisibility(View.INVISIBLE);
					eraserList.setVisibility(View.VISIBLE);
					picTool.setVisibility(View.INVISIBLE);
					wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        	
	    });	        
	    //ѡ��ͼƬ��ť
	    picButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);
					crayonList.setVisibility(View.INVISIBLE);
					colorList.setVisibility(View.INVISIBLE);
					geometryTool.setVisibility(View.INVISIBLE);
					stamppenList.setVisibility(View.INVISIBLE);
					backgroundList.setVisibility(View.INVISIBLE);
					stickerList.setVisibility(View.INVISIBLE);
					eraserList.setVisibility(View.INVISIBLE);
					picTool.setVisibility(View.VISIBLE);	
					wordTool.setVisibility(View.INVISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        		      
	    });
	    //������ְ�ť
	    wordsButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(0x00ffffff);
					markerList.setVisibility(View.INVISIBLE);
					crayonList.setVisibility(View.INVISIBLE);
					colorList.setVisibility(View.INVISIBLE);
					geometryTool.setVisibility(View.INVISIBLE);
					stamppenList.setVisibility(View.INVISIBLE);
					backgroundList.setVisibility(View.INVISIBLE);
					stickerList.setVisibility(View.INVISIBLE);
					eraserList.setVisibility(View.INVISIBLE);
					picTool.setVisibility(View.INVISIBLE);
					wordTool.setVisibility(View.VISIBLE);
					break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
				}
				flawOperation();
				return true;
			}	        		      
	    });
	    //����ˮ�ʱʵ������
	    CasualWaterUtil casualWaterUtil = new CasualWaterUtil(this, drawView);
	    casualWaterUtil.casualWaterPicSetOnClickListener();
	    //�������ʵ������
	    CasualCrayonUtil casualCrayonUtil = new CasualCrayonUtil(this, drawView);
	    casualCrayonUtil.casualCrayonPicSetOnClickListener();
	    //�������ϱʵ������
	    CasualColorUtil casualColorUtil = new CasualColorUtil(this, drawView);
	    casualColorUtil.casualColorPicSetOnClickListener();
	    //����ͼ�ε������
	    GeometryUtil graphicUtil = new GeometryUtil(this, drawView);
	    graphicUtil.graphicPicSetOnClickListener();
	    //����ӡ������ļ���
	    StamppenUtil stamppenUtil = new StamppenUtil(this, drawView);
	    stamppenUtil.stampPenPicSetOnClickListener();
	    //���ñ���ͼƬ����ļ���
	    BackgroundUtil backgroundUtil = new BackgroundUtil(this, drawView);
	    backgroundUtil.backgroundPicSetOnClickListener();
	    //������Ƥ����ļ���
	    EraserUtil eraserUtil = new EraserUtil(this, drawView);
	    eraserUtil.eraserPicSetOnClickListener();
	    //�����زĵ���ļ���
	    StickerUtil stickerUtil = new StickerUtil(this, drawView);
	    stickerUtil.stickerPicSetOnClickListener();
	    //����ͼƬ����ļ���
	    PicUtil picUtil = new PicUtil(this);
	    picUtil.picPicSetOnClickListener();	       
	    //������ذ�ť
        drawbackButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		Intent intent = new Intent();
	        	intent.setClass(DrawActivity.this,MainActivity.class);
	        	startActivity(intent);
	        	DrawActivity.this.finish();	        		
        	}	        
        }); 
        //������水ť
        saveButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		drawView.saveBitmap();
        		Intent intent = new Intent();
        		intent.setClass(DrawActivity.this,PreviewActivity.class);
        		startActivity(intent);
        		DrawActivity.this.finish();
        	}
        });
        //���undo��ť
        undoButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		drawView.undo();
        	}
        });  
        //���undo��ť
        redoButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		drawView.redo();
        	}
        });  
        //������ΰ�ť
        visibleButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v)
        	{
        		setUpAndButtomBarVisible(false);
        	}
        });        
	}
	
	public void setUpAndButtomBarVisible(boolean isVisible) {
		if(isVisible) {
			topBar.setVisibility(View.VISIBLE);
    		bottomBar.setVisibility(View.VISIBLE);
		}
		else {
			topBar.setVisibility(View.INVISIBLE);
    		bottomBar.setVisibility(View.INVISIBLE);
		}
	}
		
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindDrawables(findViewById(R.id.drawroot));
		drawView.freeBitmaps();
		System.gc();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//PIC_STORAGE_BG=1 PIC_STORAGE_STICKER=2 PIC_CAMERA_BG=3 PIC_CAMERA_STICKER=4
		if ((requestCode >= 1 && requestCode <= 4) && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			Bitmap bitmap = null;
			AssetFileDescriptor fileDescriptor = null; 
			BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;  
	        try {
	        	fileDescriptor = this.getContentResolver().openAssetFileDescriptor(selectedImage,"r"); 	
	        }
	        catch (FileNotFoundException e) { 
	    	} 
	        bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options); 
	        //��ȡ���ͼƬ�Ŀ�͸�  
	        float scaleWidth = options.outWidth * 1.0f / DrawAttribute.screenWidth;
		    float scaleHeight = options.outHeight * 1.0f / DrawAttribute.screenHeight;
		    float scale = scaleWidth > scaleHeight?scaleWidth : scaleHeight;
		    if(scale < 1.0f) {
		    	scale = 1.0f;
		    }
		    options.inJustDecodeBounds = false;
		    options.inSampleSize = (int)scale;
		    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
		    try {
		    	fileDescriptor.close(); 
		    }
		    catch(IOException e) {
		    }	
		    if(requestCode == 1 || requestCode == 3) {
				drawView.setBackgroundBitmap(bitmap, false);
			}
			else {
				drawView.addStickerBitmap(bitmap);
			}
		}
	}
	
	private void flawOperation() {
		drawView.setBasicGeometry(null);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
    	intent.setClass(DrawActivity.this,MainActivity.class);
    	startActivity(intent);
    	DrawActivity.this.finish();	
	}	
	
	private void unbindDrawables(View view) {
		if(view.getBackground() != null) {
			view.getBackground().setCallback(null);
		}
		if(view instanceof ViewGroup) {
			for(int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				unbindDrawables(((ViewGroup) view).getChildAt(i));
			}
			((ViewGroup) view).removeAllViews();  
		}
	}
}
