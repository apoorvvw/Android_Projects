package com.boilers.mhacks.connections;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUi;

import java.util.List;

import io.fabric.sdk.android.Fabric;


public class RequestActivity extends ActionBarActivity {
    long maxId;
    String act, tag1, tag2;
    public List<Tweet> tweets;
    //final TweetViewAdapter adapter = new TweetViewAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent i = this.getIntent();
        act = i.getStringExtra("tag0");
        tag1 = i.getStringExtra("tag1");
        tag2 = i.getStringExtra("tag2");

        Log.i("MY_COMMANDD",act+"------NULL");

        TwitterAuthConfig authConfig =
                new TwitterAuthConfig("consumerKey",
                        "consumerSecret");
        Fabric.with(this, new TwitterCore(authConfig),
                new TweetUi());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        this.getIntent();
        Log.i("request activity","entered the activity");
        loadTweets();

    }

    private void loadTweets() {
        int sc = 20;
        String srt = "all";
        String sq = "(#mhacks OR #mhackstweetconnection)" + " AND " + act + " OR " + tag1 + " OR " + tag2;
        String test = "#chicago";
        Log.i("MY_COMMAND",sq);
        SearchService service = TwitterCore.getInstance().getApiClient().getSearchService();

        service.tweets(sq , null, null, null, srt, sc, null, null, maxId, true, new Callback<Search>() {

                    @Override
                    public void success(Result<Search> searchResult) {
                        tweets = searchResult.data.tweets;


//                        adapter.getTweets().addAll(tweets);
//                        adapter.notifyDataSetChanged();
                        if (tweets.size() > 0) {
                            maxId = tweets.get(tweets.size() - 1).id - 1;
                        } else {
                            //endOfSearchResults = true;
                        }
                        //flagLoading = false;
                        final LinearLayout myLayout = (LinearLayout) findViewById(R.id.lineartweet);
//                        final long tweetId = 510908133917487104L;
//                        TweetUtils.loadTweet(tweetId, new LoadCallback<Tweet>() {
//                            @Override
//                            public void success(Tweet tweet) {
//                                myLayout.addView(new TweetView(
//                                        RequestActivity.this, tweet));
//                            }
//
//                            @Override
//                            public void failure(TwitterException exception) {
//                                // Toast.makeText(...).show();
//                            }
//                        });
                        if(tweets!=null) {
                            Log.i("123213", "tweet");
                            for(Tweet tweet : tweets) {

                                myLayout.addView(
                                        new CompactTweetView(
                                                RequestActivity.this,
                                                tweet));
                            }

                        }
                        Log.i("123", "callback1 "+maxId + " " + tweets.size());
                    }

                    @Override
                    public void failure(TwitterException error) {

                        Toast.makeText(RequestActivity.this, "Could not match",
                                Toast.LENGTH_LONG).show();
                        Log.i("123", "callback");
                        //flagLoading = false;
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
