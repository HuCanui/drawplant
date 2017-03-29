package com.hucanhui.finaldesign.util;
//javaapk.com�ṩ����
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.hucanhui.finaldesign.DrawActivity;
import com.hucanhui.finaldesign.R;
import com.hucanhui.finaldesign.view.DrawView;

public class StickerUtil {	
	private DrawView drawView;
	private DrawActivity activity;
	
	private ViewPager viewPager;
	private View[] pageList;
	private ImageView[] dotImageViews;
	public StickerUtil(DrawActivity activity,DrawView drawView) {
		this.drawView = drawView;
		this.activity = activity;
		LayoutInflater inflater = activity.getLayoutInflater();
		pageList = new View[9];
		dotImageViews = new ImageView[9];
		pageList[0] = inflater.inflate(R.layout.stickerpic_page1, null);
		pageList[1] = inflater.inflate(R.layout.stickerpic_page2, null);
		pageList[2] = inflater.inflate(R.layout.stickerpic_page3, null);
		pageList[3] = inflater.inflate(R.layout.stickerpic_page4, null);
		pageList[4] = inflater.inflate(R.layout.stickerpic_page5, null);
		pageList[5] = inflater.inflate(R.layout.stickerpic_page6, null);
		pageList[6] = inflater.inflate(R.layout.stickerpic_page7, null);
		pageList[7] = inflater.inflate(R.layout.stickerpic_page8, null);
		pageList[8] = inflater.inflate(R.layout.stickerpic_page9, null);
		dotImageViews[0] = (ImageView)activity.findViewById(R.id.stickerdot1);
		dotImageViews[1] = (ImageView)activity.findViewById(R.id.stickerdot2);
		dotImageViews[2] = (ImageView)activity.findViewById(R.id.stickerdot3);
		dotImageViews[3] = (ImageView)activity.findViewById(R.id.stickerdot4);
		dotImageViews[4] = (ImageView)activity.findViewById(R.id.stickerdot5);
		dotImageViews[5] = (ImageView)activity.findViewById(R.id.stickerdot6);
		dotImageViews[6] = (ImageView)activity.findViewById(R.id.stickerdot7);
		dotImageViews[7] = (ImageView)activity.findViewById(R.id.stickerdot8);
		dotImageViews[8] = (ImageView)activity.findViewById(R.id.stickerdot9);
		dotImageViews[0].setImageResource(R.drawable.dothighlight);
		for(int i = 1; i < dotImageViews.length; i++)
			dotImageViews[i].setImageResource(R.drawable.dotnormal);
		viewPager = (ViewPager)(activity.findViewById(R.id.stickerviewpager));
		viewPager.setAdapter(new StikerAdapter());
		viewPager.setOnPageChangeListener(new StikerListener());
	}
	
	class StikerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return pageList.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(pageList[arg1]);
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(pageList[arg1]);
			return pageList[arg1];
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

		@Override
		public void finishUpdate(View arg0) {
		}
	}
	
	class StikerListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < dotImageViews.length; i++) {
				if (arg0 != i) {
					dotImageViews[i].setImageResource(R.drawable.dotnormal);		
				}
			}
			dotImageViews[arg0].setImageResource(R.drawable.dothighlight);		
		}

	}
	
	public void stickerPicSetOnClickListener() {
		ImageView[] stickers = new ImageView[148];
		stickers[0] = (ImageView)pageList[0].findViewById(R.id.stickerthumb01);
		stickers[1] = (ImageView)pageList[0].findViewById(R.id.stickerthumb02);
		stickers[2] = (ImageView)pageList[0].findViewById(R.id.stickerthumb03);
		stickers[3] = (ImageView)pageList[0].findViewById(R.id.stickerthumb04);
		stickers[4] = (ImageView)pageList[0].findViewById(R.id.stickerthumb05);
		stickers[5] = (ImageView)pageList[0].findViewById(R.id.stickerthumb06);
		stickers[6] = (ImageView)pageList[0].findViewById(R.id.stickerthumb07);
		stickers[7] = (ImageView)pageList[0].findViewById(R.id.stickerthumb08);
		stickers[8] = (ImageView)pageList[0].findViewById(R.id.stickerthumb09);
		stickers[9] = (ImageView)pageList[0].findViewById(R.id.stickerthumb10);
		stickers[10] = (ImageView)pageList[0].findViewById(R.id.stickerthumb11);
		stickers[11] = (ImageView)pageList[0].findViewById(R.id.stickerthumb12);
		stickers[12] = (ImageView)pageList[0].findViewById(R.id.stickerthumb13);
		stickers[13] = (ImageView)pageList[0].findViewById(R.id.stickerthumb14);
		stickers[14] = (ImageView)pageList[0].findViewById(R.id.stickerthumb15);
		stickers[15] = (ImageView)pageList[0].findViewById(R.id.stickerthumb16);
		stickers[16] = (ImageView)pageList[0].findViewById(R.id.stickerthumb17);
		stickers[17] = (ImageView)pageList[0].findViewById(R.id.stickerthumb18);
		stickers[18] = (ImageView)pageList[1].findViewById(R.id.stickerthumb19);
		stickers[19] = (ImageView)pageList[1].findViewById(R.id.stickerthumb20);
		stickers[20] = (ImageView)pageList[1].findViewById(R.id.stickerthumb21);
		stickers[21] = (ImageView)pageList[1].findViewById(R.id.stickerthumb22);
		stickers[22] = (ImageView)pageList[1].findViewById(R.id.stickerthumb23);
		stickers[23] = (ImageView)pageList[1].findViewById(R.id.stickerthumb24);
		stickers[24] = (ImageView)pageList[1].findViewById(R.id.stickerthumb25);
		stickers[25] = (ImageView)pageList[1].findViewById(R.id.stickerthumb26);
		stickers[26] = (ImageView)pageList[1].findViewById(R.id.stickerthumb27);
		stickers[27] = (ImageView)pageList[1].findViewById(R.id.stickerthumb28);
		stickers[28] = (ImageView)pageList[1].findViewById(R.id.stickerthumb29);
		stickers[29] = (ImageView)pageList[1].findViewById(R.id.stickerthumb30);
		stickers[30] = (ImageView)pageList[1].findViewById(R.id.stickerthumb31);
		stickers[31] = (ImageView)pageList[1].findViewById(R.id.stickerthumb32);
		stickers[32] = (ImageView)pageList[1].findViewById(R.id.stickerthumb33);
		stickers[33] = (ImageView)pageList[1].findViewById(R.id.stickerthumb34);
		stickers[34] = (ImageView)pageList[1].findViewById(R.id.stickerthumb35);
		stickers[35] = (ImageView)pageList[1].findViewById(R.id.stickerthumb36);
		stickers[36] = (ImageView)pageList[2].findViewById(R.id.stickerthumb37);
		stickers[37] = (ImageView)pageList[2].findViewById(R.id.stickerthumb38);
		stickers[38] = (ImageView)pageList[2].findViewById(R.id.stickerthumb39);
		stickers[39] = (ImageView)pageList[2].findViewById(R.id.stickerthumb40);
		stickers[40] = (ImageView)pageList[2].findViewById(R.id.stickerthumb41);
		stickers[41] = (ImageView)pageList[2].findViewById(R.id.stickerthumb42);
		stickers[42] = (ImageView)pageList[2].findViewById(R.id.stickerthumb43);
		stickers[43] = (ImageView)pageList[2].findViewById(R.id.stickerthumb44);
		stickers[44] = (ImageView)pageList[2].findViewById(R.id.stickerthumb45);
		stickers[45] = (ImageView)pageList[2].findViewById(R.id.stickerthumb46);
		stickers[46] = (ImageView)pageList[2].findViewById(R.id.stickerthumb47);
		stickers[47] = (ImageView)pageList[2].findViewById(R.id.stickerthumb48);
		stickers[48] = (ImageView)pageList[2].findViewById(R.id.stickerthumb49);
		stickers[49] = (ImageView)pageList[2].findViewById(R.id.stickerthumb50);
		stickers[50] = (ImageView)pageList[2].findViewById(R.id.stickerthumb51);
		stickers[51] = (ImageView)pageList[2].findViewById(R.id.stickerthumb52);
		stickers[52] = (ImageView)pageList[2].findViewById(R.id.stickerthumb53);
		stickers[53] = (ImageView)pageList[2].findViewById(R.id.stickerthumb54);
		stickers[54] = (ImageView)pageList[3].findViewById(R.id.stickerthumb55);
		stickers[55] = (ImageView)pageList[3].findViewById(R.id.stickerthumb56);
		stickers[56] = (ImageView)pageList[3].findViewById(R.id.stickerthumb57);
		stickers[57] = (ImageView)pageList[3].findViewById(R.id.stickerthumb58);
		stickers[58] = (ImageView)pageList[3].findViewById(R.id.stickerthumb59);
		stickers[59] = (ImageView)pageList[3].findViewById(R.id.stickerthumb60);
		stickers[60] = (ImageView)pageList[3].findViewById(R.id.stickerthumb61);
		stickers[61] = (ImageView)pageList[3].findViewById(R.id.stickerthumb62);
		stickers[62] = (ImageView)pageList[3].findViewById(R.id.stickerthumb63);
		stickers[63] = (ImageView)pageList[3].findViewById(R.id.stickerthumb64);
		stickers[64] = (ImageView)pageList[3].findViewById(R.id.stickerthumb65);
		stickers[65] = (ImageView)pageList[3].findViewById(R.id.stickerthumb66);
		stickers[66] = (ImageView)pageList[3].findViewById(R.id.stickerthumb67);
		stickers[67] = (ImageView)pageList[3].findViewById(R.id.stickerthumb68);
		stickers[68] = (ImageView)pageList[3].findViewById(R.id.stickerthumb69);
		stickers[69] = (ImageView)pageList[3].findViewById(R.id.stickerthumb70);
		stickers[70] = (ImageView)pageList[3].findViewById(R.id.stickerthumb71);
		stickers[71] = (ImageView)pageList[3].findViewById(R.id.stickerthumb72);
		stickers[72] = (ImageView)pageList[4].findViewById(R.id.stickerthumb73);
		stickers[73] = (ImageView)pageList[4].findViewById(R.id.stickerthumb74);
		stickers[74] = (ImageView)pageList[4].findViewById(R.id.stickerthumb75);
		stickers[75] = (ImageView)pageList[4].findViewById(R.id.stickerthumb76);
		stickers[76] = (ImageView)pageList[4].findViewById(R.id.stickerthumb77);
		stickers[77] = (ImageView)pageList[4].findViewById(R.id.stickerthumb78);
		stickers[78] = (ImageView)pageList[4].findViewById(R.id.stickerthumb79);
		stickers[79] = (ImageView)pageList[4].findViewById(R.id.stickerthumb80);
		stickers[80] = (ImageView)pageList[4].findViewById(R.id.stickerthumb81);
		stickers[81] = (ImageView)pageList[4].findViewById(R.id.stickerthumb82);
		stickers[82] = (ImageView)pageList[4].findViewById(R.id.stickerthumb83);
		stickers[83] = (ImageView)pageList[4].findViewById(R.id.stickerthumb84);
		stickers[84] = (ImageView)pageList[4].findViewById(R.id.stickerthumb85);
		stickers[85] = (ImageView)pageList[4].findViewById(R.id.stickerthumb86);
		stickers[86] = (ImageView)pageList[4].findViewById(R.id.stickerthumb87);
		stickers[87] = (ImageView)pageList[4].findViewById(R.id.stickerthumb88);
		stickers[88] = (ImageView)pageList[4].findViewById(R.id.stickerthumb89);
		stickers[89] = (ImageView)pageList[4].findViewById(R.id.stickerthumb90);
		stickers[90] = (ImageView)pageList[5].findViewById(R.id.stickerthumb91);
		stickers[91] = (ImageView)pageList[5].findViewById(R.id.stickerthumb92);
		stickers[92] = (ImageView)pageList[5].findViewById(R.id.stickerthumb93);
		stickers[93] = (ImageView)pageList[5].findViewById(R.id.stickerthumb94);
		stickers[94] = (ImageView)pageList[5].findViewById(R.id.stickerthumb95);
		stickers[95] = (ImageView)pageList[5].findViewById(R.id.stickerthumb96);
		stickers[96] = (ImageView)pageList[5].findViewById(R.id.stickerthumb97);
		stickers[97] = (ImageView)pageList[5].findViewById(R.id.stickerthumb98);
		stickers[98] = (ImageView)pageList[5].findViewById(R.id.stickerthumb99);
		stickers[99] = (ImageView)pageList[5].findViewById(R.id.stickerthumb100);
		stickers[100] = (ImageView)pageList[5].findViewById(R.id.stickerthumb101);
		stickers[101] = (ImageView)pageList[5].findViewById(R.id.stickerthumb102);
		stickers[102] = (ImageView)pageList[5].findViewById(R.id.stickerthumb103);
		stickers[103] = (ImageView)pageList[5].findViewById(R.id.stickerthumb104);
		stickers[104] = (ImageView)pageList[5].findViewById(R.id.stickerthumb105);
		stickers[105] = (ImageView)pageList[5].findViewById(R.id.stickerthumb106);
		stickers[106] = (ImageView)pageList[5].findViewById(R.id.stickerthumb107);
		stickers[107] = (ImageView)pageList[5].findViewById(R.id.stickerthumb108);
		stickers[108] = (ImageView)pageList[6].findViewById(R.id.stickerthumb109);
		stickers[109] = (ImageView)pageList[6].findViewById(R.id.stickerthumb110);
		stickers[110] = (ImageView)pageList[6].findViewById(R.id.stickerthumb111);
		stickers[111] = (ImageView)pageList[6].findViewById(R.id.stickerthumb112);
		stickers[112] = (ImageView)pageList[6].findViewById(R.id.stickerthumb113);
		stickers[113] = (ImageView)pageList[6].findViewById(R.id.stickerthumb114);
		stickers[114] = (ImageView)pageList[6].findViewById(R.id.stickerthumb115);
		stickers[115] = (ImageView)pageList[6].findViewById(R.id.stickerthumb116);
		stickers[116] = (ImageView)pageList[6].findViewById(R.id.stickerthumb117);
		stickers[117] = (ImageView)pageList[6].findViewById(R.id.stickerthumb118);
		stickers[118] = (ImageView)pageList[6].findViewById(R.id.stickerthumb119);
		stickers[119] = (ImageView)pageList[6].findViewById(R.id.stickerthumb120);
		stickers[120] = (ImageView)pageList[6].findViewById(R.id.stickerthumb121);
		stickers[121] = (ImageView)pageList[6].findViewById(R.id.stickerthumb122);
		stickers[122] = (ImageView)pageList[6].findViewById(R.id.stickerthumb123);
		stickers[123] = (ImageView)pageList[6].findViewById(R.id.stickerthumb124);
		stickers[124] = (ImageView)pageList[6].findViewById(R.id.stickerthumb125);
		stickers[125] = (ImageView)pageList[6].findViewById(R.id.stickerthumb126);
		stickers[126] = (ImageView)pageList[7].findViewById(R.id.stickerthumb127);
		stickers[127] = (ImageView)pageList[7].findViewById(R.id.stickerthumb128);
		stickers[128] = (ImageView)pageList[7].findViewById(R.id.stickerthumb129);
		stickers[129] = (ImageView)pageList[7].findViewById(R.id.stickerthumb130);
		stickers[130] = (ImageView)pageList[7].findViewById(R.id.stickerthumb131);
		stickers[131] = (ImageView)pageList[7].findViewById(R.id.stickerthumb132);
		stickers[132] = (ImageView)pageList[7].findViewById(R.id.stickerthumb133);
		stickers[133] = (ImageView)pageList[7].findViewById(R.id.stickerthumb134);
		stickers[134] = (ImageView)pageList[7].findViewById(R.id.stickerthumb135);
		stickers[135] = (ImageView)pageList[7].findViewById(R.id.stickerthumb136);
		stickers[136] = (ImageView)pageList[7].findViewById(R.id.stickerthumb137);
		stickers[137] = (ImageView)pageList[7].findViewById(R.id.stickerthumb138);
		stickers[138] = (ImageView)pageList[7].findViewById(R.id.stickerthumb139);
		stickers[139] = (ImageView)pageList[7].findViewById(R.id.stickerthumb140);
		stickers[140] = (ImageView)pageList[7].findViewById(R.id.stickerthumb141);
		stickers[141] = (ImageView)pageList[7].findViewById(R.id.stickerthumb142);
		stickers[142] = (ImageView)pageList[7].findViewById(R.id.stickerthumb143);
		stickers[143] = (ImageView)pageList[7].findViewById(R.id.stickerthumb144);
		stickers[144] = (ImageView)pageList[8].findViewById(R.id.stickerthumb145);
		stickers[145] = (ImageView)pageList[8].findViewById(R.id.stickerthumb146);
		stickers[146] = (ImageView)pageList[8].findViewById(R.id.stickerthumb147);
		stickers[147] = (ImageView)pageList[8].findViewById(R.id.stickerthumb148);
		StickerPage1Listener stickerPage1Listener = new StickerPage1Listener();
		StickerPage2Listener stickerPage2Listener = new StickerPage2Listener();
		StickerPage3Listener stickerPage3Listener = new StickerPage3Listener();
		StickerPage4Listener stickerPage4Listener = new StickerPage4Listener();
		StickerPage5Listener stickerPage5Listener = new StickerPage5Listener();
		StickerPage6Listener stickerPage6Listener = new StickerPage6Listener();
		StickerPage7Listener stickerPage7Listener = new StickerPage7Listener();
		StickerPage8Listener stickerPage8Listener = new StickerPage8Listener();
		StickerPage9Listener stickerPage9Listener = new StickerPage9Listener();
		for(int i = 0;i < 18;i++) {
			stickers[i].setOnTouchListener(stickerPage1Listener);
		}	
		for(int i = 18;i < 36;i++) {
			stickers[i].setOnTouchListener(stickerPage2Listener);
		}
		for(int i = 36;i < 54;i++) {
			stickers[i].setOnTouchListener(stickerPage3Listener);
		}
		for(int i = 54;i < 72;i++) {
			stickers[i].setOnTouchListener(stickerPage4Listener);
		}
		for(int i = 72;i < 90;i++) {
			stickers[i].setOnTouchListener(stickerPage5Listener);
		}
		for(int i = 90;i < 108;i++) {
			stickers[i].setOnTouchListener(stickerPage6Listener);
		}
		for(int i = 108;i < 126;i++) {
			stickers[i].setOnTouchListener(stickerPage7Listener);
		}
		for(int i = 126;i < 144;i++) {
			stickers[i].setOnTouchListener(stickerPage8Listener);
		}
		for(int i = 144;i < 148;i++) {
			stickers[i].setOnTouchListener(stickerPage9Listener);
		}
	}
	
	class StickerPage1Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker1.png";
			    switch(v.getId()) {
			    case R.id.stickerthumb01:break;
				case R.id.stickerthumb02:s = "sticker2.png";break;
				case R.id.stickerthumb03:s = "sticker3.png";break;
				case R.id.stickerthumb04:s = "sticker4.png";break;
				case R.id.stickerthumb05:s = "sticker5.png";break;
				case R.id.stickerthumb06:s = "sticker6.png";break;
				case R.id.stickerthumb07:s = "sticker7.png";break;
				case R.id.stickerthumb08:s = "sticker8.png";break;
				case R.id.stickerthumb09:s = "sticker9.png";break;
				case R.id.stickerthumb10:s = "sticker10.png";break;
				case R.id.stickerthumb11:s = "sticker11.png";break;
				case R.id.stickerthumb12:s = "sticker12.png";break;
				case R.id.stickerthumb13:s = "sticker13.png";break;
				case R.id.stickerthumb14:s = "sticker14.png";break;
				case R.id.stickerthumb15:s = "sticker15.png";break;
				case R.id.stickerthumb16:s = "sticker16.png";break;
				case R.id.stickerthumb17:s = "sticker17.png";break;
				case R.id.stickerthumb18:s = "sticker18.png";
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);	
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
	class StickerPage2Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker19.png";
			    switch(v.getId()) {
			    case R.id.stickerthumb19:break;
				case R.id.stickerthumb20:s = "sticker20.png";break;
				case R.id.stickerthumb21:s = "sticker21.png";break;
				case R.id.stickerthumb22:s = "sticker22.png";break;
				case R.id.stickerthumb23:s = "sticker23.png";break;
				case R.id.stickerthumb24:s = "sticker24.png";break;
				case R.id.stickerthumb25:s = "sticker25.png";break;
				case R.id.stickerthumb26:s = "sticker26.png";break;
				case R.id.stickerthumb27:s = "sticker27.png";break;
				case R.id.stickerthumb28:s = "sticker28.png";break;
				case R.id.stickerthumb29:s = "sticker29.png";break;
				case R.id.stickerthumb30:s = "sticker30.png";break;
				case R.id.stickerthumb31:s = "sticker31.png";break;
				case R.id.stickerthumb32:s = "sticker32.png";break;
				case R.id.stickerthumb33:s = "sticker33.png";break;
				case R.id.stickerthumb34:s = "sticker34.png";break;
				case R.id.stickerthumb35:s = "sticker35.png";break;
				case R.id.stickerthumb36:s = "sticker36.png";break;
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);	
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
	class StickerPage3Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker37.png";
			    switch(v.getId()) {
			    case R.id.stickerthumb37:break;
				case R.id.stickerthumb38:s = "sticker38.png";break;
				case R.id.stickerthumb39:s = "sticker39.png";break;
				case R.id.stickerthumb40:s = "sticker40.png";break;
				case R.id.stickerthumb41:s = "sticker41.png";break;
				case R.id.stickerthumb42:s = "sticker42.png";break;
				case R.id.stickerthumb43:s = "sticker43.png";break;
				case R.id.stickerthumb44:s = "sticker44.png";break;
				case R.id.stickerthumb45:s = "sticker45.png";break;
				case R.id.stickerthumb46:s = "sticker46.png";break;
				case R.id.stickerthumb47:s = "sticker47.png";break;
				case R.id.stickerthumb48:s = "sticker48.png";break;
				case R.id.stickerthumb49:s = "sticker49.png";break;
				case R.id.stickerthumb50:s = "sticker50.png";break;
				case R.id.stickerthumb51:s = "sticker51.png";break;
				case R.id.stickerthumb52:s = "sticker52.png";break;
				case R.id.stickerthumb53:s = "sticker53.png";break;
				case R.id.stickerthumb54:s = "sticker54.png";break;
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);	
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
	class StickerPage4Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker55.png";
			    switch(v.getId()) {
				case R.id.stickerthumb55:break;
				case R.id.stickerthumb56:s = "sticker56.png";break;
				case R.id.stickerthumb57:s = "sticker57.png";break;
				case R.id.stickerthumb58:s = "sticker58.png";break;
				case R.id.stickerthumb59:s = "sticker59.png";break;
				case R.id.stickerthumb60:s = "sticker60.png";break;
				case R.id.stickerthumb61:s = "sticker61.png";break;
				case R.id.stickerthumb62:s = "sticker62.png";break;
				case R.id.stickerthumb63:s = "sticker63.png";break;
				case R.id.stickerthumb64:s = "sticker64.png";break;
				case R.id.stickerthumb65:s = "sticker65.png";break;
				case R.id.stickerthumb66:s = "sticker66.png";break;
				case R.id.stickerthumb67:s = "sticker67.png";break;
				case R.id.stickerthumb68:s = "sticker68.png";break;
				case R.id.stickerthumb69:s = "sticker69.png";break;
				case R.id.stickerthumb70:s = "sticker70.png";break;
				case R.id.stickerthumb71:s = "sticker71.png";break;
				case R.id.stickerthumb72:s = "sticker72.png";break;
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);	
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
	class StickerPage5Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker73.png";
			    switch(v.getId()) {
			    case R.id.stickerthumb73:break;
				case R.id.stickerthumb74:s = "sticker74.png";break;
				case R.id.stickerthumb75:s = "sticker75.png";break;
				case R.id.stickerthumb76:s = "sticker76.png";break;
				case R.id.stickerthumb77:s = "sticker77.png";break;
				case R.id.stickerthumb78:s = "sticker78.png";break;
				case R.id.stickerthumb79:s = "sticker79.png";break;
				case R.id.stickerthumb80:s = "sticker80.png";break;
				case R.id.stickerthumb81:s = "sticker81.png";break;
				case R.id.stickerthumb82:s = "sticker82.png";break;
				case R.id.stickerthumb83:s = "sticker83.png";break;
				case R.id.stickerthumb84:s = "sticker84.png";break;
				case R.id.stickerthumb85:s = "sticker85.png";break;
				case R.id.stickerthumb86:s = "sticker86.png";break;
				case R.id.stickerthumb87:s = "sticker87.png";break;
				case R.id.stickerthumb88:s = "sticker88.png";break;
				case R.id.stickerthumb89:s = "sticker89.png";break;
				case R.id.stickerthumb90:s = "sticker90.png";break;
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
	class StickerPage6Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker91.png";
			    switch(v.getId()) {
			    case R.id.stickerthumb91:break;
				case R.id.stickerthumb92:s = "sticker92.png";break;
				case R.id.stickerthumb93:s = "sticker93.png";break;
				case R.id.stickerthumb94:s = "sticker94.png";break;
				case R.id.stickerthumb95:s = "sticker95.png";break;
				case R.id.stickerthumb96:s = "sticker96.png";break;
				case R.id.stickerthumb97:s = "sticker97.png";break;
				case R.id.stickerthumb98:s = "sticker98.png";break;
				case R.id.stickerthumb99:s = "sticker99.png";break;
				case R.id.stickerthumb100:s = "sticker100.png";break;
				case R.id.stickerthumb101:s = "sticker101.png";break;
				case R.id.stickerthumb102:s = "sticker102.png";break;
				case R.id.stickerthumb103:s = "sticker103.png";break;
				case R.id.stickerthumb104:s = "sticker104.png";break;
				case R.id.stickerthumb105:s = "sticker105.png";break;
				case R.id.stickerthumb106:s = "sticker106.png";break;
				case R.id.stickerthumb107:s = "sticker107.png";break;
				case R.id.stickerthumb108:s = "sticker108.png";
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
	class StickerPage7Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker109.png";
			    switch(v.getId()) {
			    case R.id.stickerthumb109:break;
				case R.id.stickerthumb110:s = "sticker110.png";break;
				case R.id.stickerthumb111:s = "sticker111.png";break;
				case R.id.stickerthumb112:s = "sticker112.png";break;
				case R.id.stickerthumb113:s = "sticker113.png";break;
				case R.id.stickerthumb114:s = "sticker114.png";break;
				case R.id.stickerthumb115:s = "sticker115.png";break;
				case R.id.stickerthumb116:s = "sticker116.png";break;
				case R.id.stickerthumb117:s = "sticker117.png";break;
				case R.id.stickerthumb118:s = "sticker118.png";break;
				case R.id.stickerthumb119:s = "sticker119.png";break;
				case R.id.stickerthumb120:s = "sticker120.png";break;
				case R.id.stickerthumb121:s = "sticker121.png";break;
				case R.id.stickerthumb122:s = "sticker122.png";break;
				case R.id.stickerthumb123:s = "sticker123.png";break;
				case R.id.stickerthumb124:s = "sticker124.png";break;
				case R.id.stickerthumb125:s = "sticker125.png";break;
				case R.id.stickerthumb126:s = "sticker126.png";break;
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	class StickerPage8Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker127.png";
			    switch(v.getId()) {
			    case R.id.stickerthumb127:break;
				case R.id.stickerthumb128:s = "sticker128.png";break;
				case R.id.stickerthumb129:s = "sticker129.png";break;
				case R.id.stickerthumb130:s = "sticker130.png";break;
				case R.id.stickerthumb131:s = "sticker131.png";break;
				case R.id.stickerthumb132:s = "sticker132.png";break;
				case R.id.stickerthumb133:s = "sticker133.png";break;
				case R.id.stickerthumb134:s = "sticker134.png";break;
				case R.id.stickerthumb135:s = "sticker135.png";break;
				case R.id.stickerthumb136:s = "sticker136.png";break;
				case R.id.stickerthumb137:s = "sticker137.png";break;
				case R.id.stickerthumb138:s = "sticker138.png";break;
				case R.id.stickerthumb139:s = "sticker139.png";break;
				case R.id.stickerthumb140:s = "sticker140.png";break;
				case R.id.stickerthumb141:s = "sticker141.png";break;
				case R.id.stickerthumb142:s = "sticker142.png";break;
				case R.id.stickerthumb143:s = "sticker143.png";break;
				case R.id.stickerthumb144:s = "sticker144.png";break;
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);	
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
	class StickerPage9Listener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(DrawAttribute.backgroundOnClickColor);break;
			case MotionEvent.ACTION_UP:v.setBackgroundColor(0x00ffffff);
			    String s = "sticker145.png";
			    switch(v.getId()) {
				case R.id.stickerthumb145:break;
				case R.id.stickerthumb146:s = "sticker146.png";break;
				case R.id.stickerthumb147:s = "sticker147.png";break;
				case R.id.stickerthumb148:s = "sticker148.png";break;
				}
			    Bitmap bitmap = DrawAttribute.getImageFromAssetsFile(activity,s,false);
			    drawView.addStickerBitmap(bitmap);
				break;
			case MotionEvent.ACTION_CANCEL:v.setBackgroundColor(0x00ff0000);
			}
			return true;
		}		
	}
	
}