package com.hucanhui.finaldesign.util;

import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.view.DrawView;

public class StamppenUtil {	
	private DrawView drawView;
	private DrawActivity activity;
	public StamppenUtil(DrawActivity activity,DrawView drawView) {
		this.drawView = drawView;
		this.activity = activity;
	}
	public void stampPenPicSetOnClickListener() {
		ImageView[] stampPens = new ImageView[12];
		stampPens[0] = (ImageView)activity.findViewById(R.id.stamppen01);
		stampPens[1] = (ImageView)activity.findViewById(R.id.stamppen02);
		stampPens[2] = (ImageView)activity.findViewById(R.id.stamppen03);
		stampPens[3] = (ImageView)activity.findViewById(R.id.stamppen04);
		stampPens[4] = (ImageView)activity.findViewById(R.id.stamppen05);
		stampPens[5] = (ImageView)activity.findViewById(R.id.stamppen06);
		stampPens[6] = (ImageView)activity.findViewById(R.id.stamppen07);
		stampPens[7] = (ImageView)activity.findViewById(R.id.stamppen08);
		stampPens[8] = (ImageView)activity.findViewById(R.id.stamppen09);
		stampPens[9] = (ImageView)activity.findViewById(R.id.stamppen10);
		stampPens[10] = (ImageView)activity.findViewById(R.id.stamppen11);
		stampPens[11] = (ImageView)activity.findViewById(R.id.stamppen12);
		StampPenListener stampPenListener = new StampPenListener(stampPens);
		for(int i = 0;i < stampPens.length;i++) {
			stampPens[i].setOnClickListener(stampPenListener);
		}		
	}
	
	class StampPenListener implements View.OnClickListener {
		private ImageView[] stampPen;
		public StampPenListener(ImageView[] stampPen) {
			this.stampPen = stampPen;
		}
		
		@Override
		public void onClick(View v) {	
			int index = 0;int color = 0xfff195d1;
			switch(v.getId()) {
				case R.id.stamppen01:index = 0;color = 0xfff195d1;break;
				case R.id.stamppen02:index = 1;color = 0xffca161a;break;
				case R.id.stamppen03:index = 2;color = 0xff5021a2;break;
				case R.id.stamppen04:index = 3;color = 0xff4b8a4c;break;
				case R.id.stamppen05:index = 4;color = 0xff2c26d3;break;
				case R.id.stamppen06:index = 5;color = 0xfffcf600;break;
				case R.id.stamppen07:index = 6;color = 0xffc71317;break;
				case R.id.stamppen08:index = 7;color = 0xff806bb3;break;
				case R.id.stamppen09:index = 8;color = 0xff106af1;break;
				case R.id.stamppen10:index = 9;color = 0xfffdf600;break;
				case R.id.stamppen11:index = 10;color = 0xfff19902;break;
				case R.id.stamppen12:index = 11;color = 0xff85c315;break;
			}
			ImageView imageView = (ImageView)activity.findViewById(activity.brushDrawableId);
			imageView.scrollTo(0, -30);
			stampPen[index].scrollTo(0, 0);
			activity.brushDrawableId = v.getId();
			int type = index/3;
			if(type == 0)
				drawView.setStampBitmaps(DrawAttribute.DrawStatus.STAMP_HEART,color);
			else if(type == 1)
				drawView.setStampBitmaps(DrawAttribute.DrawStatus.STAMP_STAR,color);
			else if(type == 2)
				drawView.setStampBitmaps(DrawAttribute.DrawStatus.STAMP_BUBBLE,color);
			else
				drawView.setStampBitmaps(DrawAttribute.DrawStatus.STAMP_DOTS,color);
		}	
	}
}
