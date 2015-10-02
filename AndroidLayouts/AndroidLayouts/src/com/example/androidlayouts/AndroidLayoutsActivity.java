package com.example.androidlayouts;

import android.app.Activity;
import android.os.Bundle;

public class AndroidLayoutsActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
		
		// uncomment below for relative layout
		//setContentView(R.layout.relative_layout);
		
		// uncomment below for relative layout
		//setContentView(R.layout.table_layout);
    }
}