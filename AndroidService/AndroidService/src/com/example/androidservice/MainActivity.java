package com.example.androidservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
 int disply =0;
 IntentFilter myBDFilter;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
     ///myBDFilter = new IntentFilter(MyService.strService);
     
		
	}
	@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			myBDFilter = new IntentFilter(MyService.strService);
			this.registerReceiver(br, myBDFilter);
		}
	
	@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			this.unregisterReceiver(br);
		}

	// Start the service
	public void startService(View view) {
		startService(new Intent(this, MyService.class));
	}

	// Stop the service
	public void stopService(View view) {
		stopService(new Intent(this, MyService.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	BroadcastReceiver br = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context cont, Intent intent) {
			// TODO Auto-generated method stub
			disply =  intent.getIntExtra("COUNTER",5);
			Log.e("COUNTER",disply +"");
		}
	};	
}