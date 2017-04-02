package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class splash extends Activity{

	MediaPlayer oursong;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		 oursong = MediaPlayer.create(splash.this,R.raw.splash);
		oursong.start();
		Thread timer=new Thread(){
		   
			public void run(){
				try{
					sleep(5000);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					Intent newintent = new Intent("com.example.notes.NOTEACTIVITY");
					startActivity(newintent);
				}
			}
		
		};
		timer.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		oursong.release();
		finish();
	}
	

	 
}
