package com.hucanhui.finaldesign;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;

public class BrowseActivity extends Activity {
	private ArrayList<String> fileList;
	private BrowseOnPageChangeListener browseOnPageChangeListener;
	private BrowseAdapter browseAdapter;
	private LinearLayout browseTopLinearLayout;
	private LinearLayout browseBottomLinearLayout;
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {				
			postDelayed(new layoutRunnable(), 3000);
			super.handleMessage(msg);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		Serializable serializable = bundle.getSerializable("com.example.finaldesign.BitmapPath");	
		fileList = (ArrayList<String>)serializable;
		int position = bundle.getInt("com.example.finaldesign.Position", 0);
		browseTopLinearLayout = (LinearLayout)(this.findViewById(R.id.browsetop));
		browseBottomLinearLayout = (LinearLayout)(this.findViewById(R.id.browsebottom));
		//browseTopLinearLayout.setVisibility(View.INVISIBLE);
		//browseBottomLinearLayout.setVisibility(View.INVISIBLE);
		handler.sendEmptyMessage(0);
		ViewPager viewPager = (ViewPager)(this.findViewById(R.id.browseviewpager));
		browseAdapter = new BrowseAdapter();
		browseOnPageChangeListener = new BrowseOnPageChangeListener();
		viewPager.setAdapter(browseAdapter);
		viewPager.setOnPageChangeListener(browseOnPageChangeListener);
		viewPager.setCurrentItem(position);
		browseOnPageChangeListener.pageNum = position;
		ImageButton browsebackbtn = (ImageButton)this.findViewById(R.id.browsebackbtn);
		ImageButton browseeditbtn = (ImageButton)this.findViewById(R.id.browseeditbtn);
		ImageButton browsesharebtn = (ImageButton)this.findViewById(R.id.browsesharebtn);
		ImageButton browsedeletebtn = (ImageButton)this.findViewById(R.id.browsedeletebtn);
		//������ذ�ť
		browsebackbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();  
				intent.setClass(BrowseActivity.this,PreviewActivity.class);  
				startActivity(intent);
				BrowseActivity.this.finish();
			}			
		});
		//����༭��ť
		browseeditbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.removeCallbacksAndMessages(null);
				//handler.sendEmptyMessage(0);
				Intent intent = new Intent();  
				intent.setClass(BrowseActivity.this,DrawActivity.class);  
				intent.putExtra("com.example.finaldesign.BackgroundBitmapPath",
						fileList.get(browseOnPageChangeListener.pageNum));
				startActivity(intent);
				BrowseActivity.this.finish();
			}		
		});
		//�������ť
		browsesharebtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.removeCallbacksAndMessages(null);
				//handler.sendEmptyMessage(0);
				Intent picMessageIntent = new Intent(Intent.ACTION_SEND);
				String sharePath = fileList.get(browseOnPageChangeListener.pageNum);
				if(sharePath.endsWith(".png")) {
					picMessageIntent.setType("image/png");  
				}
				else if(sharePath.endsWith(".jpg")) {
					picMessageIntent.setType("image/jpg");
				}
				else if(sharePath.endsWith(".bmp")) {
					picMessageIntent.setType("image/bmp");
				}
				File shareFile = new File(sharePath);
				picMessageIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(shareFile));  
				startActivity(Intent.createChooser(picMessageIntent, "ѡ������Ӧ��:"));
			}		
		});
		//���ɾ����ť
		browsedeletebtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.removeCallbacksAndMessages(null);
				//handler.sendEmptyMessage(0);
				AlertDialog.Builder builder = new AlertDialog.Builder(BrowseActivity.this)
				.setIcon(drawable.ic_dialog_alert).setMessage("ȷ��ɾ����")
		   		.setPositiveButton("OK", new DialogInterface.OnClickListener() { 	
		   			@Override
		   			public void onClick(DialogInterface dialog, int which) {
		   				handler.sendEmptyMessage(0);
		   				browseAdapter.deletePage(browseOnPageChangeListener.pageNum);	
		   			}	
		   		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {		
		   			@Override				
		   			public void onClick(DialogInterface dialog, int which) {						
		   				//dialog.cancel();
		   				handler.sendEmptyMessage(0);
		   				dialog.dismiss();										
		   			}
		   		});
				builder.create().show();
			}			
		});
	}
		
	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacksAndMessages(null);
		handler = null;
	}

	@Override		
	public void onBackPressed() {		 		
		Intent intent = new Intent();  
		intent.setClass(BrowseActivity.this,PreviewActivity.class);  
		startActivity(intent);
		BrowseActivity.this.finish();
	}
	
	class BrowseAdapter extends PagerAdapter {
		
		@Override
		public int getCount() {
			return fileList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			 return arg0==((ImageView)arg1);
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((ImageView) arg2);
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			ImageView imageView = new ImageView(BrowseActivity.this);   
			Bitmap bitmap = BitmapFactory.decodeFile(fileList.get(arg1));
		    imageView.setImageBitmap(bitmap);
		    imageView.setPadding(5, 0, 5, 0);
		    imageView.setScaleType(ScaleType.CENTER_INSIDE);
		    imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					browseTopLinearLayout.setVisibility(View.VISIBLE);
					browseBottomLinearLayout.setVisibility(View.VISIBLE);
					handler.removeCallbacksAndMessages(null);
					handler.sendEmptyMessage(0);
				}
		    	
		    });
		    ((ViewPager) arg0).addView(imageView,0);  
		    return imageView; 
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
		
		public void deletePage(int position) {
			File file = new File(fileList.remove(position));
			file.delete();
			this.notifyDataSetChanged();
		}
	}
	
	class BrowseOnPageChangeListener implements OnPageChangeListener {
		public int pageNum;

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			if(browseTopLinearLayout.getVisibility() == View.VISIBLE) {
				browseTopLinearLayout.setVisibility(View.INVISIBLE);
				browseBottomLinearLayout.setVisibility(View.INVISIBLE);
			}
		}

		@Override
		public void onPageSelected(int arg0) {
			pageNum = arg0;
		}		
	}
	
	class layoutRunnable implements Runnable{
		@Override
		public void run() {			
			browseTopLinearLayout.setVisibility(View.INVISIBLE);
			browseBottomLinearLayout.setVisibility(View.INVISIBLE);
		}
	}
}
