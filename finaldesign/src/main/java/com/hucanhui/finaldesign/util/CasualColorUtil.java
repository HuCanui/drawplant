package com.hucanhui.finaldesign.util;

import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.view.DrawView;

public class CasualColorUtil {	
	private DrawView drawView;
	private DrawActivity activity;
	public CasualColorUtil(DrawActivity activity,DrawView drawView) {
		this.drawView = drawView;
		this.activity = activity;
	}
	
	public void casualColorPicSetOnClickListener() {
		ImageView[] casualColors = new ImageView[52];
		casualColors[0] = (ImageView)activity.findViewById(R.id.paint01);
		casualColors[1] = (ImageView)activity.findViewById(R.id.paint02);
		casualColors[2] = (ImageView)activity.findViewById(R.id.paint03);
		casualColors[3] = (ImageView)activity.findViewById(R.id.paint04);
		casualColors[4] = (ImageView)activity.findViewById(R.id.paint05);
		casualColors[5] = (ImageView)activity.findViewById(R.id.paint06);
		casualColors[6] = (ImageView)activity.findViewById(R.id.paint07);
		casualColors[7] = (ImageView)activity.findViewById(R.id.paint08);
		casualColors[8] = (ImageView)activity.findViewById(R.id.paint09);
		casualColors[9] = (ImageView)activity.findViewById(R.id.paint10);
		casualColors[10] = (ImageView)activity.findViewById(R.id.paint11);
		casualColors[11] = (ImageView)activity.findViewById(R.id.paint12);
		casualColors[12] = (ImageView)activity.findViewById(R.id.paint13);
		casualColors[13] = (ImageView)activity.findViewById(R.id.paint14);
		casualColors[14] = (ImageView)activity.findViewById(R.id.paint15);
		casualColors[15] = (ImageView)activity.findViewById(R.id.paint16);
		casualColors[16] = (ImageView)activity.findViewById(R.id.paint17);
		casualColors[17] = (ImageView)activity.findViewById(R.id.paint18);
		casualColors[18] = (ImageView)activity.findViewById(R.id.paint19);
		casualColors[19] = (ImageView)activity.findViewById(R.id.paint20);
		casualColors[20] = (ImageView)activity.findViewById(R.id.paint21);
		casualColors[21] = (ImageView)activity.findViewById(R.id.paint22);
		casualColors[22] = (ImageView)activity.findViewById(R.id.paint23);
		casualColors[23] = (ImageView)activity.findViewById(R.id.paint24);
		casualColors[24] = (ImageView)activity.findViewById(R.id.paint25);
		casualColors[25] = (ImageView)activity.findViewById(R.id.paint26);
		casualColors[26] = (ImageView)activity.findViewById(R.id.paint27);
		casualColors[27] = (ImageView)activity.findViewById(R.id.paint28);
		casualColors[28] = (ImageView)activity.findViewById(R.id.paint29);
		casualColors[29] = (ImageView)activity.findViewById(R.id.paint30);
		casualColors[30] = (ImageView)activity.findViewById(R.id.paint31);
		casualColors[31] = (ImageView)activity.findViewById(R.id.paint32);
		casualColors[32] = (ImageView)activity.findViewById(R.id.paint33);
		casualColors[33] = (ImageView)activity.findViewById(R.id.paint34);
		casualColors[34] = (ImageView)activity.findViewById(R.id.paint35);
		casualColors[35] = (ImageView)activity.findViewById(R.id.paint36);
		casualColors[36] = (ImageView)activity.findViewById(R.id.paint37);
		casualColors[37] = (ImageView)activity.findViewById(R.id.paint38);
		casualColors[38] = (ImageView)activity.findViewById(R.id.paint39);
		casualColors[39] = (ImageView)activity.findViewById(R.id.paint40);
		casualColors[40] = (ImageView)activity.findViewById(R.id.paint41);
		casualColors[41] = (ImageView)activity.findViewById(R.id.paint42);
		casualColors[42] = (ImageView)activity.findViewById(R.id.paint43);
		casualColors[43] = (ImageView)activity.findViewById(R.id.paint44);
		casualColors[44] = (ImageView)activity.findViewById(R.id.paint45);
		casualColors[45] = (ImageView)activity.findViewById(R.id.paint46);
		casualColors[46] = (ImageView)activity.findViewById(R.id.paint47);
		casualColors[47] = (ImageView)activity.findViewById(R.id.paint48);
		casualColors[48] = (ImageView)activity.findViewById(R.id.paint49);
		casualColors[49] = (ImageView)activity.findViewById(R.id.paint50);
		casualColors[50] = (ImageView)activity.findViewById(R.id.paint51);
		casualColors[51] = (ImageView)activity.findViewById(R.id.paint52);
		CasualpaintListener colorListener = new CasualpaintListener(casualColors);
		for(int i = 0;i < casualColors.length;i++) {
			casualColors[i].setOnClickListener(colorListener);
		}		
	}
	
	class CasualpaintListener implements View.OnClickListener {
		private ImageView[] casualColor;
		
		public CasualpaintListener(ImageView[] casualColor) {
			this.casualColor = casualColor;
		}
		@Override
		public void onClick(View v) {	
			int index = 0;int color = 0xfff0f3f3;
			switch(v.getId()) {
				case R.id.paint01:index = 0;color = 0xfff0f3f3;break;
				case R.id.paint02:index = 1;color = 0xfff0f3f3;break;
				case R.id.paint03:index = 2;color = 0xff9fa2a2;break;
				case R.id.paint04:index = 3;color = 0xff9fa2a2;break;
				case R.id.paint05:index = 4;color = 0xff191c1b;break;
				case R.id.paint06:index = 5;color = 0xff191c1b;break;
				case R.id.paint07:index = 6;color = 0xff361e0f;break;
				case R.id.paint08:index = 7;color = 0xff361e0f;break;
				case R.id.paint09:index = 8;color = 0xffae9c6f;break;
				case R.id.paint10:index = 9;color = 0xffae9c6f;break;
				case R.id.paint11:index = 10;color = 0xff5a2a5b;break;
				case R.id.paint12:index = 11;color = 0xff5a2a5b;break;
				case R.id.paint13:index = 12;color = 0xff3f1773;break;
				case R.id.paint14:index = 13;color = 0xff3f1773;break;
				case R.id.paint15:index = 14;color = 0xff9b87d9;break;
				case R.id.paint16:index = 15;color = 0xff9b87d9;break;
				case R.id.paint17:index = 16;color = 0xff0c0857;break;
				case R.id.paint18:index = 17;color = 0xff0c0857;break;
				case R.id.paint19:index = 18;color = 0xff1430a6;break;
				case R.id.paint20:index = 19;color = 0xff1430a6;break;
				case R.id.paint21:index = 20;color = 0xff79a8d1;break;
				case R.id.paint22:index = 21;color = 0xff79a8d1;break;
				case R.id.paint23:index = 22;color = 0xff1f6c75;break;
				case R.id.paint24:index = 23;color = 0xff1f6c75;break;
				case R.id.paint25:index = 24;color = 0xff154329;break;
				case R.id.paint26:index = 25;color = 0xff154329;break;
				case R.id.paint27:index = 26;color = 0xff407532;break;
				case R.id.paint28:index = 27;color = 0xff407532;break;
				case R.id.paint29:index = 28;color = 0xff61b93a;break;
				case R.id.paint30:index = 29;color = 0xff61b93a;break;
				case R.id.paint31:index = 30;color = 0xffb5e41d;break;
				case R.id.paint32:index = 31;color = 0xffb5e41d;break;
				case R.id.paint33:index = 32;color = 0xfffbf504;break;
				case R.id.paint34:index = 33;color = 0xfffbf504;break;
				case R.id.paint35:index = 34;color = 0xffd9b030;break;
				case R.id.paint36:index = 35;color = 0xffd9b030;break;
				case R.id.paint37:index = 36;color = 0xfff2a618;break;
				case R.id.paint38:index = 37;color = 0xfff2a618;break;
				case R.id.paint39:index = 38;color = 0xffeac0a0;break;
				case R.id.paint40:index = 39;color = 0xffeac0a0;break;
				case R.id.paint41:index = 40;color = 0xfff98e5f;break;
				case R.id.paint42:index = 41;color = 0xfff98e5f;break;
				case R.id.paint43:index = 42;color = 0xfff15908;break;
				case R.id.paint44:index = 43;color = 0xfff15908;break;
				case R.id.paint45:index = 44;color = 0xffa70910;break;
				case R.id.paint46:index = 45;color = 0xffa70910;break;
				case R.id.paint47:index = 46;color = 0xff60121d;break;
				case R.id.paint48:index = 47;color = 0xff60121d;break;
				case R.id.paint49:index = 48;color = 0xff9c0e4d;break;
				case R.id.paint50:index = 49;color = 0xff9c0e4d;break;
				case R.id.paint51:index = 50;color = 0xffe0abba;break;
				case R.id.paint52:index = 51;color = 0xffe0abba;break;
			}
			ImageView imageView = (ImageView)activity.findViewById(activity.brushDrawableId);
			imageView.scrollTo(0, -30);
			casualColor[index].scrollTo(0, 0);
			activity.brushDrawableId = v.getId();
			if(index % 2 == 0)
				drawView.setBrushBitmap(DrawAttribute.DrawStatus.CASUAL_COLOR_SMALL,color);
			else drawView.setBrushBitmap(DrawAttribute.DrawStatus.CASUAL_COLOR_BIG,color);
		}	
	}
}
