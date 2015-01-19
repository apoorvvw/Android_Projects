package com.boilers.mhacks.connections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;


public class ProvideActivity extends ActionBarActivity{

    TextView t1;
    TextView t2;
    TextView spintv;
    Spinner spin;
    String act = "drive";
    Boolean status;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent i = this.getIntent();
        status = i.getBooleanExtra("status",true);
        Fabric.with(this, new TweetComposer());
        setContentView(R.layout.activity_provide);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView3);
        t1.setText("From: ");
        t2.setText("TO  : ");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, R.layout.spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

//        spin = (Spinner) findViewById(R.id.spinner);
//        //   spin.setOnItemSelectedListener(this)
//         adapter = new ArrayAdapter<String>(
//                    this, android.R.layout.simple_spinner_item, ProvideData.setActions());
//         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spin.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                EditText qwerty = (EditText)findViewById(R.id.editText);
                EditText asdfgh = (EditText)findViewById(R.id.editText2);
                qwerty.setText("");
                asdfgh.setText("");
                switch(position)
                {
                    case 0:
                        act = "drive";
                        t1.setText("From: ");
                        t2.setText("TO  : ");
                        break;
                    case 1:
                        act = "share";
                        t1.setText("Class: ");
                        t2.setText("Sectn: ");
                        break;
                    case 2:
                        act = "sell";
                        t1.setText("Product: ");
                        t2.setText("Price: ");
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        ImageButton BB = (ImageButton) findViewById(R.id.imageButton);
        BB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText qwerty = (EditText) findViewById(R.id.editText);
                EditText asdfgh = (EditText) findViewById(R.id.editText2);
                    Intent puthusu = new Intent(ProvideActivity.this, RequestActivity.class);


                if (qwerty.getText().toString().isEmpty() || asdfgh.getText().toString().isEmpty()) {
                    return;
                }

                String text1 = "\n#MHacksTweetConnection #" + act;
                Log.i("MY_NEW",act);
                String temp = qwerty.getText().toString();
                for (int i = 0; i < temp.length(); ++i) {
                    if (temp.charAt(i) == ' ' || temp.charAt(i) == '\n' || temp.charAt(i) == '<' || temp.charAt(i) == '#') {
                        //temp[i] = '_';
                        temp.toCharArray()[i] = '_';
                        temp.toString();
                    }
                }
                if(!status){
                    puthusu.putExtra("tag0", "#" + act);
                    puthusu.putExtra("tag1", "#" + temp);
                }
                text1 += " #" + temp;
                temp = asdfgh.getText().toString();
                for (int i = 0; i < temp.length(); ++i) {
                    if (temp.charAt(i) == ' ' || temp.charAt(i) == '\n' || temp.charAt(i) == '<' || temp.charAt(i) == '#') {
                        temp.toCharArray()[i] = '_';
                        temp.toString();
                    }
                }
                if(!status){
                    puthusu.putExtra("tag2", "#" + temp);
                }

                text1 += " #" + temp;
                if(status) {/*

                    //TODO sendgrid code
                    SendGrid sendgrid = new SendGrid(api_user, api_key);

                    SendGrid.Email email = new SendGrid.Email();

                    email.addTo("test@sendgrid.com");
                    email.setFrom("you@youremail.com");
                    email.setSubject("Sending with SendGrid is Fun");
                    email.setHtml("and easy to do anywhere, even with Java");

                    SendGrid.Response response = sendgrid.send(email);


                    */
                    Toast.makeText(ProvideActivity.this, "Please feel free to add any message here, but DO NOT change the hash tags.", Toast.LENGTH_LONG).show();
                    TweetComposer.Builder builder = new TweetComposer.Builder(ProvideActivity.this)
                            .text(text1);
                    builder.show();
                    finish();   // TO FINISH THE EXISTANCE OF THIS ACTIVITY SO THAT USER DOESN"T RETURN BACK
                }
                else{
                    startActivity(puthusu);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_provide, menu);
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

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
