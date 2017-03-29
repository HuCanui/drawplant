package com.hucanhui.finaldesign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView imageView = new ImageView(this);
		Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.about)).getBitmap();
		imageView.setImageBitmap(bitmap);
		setContentView(imageView);
	}
	
	@Override		
	public void onBackPressed() {		 		
		Intent intent = new Intent();  
		intent.setClass(AboutActivity.this,MainActivity.class);  
		startActivity(intent);
		AboutActivity.this.finish();
	}
}