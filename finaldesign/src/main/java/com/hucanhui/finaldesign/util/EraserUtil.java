package com.hucanhui.finaldesign.util;
//javaapk.com�ṩ����
import android.R.drawable;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.MainActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.view.DrawView;

public class EraserUtil {	
	private DrawView drawView;
	private DrawActivity activity;
	public EraserUtil(DrawActivity activity,DrawView drawView) {
		this.drawView = drawView;
		this.activity = activity;
	}
	public void eraserPicSetOnClickListener() {
		ImageView[] erasers = new ImageView[4];
		erasers[0] = (ImageView)activity.findViewById(R.id.eraser01);
		erasers[1] = (ImageView)activity.findViewById(R.id.eraser02);
		erasers[2] = (ImageView)activity.findViewById(R.id.eraser03);
		erasers[3] = (ImageView)activity.findViewById(R.id.eraser04);
		EraserListener eraserListener = new EraserListener(erasers);
		for(int i = 0;i < erasers.length;i++) {
			erasers[i].setOnClickListener(eraserListener);
		}		
	}
	
	class EraserListener implements View.OnClickListener {
		private ImageView[] erasers;
		public EraserListener(ImageView[] erasers) {
			this.erasers = erasers;
		}
		@Override
		public void onClick(View v) {	
			int index = 0;
			switch(v.getId()) {
				case R.id.eraser01:break;
				case R.id.eraser02:index = 1;break;
				case R.id.eraser03:index = 2;break;
				case R.id.eraser04:index = 3;break;
			}
			if(index == 3) {
				AlertDialog.Builder builder = new AlertDialog.Builder(activity)
		   		 .setIcon(drawable.ic_dialog_alert).setMessage("ȷ�����?")
		   		 .setPositiveButton("OK", new DialogInterface.OnClickListener() { 					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							drawView.cleanPaintBitmap()	;
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
			else {
				ImageView imageView = (ImageView)activity.findViewById(activity.brushDrawableId);
				imageView.scrollTo(0, -30);
				erasers[index].scrollTo(0, 0);
				activity.brushDrawableId = v.getId();	
				drawView.setBrushBitmap(DrawAttribute.DrawStatus.ERASER,index);
			}
		}	
	}
}
