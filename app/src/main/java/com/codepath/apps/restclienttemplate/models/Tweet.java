package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    // list out the attributes
    public String body;
    public long uid; //database ID for the tweet
    public User user;
    public String createAt;
    public String handleName;

    public Tweet() {

    }

    // deserialize the JSON
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException{
        Tweet tweet = new Tweet();

        // extract the values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        //Log.d("Image", jsonObject.getString("media_url"));
        tweet.handleName = tweet.user.screenName;
        return tweet;
    }

}
