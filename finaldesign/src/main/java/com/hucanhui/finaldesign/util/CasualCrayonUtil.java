package com.hucanhui.finaldesign.util;

import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.view.DrawView;

public class CasualCrayonUtil {	
	private DrawView drawView;
	private DrawActivity activity;
	public CasualCrayonUtil(DrawActivity activity,DrawView drawView) {
		this.drawView = drawView;
		this.activity = activity;
	}
	
	public void casualCrayonPicSetOnClickListener() {
		ImageView[] casualCrayons = new ImageView[68];
		casualCrayons[0] = (ImageView)activity.findViewById(R.id.crayon01);
		casualCrayons[1] = (ImageView)activity.findViewById(R.id.crayon02);
		casualCrayons[2] = (ImageView)activity.findViewById(R.id.crayon03);
		casualCrayons[3] = (ImageView)activity.findViewById(R.id.crayon04);
		casualCrayons[4] = (ImageView)activity.findViewById(R.id.crayon05);
		casualCrayons[5] = (ImageView)activity.findViewById(R.id.crayon06);
		casualCrayons[6] = (ImageView)activity.findViewById(R.id.crayon07);
		casualCrayons[7] = (ImageView)activity.findViewById(R.id.crayon08);
		casualCrayons[8] = (ImageView)activity.findViewById(R.id.crayon09);
		casualCrayons[9] = (ImageView)activity.findViewById(R.id.crayon10);
		casualCrayons[10] = (ImageView)activity.findViewById(R.id.crayon11);
		casualCrayons[11] = (ImageView)activity.findViewById(R.id.crayon12);
		casualCrayons[12] = (ImageView)activity.findViewById(R.id.crayon13);
		casualCrayons[13] = (ImageView)activity.findViewById(R.id.crayon14);
		casualCrayons[14] = (ImageView)activity.findViewById(R.id.crayon15);
		casualCrayons[15] = (ImageView)activity.findViewById(R.id.crayon16);
		casualCrayons[16] = (ImageView)activity.findViewById(R.id.crayon17);
		casualCrayons[17] = (ImageView)activity.findViewById(R.id.crayon18);
		casualCrayons[18] = (ImageView)activity.findViewById(R.id.crayon19);
		casualCrayons[19] = (ImageView)activity.findViewById(R.id.crayon20);
		casualCrayons[20] = (ImageView)activity.findViewById(R.id.crayon21);
		casualCrayons[21] = (ImageView)activity.findViewById(R.id.crayon22);
		casualCrayons[22] = (ImageView)activity.findViewById(R.id.crayon23);
		casualCrayons[23] = (ImageView)activity.findViewById(R.id.crayon24);
		casualCrayons[24] = (ImageView)activity.findViewById(R.id.crayon25);
		casualCrayons[25] = (ImageView)activity.findViewById(R.id.crayon26);
		casualCrayons[26] = (ImageView)activity.findViewById(R.id.crayon27);
		casualCrayons[27] = (ImageView)activity.findViewById(R.id.crayon28);
		casualCrayons[28] = (ImageView)activity.findViewById(R.id.crayon29);
		casualCrayons[29] = (ImageView)activity.findViewById(R.id.crayon30);
		casualCrayons[30] = (ImageView)activity.findViewById(R.id.crayon31);
		casualCrayons[31] = (ImageView)activity.findViewById(R.id.crayon32);
		casualCrayons[32] = (ImageView)activity.findViewById(R.id.crayon33);
		casualCrayons[33] = (ImageView)activity.findViewById(R.id.crayon34);
		casualCrayons[34] = (ImageView)activity.findViewById(R.id.crayon35);
		casualCrayons[35] = (ImageView)activity.findViewById(R.id.crayon36);
		casualCrayons[36] = (ImageView)activity.findViewById(R.id.crayon37);
		casualCrayons[37] = (ImageView)activity.findViewById(R.id.crayon38);
		casualCrayons[38] = (ImageView)activity.findViewById(R.id.crayon39);
		casualCrayons[39] = (ImageView)activity.findViewById(R.id.crayon40);
		casualCrayons[40] = (ImageView)activity.findViewById(R.id.crayon41);
		casualCrayons[41] = (ImageView)activity.findViewById(R.id.crayon42);
		casualCrayons[42] = (ImageView)activity.findViewById(R.id.crayon43);
		casualCrayons[43] = (ImageView)activity.findViewById(R.id.crayon44);
		casualCrayons[44] = (ImageView)activity.findViewById(R.id.crayon45);
		casualCrayons[45] = (ImageView)activity.findViewById(R.id.crayon46);
		casualCrayons[46] = (ImageView)activity.findViewById(R.id.crayon47);
		casualCrayons[47] = (ImageView)activity.findViewById(R.id.crayon48);
		casualCrayons[48] = (ImageView)activity.findViewById(R.id.crayon49);
		casualCrayons[49] = (ImageView)activity.findViewById(R.id.crayon50);
		casualCrayons[50] = (ImageView)activity.findViewById(R.id.crayon51);
		casualCrayons[51] = (ImageView)activity.findViewById(R.id.crayon52);
		casualCrayons[52] = (ImageView)activity.findViewById(R.id.crayon53);
		casualCrayons[53] = (ImageView)activity.findViewById(R.id.crayon54);
		casualCrayons[54] = (ImageView)activity.findViewById(R.id.crayon55);
		casualCrayons[55] = (ImageView)activity.findViewById(R.id.crayon56);
		casualCrayons[56] = (ImageView)activity.findViewById(R.id.crayon57);
		casualCrayons[57] = (ImageView)activity.findViewById(R.id.crayon58);
		casualCrayons[58] = (ImageView)activity.findViewById(R.id.crayon59);
		casualCrayons[59] = (ImageView)activity.findViewById(R.id.crayon60);
		casualCrayons[60] = (ImageView)activity.findViewById(R.id.crayon61);
		casualCrayons[61] = (ImageView)activity.findViewById(R.id.crayon62);
		casualCrayons[62] = (ImageView)activity.findViewById(R.id.crayon63);
		casualCrayons[63] = (ImageView)activity.findViewById(R.id.crayon64);
		casualCrayons[64] = (ImageView)activity.findViewById(R.id.crayon65);
		casualCrayons[65] = (ImageView)activity.findViewById(R.id.crayon66);
		casualCrayons[66] = (ImageView)activity.findViewById(R.id.crayon67);
		casualCrayons[67] = (ImageView)activity.findViewById(R.id.crayon68);
		CasualCrayonListener crayonListener = new CasualCrayonListener(casualCrayons);
		for(int i = 0;i < casualCrayons.length;i++) {
			casualCrayons[i].setOnClickListener(crayonListener);
		}		
	}
	
	class CasualCrayonListener implements View.OnClickListener {
		private ImageView[] casualCrayons;
		public CasualCrayonListener(ImageView[] casualCrayons) {
			this.casualCrayons = casualCrayons;
		}
		@Override
		public void onClick(View v) {	
			int index = 0;int color = 0xff000000;
			switch(v.getId()) {
				case R.id.crayon01:index = 0;color = 0xfff2f2f2;break;
				case R.id.crayon02:index = 1;color = 0xff131816;break;
				case R.id.crayon03:index = 2;color = 0xff5f6c73;break;
				case R.id.crayon04:index = 3;color = 0xffadb8bd;break;
				case R.id.crayon05:index = 4;color = 0xff455256;break;
				case R.id.crayon06:index = 5;color = 0xff886936;break;
				case R.id.crayon07:index = 6;color = 0xff502b18;break;
				case R.id.crayon08:index = 7;color = 0xff3f2d20;break;
				case R.id.crayon09:index = 8;color = 0xff482620;break;
				case R.id.crayon10:index = 9;color = 0xff6a2420;break;
				case R.id.crayon11:index = 10;color = 0xff8e1b11;break;
				case R.id.crayon12:index = 11;color = 0xffab3615;break;
				case R.id.crayon13:index = 12;color = 0xffc86f4c;break;
				case R.id.crayon14:index = 13;color = 0xffce2c1a;break;
				case R.id.crayon15:index = 14;color = 0xffd02d2f;break;
				case R.id.crayon16:index = 15;color = 0xffa70f0d;break;
				case R.id.crayon17:index = 16;color = 0xffe84a1d;break;
				case R.id.crayon18:index = 17;color = 0xfffe3921;break;
				case R.id.crayon19:index = 18;color = 0xffff7b11;break;
				case R.id.crayon20:index = 19;color = 0xfffd661e;break;
				case R.id.crayon21:index = 20;color = 0xffef4038;break;
				case R.id.crayon22:index = 21;color = 0xff6c0e05;break;
				case R.id.crayon23:index = 22;color = 0xff6c191e;break;
				case R.id.crayon24:index = 23;color = 0xff9b2025;break;
				case R.id.crayon25:index = 24;color = 0xffaf1118;break;
				case R.id.crayon26:index = 25;color = 0xffed1c2a;break;
				case R.id.crayon27:index = 26;color = 0xfffd3e5a;break;
				case R.id.crayon28:index = 27;color = 0xff96435c;break;
				case R.id.crayon29:index = 28;color = 0xfffd60a0;break;
				case R.id.crayon30:index = 29;color = 0xffa4101b;break;
				case R.id.crayon31:index = 30;color = 0xffdf455a;break;
				case R.id.crayon32:index = 31;color = 0xfffd211e;break;
				case R.id.crayon33:index = 32;color = 0xfff61749;break;
				case R.id.crayon34:index = 33;color = 0xffda63ae;break;
				case R.id.crayon35:index = 34;color = 0xffb0294d;break;
				case R.id.crayon36:index = 35;color = 0xff6e1e24;break;
				case R.id.crayon37:index = 36;color = 0xff8f1d36;break;
				case R.id.crayon38:index = 37;color = 0xff531827;break;
				case R.id.crayon39:index = 38;color = 0xffa53b8e;break;
				case R.id.crayon40:index = 39;color = 0xff595195;break;
				case R.id.crayon41:index = 40;color = 0xff6a4782;break;
				case R.id.crayon42:index = 41;color = 0xff4e2d39;break;
				case R.id.crayon43:index = 42;color = 0xff453753;break;
				case R.id.crayon44:index = 43;color = 0xff0e1447;break;
				case R.id.crayon45:index = 44;color = 0xff00193f;break;
				case R.id.crayon46:index = 45;color = 0xff1c2d5c;break;
				case R.id.crayon47:index = 46;color = 0xff1d253c;break;
				case R.id.crayon48:index = 47;color = 0xff223984;break;
				case R.id.crayon49:index = 48;color = 0xff5b78b4;break;
				case R.id.crayon50:index = 49;color = 0xff55859c;break;
				case R.id.crayon51:index = 50;color = 0xff4ea5cb;break;
				case R.id.crayon52:index = 51;color = 0xff142c41;break;
				case R.id.crayon53:index = 52;color = 0xff1f5b73;break;
				case R.id.crayon54:index = 53;color = 0xff178281;break;
				case R.id.crayon55:index = 54;color = 0xff51ba94;break;
				case R.id.crayon56:index = 55;color = 0xff5fbd26;break;
				case R.id.crayon57:index = 56;color = 0xff1e8c2e;break;
				case R.id.crayon58:index = 57;color = 0xff2f4933;break;
				case R.id.crayon59:index = 58;color = 0xff1f534d;break;
				case R.id.crayon60:index = 59;color = 0xff2f5344;break;
				case R.id.crayon61:index = 60;color = 0xff30412e;break;
				case R.id.crayon62:index = 61;color = 0xff415123;break;
				case R.id.crayon63:index = 62;color = 0xffa9b472;break;
				case R.id.crayon64:index = 63;color = 0xff94ad28;break;
				case R.id.crayon65:index = 64;color = 0xffdfb832;break;
				case R.id.crayon66:index = 65;color = 0xffb57a15;break;
				case R.id.crayon67:index = 66;color = 0xfffd8f17;break;
				case R.id.crayon68:index = 67;color = 0xffdf8e14;break;
			}
			ImageView imageView = (ImageView)activity.findViewById(activity.brushDrawableId);
			imageView.scrollTo(0, -30);
			casualCrayons[index].scrollTo(0, 0);
			activity.brushDrawableId = v.getId();
			drawView.setBrushBitmap(DrawAttribute.DrawStatus.CASUAL_CRAYON,color);
		}	
	}
}
