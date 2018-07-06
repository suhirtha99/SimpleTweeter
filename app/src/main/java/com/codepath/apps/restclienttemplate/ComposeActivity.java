package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    private TwitterClient client;
    private AsyncHttpResponseHandler handler;
    private EditText etUserTweet;
    private Tweet tweet;
    private TextView tvCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etUserTweet = (EditText) findViewById(R.id.etUserTweet);

        tvCount = (TextView) findViewById(R.id.tvCount);

        etUserTweet.addTextChangedListener(mTextEditorWatcher);


        // create client
        client = TwitterApp.getRestClient(this);
        handler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    tweet = Tweet.fromJson(response);
                    Log.d("Tweet", "This is the message " + response.getString("text"));
                    Intent i = new Intent(ComposeActivity.this, TimelineActivity.class);
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(20, i);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        };

        //tvCount.addTextChangedListener(mTextEditorWatcher);

    }


    public void postTweet(View v) {
        String message = etUserTweet.getText().toString();
        client.sendTweet(message, handler);
    }

    public void exitTweet(View v) {
        Intent intent = new Intent(ComposeActivity.this, TimelineActivity.class);
        startActivity(intent);
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvCount.setText(String.valueOf(s.length())+ "/140 characters");

        }

        public void afterTextChanged(Editable s) {
        }
    };

}
