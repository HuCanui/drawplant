package com.hucanhui.finaldesign;
//javaapk.com�ṩ����
import com.hucanhui.finaldesign.view.MainView;

import android.R.drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {

	private MainView mainView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//overridePendingTransition(android.R.anim.fade_in,android.R.anim.slide_out_right); 
		mainView = new MainView(this);
		setContentView(mainView);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindDrawables(mainView);
		mainView.freeBitmaps();
	}

	@Override
	public void onBackPressed() {
   		 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
   		 .setIcon(drawable.ic_dialog_alert).setMessage("ȷ���˳�?")
   		 .setPositiveButton("OK", new DialogInterface.OnClickListener() { 					
				@Override
				public void onClick(DialogInterface dialog, int which) {
					System.exit(0);		
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
