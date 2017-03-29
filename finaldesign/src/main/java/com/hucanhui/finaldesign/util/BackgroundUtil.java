package com.hucanhui.finaldesign.util;
//javaapk.com�ṩ����
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.view.DrawView;

public class BackgroundUtil {
	private DrawActivity drawActivity;
	private DrawView drawView;
	public BackgroundUtil(DrawActivity drawActivity,DrawView drawView){
		this.drawActivity = drawActivity;
		this.drawView = drawView;			
	}
	public void backgroundPicSetOnClickListener() {
		ImageView[] backgrounds = new ImageView[47];
		backgrounds[0] = (ImageView)drawActivity.findViewById(R.id.smallpaper00);
		backgrounds[1] = (ImageView)drawActivity.findViewById(R.id.smallpaper01);
		backgrounds[2] = (ImageView)drawActivity.findViewById(R.id.smallpaper02);
		backgrounds[3] = (ImageView)drawActivity.findViewById(R.id.smallpaper03);
		backgrounds[4] = (ImageView)drawActivity.findViewById(R.id.smallpaper04);
		backgrounds[5] = (ImageView)drawActivity.findViewById(R.id.smallpaper05);
		backgrounds[6] = (ImageView)drawActivity.findViewById(R.id.smallpaper06);
		backgrounds[7] = (ImageView)drawActivity.findViewById(R.id.smallpaper07);
		backgrounds[8] = (ImageView)drawActivity.findViewById(R.id.smallpaper08);
		backgrounds[9] = (ImageView)drawActivity.findViewById(R.id.smallpaper09);
		backgrounds[10] = (ImageView)drawActivity.findViewById(R.id.smallpaper10);
		backgrounds[11] = (ImageView)drawActivity.findViewById(R.id.smallpaper11);
		backgrounds[12] = (ImageView)drawActivity.findViewById(R.id.smallpaper12);
		backgrounds[13] = (ImageView)drawActivity.findViewById(R.id.smallpaper13);
		backgrounds[14] = (ImageView)drawActivity.findViewById(R.id.smallpaper14);
		backgrounds[15] = (ImageView)drawActivity.findViewById(R.id.smallpaper15);
		backgrounds[16] = (ImageView)drawActivity.findViewById(R.id.smallpaper16);
		backgrounds[17] = (ImageView)drawActivity.findViewById(R.id.smallpaper17);
		backgrounds[18] = (ImageView)drawActivity.findViewById(R.id.smallpaper18);
		backgrounds[19] = (ImageView)drawActivity.findViewById(R.id.smallpaper19);
		backgrounds[20] = (ImageView)drawActivity.findViewById(R.id.smallpaper20);
		backgrounds[21] = (ImageView)drawActivity.findViewById(R.id.smallpaper21);
		backgrounds[22] = (ImageView)drawActivity.findViewById(R.id.smallpaper22);
		backgrounds[23] = (ImageView)drawActivity.findViewById(R.id.smallpaper23);
		backgrounds[24] = (ImageView)drawActivity.findViewById(R.id.smallpaper24);
		backgrounds[25] = (ImageView)drawActivity.findViewById(R.id.smallpaper25);
		backgrounds[26] = (ImageView)drawActivity.findViewById(R.id.smallpaper26);
		backgrounds[27] = (ImageView)drawActivity.findViewById(R.id.smallpaper27);
		backgrounds[28] = (ImageView)drawActivity.findViewById(R.id.smallpaper28);
		backgrounds[29] = (ImageView)drawActivity.findViewById(R.id.smallpaper29);
		backgrounds[30] = (ImageView)drawActivity.findViewById(R.id.smallpaper30);
		backgrounds[31] = (ImageView)drawActivity.findViewById(R.id.smallpaper31);
		backgrounds[32] = (ImageView)drawActivity.findViewById(R.id.smallpaper32);
		backgrounds[33] = (ImageView)drawActivity.findViewById(R.id.smallpaper33);
		backgrounds[34] = (ImageView)drawActivity.findViewById(R.id.smallpaper34);
		backgrounds[35] = (ImageView)drawActivity.findViewById(R.id.smallpaper35);
		backgrounds[36] = (ImageView)drawActivity.findViewById(R.id.smallpaper36);
		backgrounds[37] = (ImageView)drawActivity.findViewById(R.id.smallpaper37);
		backgrounds[38] = (ImageView)drawActivity.findViewById(R.id.smallpaper38);
		backgrounds[39] = (ImageView)drawActivity.findViewById(R.id.smallpaper39);
		backgrounds[40] = (ImageView)drawActivity.findViewById(R.id.smallpaper40);
		backgrounds[41] = (ImageView)drawActivity.findViewById(R.id.smallpaper41);
		backgrounds[42] = (ImageView)drawActivity.findViewById(R.id.smallpaper42);
		backgrounds[43] = (ImageView)drawActivity.findViewById(R.id.smallpaper43);
		backgrounds[44] = (ImageView)drawActivity.findViewById(R.id.smallpaper44);
		backgrounds[45] = (ImageView)drawActivity.findViewById(R.id.smallpaper45);
		backgrounds[46] = (ImageView)drawActivity.findViewById(R.id.smallpaper46);
		BackgroundOnTouchListener backgroundOnTouchListener = new BackgroundOnTouchListener();
		for(int i = 0;i < backgrounds.length;i++) {
			backgrounds[i].setOnTouchListener(backgroundOnTouchListener);
		}		
	}
	class BackgroundOnTouchListener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
				String s = "bigpaper00.jpg";
				switch(v.getId()) {
				case R.id.smallpaper00:s = "bigpaper00.jpg";break;
				case R.id.smallpaper01:s = "bigpaper01.jpg";break;
				case R.id.smallpaper02:s = "bigpaper02.jpg";break;
				case R.id.smallpaper03:s = "bigpaper03.jpg";break;
				case R.id.smallpaper04:s = "bigpaper04.jpg";break;
				case R.id.smallpaper05:s = "bigpaper05.jpg";break;
				case R.id.smallpaper06:s = "bigpaper06.jpg";break;
				case R.id.smallpaper07:s = "bigpaper07.jpg";break;
				case R.id.smallpaper08:s = "bigpaper08.jpg";break;
				case R.id.smallpaper09:s = "bigpaper09.jpg";break;
				case R.id.smallpaper10:s = "bigpaper10.jpg";break;
				case R.id.smallpaper11:s = "bigpaper11.jpg";break;
				case R.id.smallpaper12:s = "bigpaper12.jpg";break;
				case R.id.smallpaper13:s = "bigpaper13.jpg";break;
				case R.id.smallpaper14:s = "bigpaper14.jpg";break;
				case R.id.smallpaper15:s = "bigpaper15.jpg";break;
				case R.id.smallpaper16:s = "bigpaper16.jpg";break;
				case R.id.smallpaper17:s = "bigpaper17.jpg";break;
				case R.id.smallpaper18:s = "bigpaper18.jpg";break;
				case R.id.smallpaper19:s = "bigpaper19.jpg";break;
				case R.id.smallpaper20:s = "bigpaper20.jpg";break;
				case R.id.smallpaper21:s = "bigpaper21.jpg";break;
				case R.id.smallpaper22:s = "bigpaper22.jpg";break;
				case R.id.smallpaper23:s = "bigpaper23.jpg";break;
				case R.id.smallpaper24:s = "bigpaper24.jpg";break;
				case R.id.smallpaper25:s = "bigpaper25.jpg";break;
				case R.id.smallpaper26:s = "bigpaper26.jpg";break;
				case R.id.smallpaper27:s = "bigpaper27.jpg";break;
				case R.id.smallpaper28:s = "bigpaper28.jpg";break;
				case R.id.smallpaper29:s = "bigpaper29.jpg";break;
				case R.id.smallpaper30:s = "bigpaper30.jpg";break;
				case R.id.smallpaper31:s = "bigpaper31.jpg";break;
				case R.id.smallpaper32:s = "bigpaper32.jpg";break;
				case R.id.smallpaper33:s = "bigpaper33.jpg";break;
				case R.id.smallpaper34:s = "bigpaper34.jpg";break;
				case R.id.smallpaper35:s = "bigpaper35.jpg";break;
				case R.id.smallpaper36:s = "bigpaper36.jpg";break;
				case R.id.smallpaper37:s = "bigpaper37.jpg";break;
				case R.id.smallpaper38:s = "bigpaper38.jpg";break;
				case R.id.smallpaper39:s = "bigpaper39.jpg";break;
				case R.id.smallpaper40:s = "bigpaper40.jpg";break;
				case R.id.smallpaper41:s = "bigpaper41.jpg";break;
				case R.id.smallpaper42:s = "bigpaper42.jpg";break;
				case R.id.smallpaper43:s = "bigpaper43.jpg";break;
				case R.id.smallpaper44:s = "bigpaper44.jpg";break;
				case R.id.smallpaper45:s = "bigpaper45.jpg";break;
				case R.id.smallpaper46:s = "bigpaper46.jpg";	
				}
				drawView.setBackgroundBitmap(DrawAttribute.
						getImageFromAssetsFile(drawActivity,s,true), true);	
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
}
