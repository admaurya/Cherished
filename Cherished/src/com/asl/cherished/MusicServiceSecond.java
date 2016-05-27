package com.asl.cherished;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
public class MusicServiceSecond extends Service  implements MediaPlayer.OnErrorListener{

    private final IBinder mBinder = new ServiceBinder();
    MediaPlayer mPlayer;
    private int length = 0;
    
    public Integer song[]={R.raw.marriage};
    
    

    public MusicServiceSecond() { }

    public class ServiceBinder extends Binder {
    	MusicServiceSecond getService()
    	 {
    		return MusicServiceSecond.this;
    	 }
    }

    @Override
    public IBinder onBind(Intent arg0){return mBinder;}

    @Override
    public void onCreate (){
	  super.onCreate();	

	  //int pos=R.raw.audio;
	  mPlayer = MediaPlayer.create(this, song[0]);
      mPlayer.setOnErrorListener(this);

       if(mPlayer!= null)
        {
        	mPlayer.setLooping(true);
        	mPlayer.setVolume(100,100);
        }


        mPlayer.setOnErrorListener(new OnErrorListener() {

	  public boolean onError(MediaPlayer mp, int what, int
          extra){

			onError(mPlayer, what, extra);
			return true;
		}
    	  });
	}

   /* @Override
	public int onStartCommand (Intent intent, int flags, int startId)
	{
         mPlayer.start();
         return START_STICKY;
	}*/
  
    public void playMusic()
	{
    	mPlayer.start();
 	}

    
	public void pauseMusic()
	{
		if(mPlayer.isPlaying())
		{
			mPlayer.pause();
			length=mPlayer.getCurrentPosition();

		}
	}

	public void resumeMusic()
	{
		if(mPlayer.isPlaying()==false)
		{
			mPlayer.seekTo(length);
			mPlayer.start();
		}
	}

	public void stopMusic()
	{
		if(mPlayer!=null)
		{
		mPlayer.stop();
		mPlayer.release();
		mPlayer = null;
		}
	}

	@Override
	public void onDestroy ()
	{
		super.onDestroy();
		if(mPlayer != null)
		{
		try{
		 mPlayer.stop();
		 mPlayer.release();
			}finally {
				mPlayer = null;
			}
		}
	}
	
	public void againStart(int position)
	{
		if(mPlayer==null)
		{
			mPlayer=MediaPlayer.create(this, song[position]);
			if(mPlayer!= null)
	        {
	        	mPlayer.setLooping(true);
	        	mPlayer.setVolume(100,100);
	        	mPlayer.start();
	        }
		}
	}
	
	
	@Override
	public boolean onUnbind(Intent intent){
		if(mPlayer!=null)
		{
		mPlayer.stop();
		mPlayer.release();
		}
	  return false;
	}

	public boolean onError(MediaPlayer mp, int what, int extra) {

		Toast.makeText(this, "music player failed", Toast.LENGTH_SHORT).show();
		if(mPlayer != null)
		{
			try{
				mPlayer.stop();
				mPlayer.release();
			}finally {
				mPlayer = null;
			}
		}
		return false;
	}
}