package com.hucanhui.finaldesign;
//javaapk.com�ṩ����
import com.hucanhui.finaldesign.util.DrawAttribute;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;


public class SplashActivity extends Activity {
		
	private void initialDrawAttribute() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		DrawAttribute.screenWidth = dm.widthPixels;
		DrawAttribute.screenHeight = dm.heightPixels;
		DrawAttribute.paint.setColor(Color.LTGRAY);
		DrawAttribute.paint.setStrokeWidth(3);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.splash);   
	    Handler x = new Handler();
	    x.postDelayed(new splashhandler(), 2000);
	        
	}
	class splashhandler implements Runnable{
		@Override
		public void run() {
			initialDrawAttribute();
			startActivity(new Intent(getApplication(),MainActivity.class));
			SplashActivity.this.finish();
		}
	}

}
