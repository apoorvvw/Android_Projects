package com.boilers.mhacks.connections;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "49MutFR7SrXZMYxewC8zymjfn";
    private static final String TWITTER_SECRET = "dUhcBUa9LeBdC9us4VWsjCFKo3cKYIVZgK3jN7DzJ3ibvHOGVO";
    public static TwitterAuthToken authToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        if(Twitter.getSessionManager().getActiveSession()==null&&authToken==null) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        TextView provide = (TextView) findViewById(R.id.textView1);
        TextView request = (TextView) findViewById(R.id.textView2);
        provide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ProvideActivity.class);
                i.putExtra("status",true);
                startActivity(i);
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ProvideActivity.class);
                i.putExtra("status",false);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Twitter.getSessionManager().getActiveSession()==null&&authToken==null) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


}
