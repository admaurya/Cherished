package com.asl.cherished;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Window;

public class SplashScreen extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        
        new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(SplashScreen.this, AndroidGridLayoutActivity.class));
				finish();
			}
		}, 2500);
        
	}

}
