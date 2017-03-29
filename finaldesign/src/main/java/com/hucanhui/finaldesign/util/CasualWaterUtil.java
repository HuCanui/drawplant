package com.hucanhui.finaldesign.util;
//javaapk.com�ṩ����
import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.view.DrawView;

public class CasualWaterUtil {
	private DrawView drawView;
	private DrawActivity activity;
	public CasualWaterUtil(DrawActivity activity,DrawView drawView) {
		this.drawView = drawView;
		this.activity = activity;
	}
	
	public void casualWaterPicSetOnClickListener() {
		ImageView[] casualWaters = new ImageView[50];
		casualWaters[0] = (ImageView)activity.findViewById(R.id.marker01);
		casualWaters[1] = (ImageView)activity.findViewById(R.id.marker02);
		casualWaters[2] = (ImageView)activity.findViewById(R.id.marker03);
		casualWaters[3] = (ImageView)activity.findViewById(R.id.marker04);
		casualWaters[4] = (ImageView)activity.findViewById(R.id.marker05);
		casualWaters[5] = (ImageView)activity.findViewById(R.id.marker06);
		casualWaters[6] = (ImageView)activity.findViewById(R.id.marker07);
		casualWaters[7] = (ImageView)activity.findViewById(R.id.marker08);
		casualWaters[8] = (ImageView)activity.findViewById(R.id.marker09);
		casualWaters[9] = (ImageView)activity.findViewById(R.id.marker10);
		casualWaters[10] = (ImageView)activity.findViewById(R.id.marker11);
		casualWaters[11] = (ImageView)activity.findViewById(R.id.marker12);
		casualWaters[12] = (ImageView)activity.findViewById(R.id.marker13);
		casualWaters[13] = (ImageView)activity.findViewById(R.id.marker14);
		casualWaters[14] = (ImageView)activity.findViewById(R.id.marker15);
		casualWaters[15] = (ImageView)activity.findViewById(R.id.marker16);
		casualWaters[16] = (ImageView)activity.findViewById(R.id.marker17);
		casualWaters[17] = (ImageView)activity.findViewById(R.id.marker18);
		casualWaters[18] = (ImageView)activity.findViewById(R.id.marker19);
		casualWaters[19] = (ImageView)activity.findViewById(R.id.marker20);
		casualWaters[20] = (ImageView)activity.findViewById(R.id.marker21);
		casualWaters[21] = (ImageView)activity.findViewById(R.id.marker22);
		casualWaters[22] = (ImageView)activity.findViewById(R.id.marker23);
		casualWaters[23] = (ImageView)activity.findViewById(R.id.marker24);
		casualWaters[24] = (ImageView)activity.findViewById(R.id.marker25);
		casualWaters[25] = (ImageView)activity.findViewById(R.id.marker26);
		casualWaters[26] = (ImageView)activity.findViewById(R.id.marker27);
		casualWaters[27] = (ImageView)activity.findViewById(R.id.marker28);
		casualWaters[28] = (ImageView)activity.findViewById(R.id.marker29);
		casualWaters[29] = (ImageView)activity.findViewById(R.id.marker30);
		casualWaters[30] = (ImageView)activity.findViewById(R.id.marker31);
		casualWaters[31] = (ImageView)activity.findViewById(R.id.marker32);
		casualWaters[32] = (ImageView)activity.findViewById(R.id.marker33);
		casualWaters[33] = (ImageView)activity.findViewById(R.id.marker34);
		casualWaters[34] = (ImageView)activity.findViewById(R.id.marker35);
		casualWaters[35] = (ImageView)activity.findViewById(R.id.marker36);
		casualWaters[36] = (ImageView)activity.findViewById(R.id.marker37);
		casualWaters[37] = (ImageView)activity.findViewById(R.id.marker38);
		casualWaters[38] = (ImageView)activity.findViewById(R.id.marker39);
		casualWaters[39] = (ImageView)activity.findViewById(R.id.marker40);
		casualWaters[40] = (ImageView)activity.findViewById(R.id.marker41);
		casualWaters[41] = (ImageView)activity.findViewById(R.id.marker42);
		casualWaters[42] = (ImageView)activity.findViewById(R.id.marker43);
		casualWaters[43] = (ImageView)activity.findViewById(R.id.marker44);
		casualWaters[44] = (ImageView)activity.findViewById(R.id.marker45);
		casualWaters[45] = (ImageView)activity.findViewById(R.id.marker46);
		casualWaters[46] = (ImageView)activity.findViewById(R.id.marker47);
		casualWaters[47] = (ImageView)activity.findViewById(R.id.marker48);
		casualWaters[48] = (ImageView)activity.findViewById(R.id.marker49);
		casualWaters[49] = (ImageView)activity.findViewById(R.id.marker50);
		CasualWaterListener waterListener = new CasualWaterListener(casualWaters);
		for(int i = 0;i < casualWaters.length;i++) {
			casualWaters[i].setOnClickListener(waterListener);
		}		
	}
	
	class CasualWaterListener implements View.OnClickListener {
		private ImageView[] casualWaters;
		public CasualWaterListener(ImageView[] casualWaters) {
			this.casualWaters = casualWaters;
		}
		@Override
		public void onClick(View v) {	
			int index = 0;int color = 0xff000000;
			switch(v.getId()) {
				case R.id.marker01:index = 0;color = 0xff000000;break;
				case R.id.marker02:index = 1;color = 0xff767e81;break;
				case R.id.marker03:index = 2;color = 0xffababab;break;
				case R.id.marker04:index = 3;color = 0xff2c2011;break;
				case R.id.marker05:index = 4;color = 0xff692c15;break;
				case R.id.marker06:index = 5;color = 0xffac2e11;break;
				case R.id.marker07:index = 6;color = 0xffc36c39;break;
				case R.id.marker08:index = 7;color = 0xffd0830f;break;
				case R.id.marker09:index = 8;color = 0xfff86e06;break;
				case R.id.marker10:index = 9;color = 0xffea4c1f;break;
				case R.id.marker11:index = 10;color = 0xffeb231e;break;
				case R.id.marker12:index = 11;color = 0xffe94946;break;
				case R.id.marker13:index = 12;color = 0xffa8110f;break;
				case R.id.marker14:index = 13;color = 0xff82141a;break;
				case R.id.marker15:index = 14;color = 0xff88254e;break;
				case R.id.marker16:index = 15;color = 0xffce2e6f;break;
				case R.id.marker17:index = 16;color = 0xffde365c;break;
				case R.id.marker18:index = 17;color = 0xfffd5993;break;
				case R.id.marker19:index = 18;color = 0xffec617b;break;
				case R.id.marker20:index = 19;color = 0xffe9808d;break;
				case R.id.marker21:index = 20;color = 0xfffea0b8;break;
				case R.id.marker22:index = 21;color = 0xfff1b2a6;break;
				case R.id.marker23:index = 22;color = 0xffc596c5;break;
				case R.id.marker24:index = 23;color = 0xff8c91c4;break;
				case R.id.marker25:index = 24;color = 0xff3a1f61;break;
				case R.id.marker26:index = 25;color = 0xff236c78;break;
				case R.id.marker27:index = 26;color = 0xff16b1be;break;
				case R.id.marker28:index = 27;color = 0xff65ebcb;break;
				case R.id.marker29:index = 28;color = 0xff80daef;break;
				case R.id.marker30:index = 29;color = 0xff42c9e1;break;
				case R.id.marker31:index = 30;color = 0xff0caaca;break;
				case R.id.marker32:index = 31;color = 0xff31cbfc;break;
				case R.id.marker33:index = 32;color = 0xff39a4fd;break;
				case R.id.marker34:index = 33;color = 0xff0875ed;break;
				case R.id.marker35:index = 34;color = 0xff284feb;break;
				case R.id.marker36:index = 35;color = 0xff1a28a8;break;
				case R.id.marker37:index = 36;color = 0xff131324;break;
				case R.id.marker38:index = 37;color = 0xff083123;break;
				case R.id.marker39:index = 38;color = 0xff095e32;break;
				case R.id.marker40:index = 39;color = 0xff558d59;break;
				case R.id.marker41:index = 40;color = 0xff10963a;break;
				case R.id.marker42:index = 41;color = 0xff43731a;break;
				case R.id.marker43:index = 42;color = 0xff4c9f2e;break;
				case R.id.marker44:index = 43;color = 0xff3eed7d;break;
				case R.id.marker45:index = 44;color = 0xff7fe153;break;
				case R.id.marker46:index = 45;color = 0xffa7d93f;break;
				case R.id.marker47:index = 46;color = 0xfff6dd3d;break;
				case R.id.marker48:index = 47;color = 0xfff7cc2f;break;
				case R.id.marker49:index = 48;color = 0xfffab308;break;
				case R.id.marker50:index = 49;color = 0xfffda51a;break;
			}
			ImageView imageView = (ImageView)activity.findViewById(activity.brushDrawableId);
			imageView.scrollTo(0, -30);
			casualWaters[index].scrollTo(0, 0);
			activity.brushDrawableId = v.getId();
			drawView.setBrushBitmap(DrawAttribute.DrawStatus.CASUAL_WATER,color);
		}	
	}
}
