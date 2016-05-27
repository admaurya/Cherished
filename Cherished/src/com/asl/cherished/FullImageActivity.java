package com.asl.cherished;

import java.io.File;
import java.util.ArrayList;

import com.asl.cherished.MusicService.ServiceBinder;

import android.R.integer;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class FullImageActivity extends Activity {
	
	private ImageView previous, next;
	private ImageView btnSong;
	int count;
	int position;
	private boolean mIsBound = false;
	private MusicService mServ;
	int pos=1;
	
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_image);
				
		count=new ImageAdapter(FullImageActivity.this).mThumbIds.length;
		btnSong=(ImageView)findViewById(R.id.btnSong);
		
		previous=(ImageView)findViewById(R.id.btnPrevious);
		next=(ImageView)findViewById(R.id.btnNext);
				// get intent data
		Intent i = getIntent();		
		// Selected image id
		position = i.getExtras().getInt("id");
		final ImageAdapter imageAdapter = new ImageAdapter(this);
		
		final ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageResource(imageAdapter.mThumbIds[position]);
		
		if(position==0)
		{
			imageView.setImageResource(imageAdapter.mThumbIds[position]);
			previous.setVisibility(View.INVISIBLE);
		}
		else if(position==(count-1))
		{
			imageView.setImageResource(imageAdapter.mThumbIds[position]);
			next.setVisibility(View.INVISIBLE);
		}
		
		CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this);		 
		ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCustomPagerAdapter);
		
				
		previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
					previous.setVisibility(View.VISIBLE);
					next.setVisibility(View.VISIBLE);
					position--;
					imageView.setImageResource(imageAdapter.mThumbIds[position]);
					if(position==0)
					{
						imageView.setImageResource(imageAdapter.mThumbIds[position]);
						previous.setVisibility(View.INVISIBLE);
					}
				
			}
		});
		
		next.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {			
					previous.setVisibility(View.VISIBLE);
					next.setVisibility(View.VISIBLE);
					position++;
					imageView.setImageResource(imageAdapter.mThumbIds[position]);
					if(position==(count-1))
					{
						imageView.setImageResource(imageAdapter.mThumbIds[position]);
						next.setVisibility(View.INVISIBLE);
					}
				}
		});
		
		btnSong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Play Audio song
				
				Intent intent=new Intent(FullImageActivity.this, MusicService.class);
				doBindService(intent);				
			}
		});
		
		Intent intent=new Intent(FullImageActivity.this, MusicService.class);
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
			
		}
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			if(mServ!=null)
				mServ.stopMusic();
			doUnbindService();
		}
	
}
