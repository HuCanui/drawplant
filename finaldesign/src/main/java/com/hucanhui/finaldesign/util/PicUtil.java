package com.hucanhui.finaldesign.util;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;

public class PicUtil {
	private DrawActivity drawActivity;
	private ImageView picStorageBackgroundbtn;
	private ImageView picCameraBackgroundbtn;
	private ImageView picStorageStickerbtn;
	private ImageView picCameraStickerbtn;
	private final int PIC_STORAGE_BG = 1;
	private final int PIC_STORAGE_STICKER = 2;
	private final int PIC_CAMERA_BG = 3;
	private final int PIC_CAMERA_STICKER = 4;
	
	public PicUtil(DrawActivity drawActivity) {
		this.drawActivity = drawActivity;
	}
	
	public void picPicSetOnClickListener() {
		picStorageBackgroundbtn = (ImageView)drawActivity.findViewById(R.id.picstorage_background);
		picCameraBackgroundbtn = (ImageView)drawActivity.findViewById(R.id.piccamera_background);
		picStorageStickerbtn = (ImageView)drawActivity.findViewById(R.id.picstorage_sticker);
		picCameraStickerbtn = (ImageView)drawActivity.findViewById(R.id.piccamera_sticker);
		PicListener picStorageListener = new PicListener();
		picStorageBackgroundbtn.setOnTouchListener(picStorageListener);
		picStorageStickerbtn.setOnTouchListener(picStorageListener);
		picCameraBackgroundbtn.setOnTouchListener(picStorageListener);
		picCameraStickerbtn.setOnTouchListener(picStorageListener);
	}
	
	class PicListener implements View.OnTouchListener {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:
				v.setBackgroundColor(0x00ffffff);
				int clickType = PIC_STORAGE_BG;
				switch(v.getId()) {
				case R.id.picstorage_background:break;
			    case R.id.picstorage_sticker:clickType = PIC_STORAGE_STICKER;break;
			    case R.id.piccamera_background:clickType = PIC_CAMERA_BG;break;
			    case R.id.piccamera_sticker:clickType = PIC_CAMERA_STICKER;
			    }
				Intent intent;
				if(clickType == PIC_STORAGE_BG || clickType == PIC_STORAGE_STICKER) {
					intent = new Intent(Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);					
				}
				else {
					intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);				
				}
				drawActivity.startActivityForResult(intent, clickType);
				break;
				case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);				
			}
			return true;
		}				
	}
}
