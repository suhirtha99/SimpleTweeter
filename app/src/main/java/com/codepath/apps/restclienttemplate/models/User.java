package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {

    // list the attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;
    public int followers;
    public int likes;
    public int following;
    public int tweetCount;

    public User() {

    }
    // deserialize the JSON
    public static User fromJSON(JSONObject json) throws JSONException{
        User user = new User();

        // extract and fill the values
        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");
        user.followers = json.getInt("followers_count");
        user.following = json.getInt("friends_count");
        user.tweetCount = json.getInt("statuses_count");
        user.likes = json.getInt("favourites_count");


        return user;
    }
}