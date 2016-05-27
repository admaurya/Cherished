package com.asl.cherished;

import com.asl.cherished.MusicServiceSecond.ServiceBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Point;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AndroidGridLayoutActivity extends Activity {
	private boolean mIsBound = false;
	private MusicServiceSecond mServ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);

		
		
		WindowManager wm = (WindowManager) AndroidGridLayoutActivity.this.getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
	    int width = metrics.widthPixels;
	    int height = metrics.heightPixels;
		
		
		GridView gridView = (GridView) findViewById(R.id.grid_view);
		
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this, width/3 , height/4));

		/**
		 * On Click event for Single Gridview Item
		 * */
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				// Sending image id to FullScreenActivity
				Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
				i.putExtra("id", position);
				startActivity(i);
			}
		});
		
		Intent intent=new Intent(AndroidGridLayoutActivity.this, MusicServiceSecond.class);
		doBindService(intent);	
		
	}
	
	/*@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Intent intent=new Intent(AndroidGridLayoutActivity.this, MusicServiceSecond.class);
		doBindService(intent);	
	}*/
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Intent intent=new Intent(AndroidGridLayoutActivity.this, MusicServiceSecond.class);
		doBindService(intent);	
	}
	
	private ServiceConnection Scon =new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder
	     service) {
			ServiceBinder binder=(ServiceBinder)service;
			mServ=binder.getService();
			if(mServ!=null)
			{
				mServ.playMusic();
				Log.e("SERVICE", "SERVICE----------------------------");
			}
			
		}

		public void onServiceDisconnected(ComponentName name) {
			mServ = null;
		}
		};

		void doBindService(Intent intent){
	 		bindService(intent,
					Scon,Context.BIND_AUTO_CREATE);
			mIsBound = true;
		}

		void doUnbindService()
		{
			if(mIsBound)
			{
				unbindService(Scon);
	      		mIsBound = false;
			}
		}
		
		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			if(mServ!=null)
				mServ.stopMusic();	
			doUnbindService();
		}
		
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			
		}
	
}