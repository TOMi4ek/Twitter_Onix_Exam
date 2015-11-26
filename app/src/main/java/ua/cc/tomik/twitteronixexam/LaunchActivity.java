package ua.cc.tomik.twitteronixexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class LaunchActivity extends AppCompatActivity {

    private static final String TWITTER_KEY =    "joMNDj1cijsZp0WjlVn1Fy3TC";
    private static final String TWITTER_SECRET = "xBHWzpVzJfyZYU63Bu6Xu7gH79iXiLDlVHD9OMrk8IScsSDjZK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_launch);
        Intent toMainIntent = new Intent(LaunchActivity.this,MainActivity.class);
        Intent toLoginIntent = new Intent(LaunchActivity.this,LoginActivity.class);
        toLoginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        toMainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Twitter.getSessionManager().getActiveSession() != null) {
            startActivity(toMainIntent);
        } else {
            startActivity(toLoginIntent);
        }
    }
}
