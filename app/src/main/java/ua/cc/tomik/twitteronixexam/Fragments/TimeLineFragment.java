package ua.cc.tomik.twitteronixexam.Fragments;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.List;

import ua.cc.tomik.twitteronixexam.R;


public class TimeLineFragment extends ListFragment {


    public TimeLineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusesService statusesService = Twitter.getApiClient().getStatusesService();
        statusesService.homeTimeline(500, null, null, null, null, null, null,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        final FixedTweetTimeline homeTimeline = new FixedTweetTimeline.Builder()
                                .setTweets(listResult.data)
                                .build();
                        final TweetTimelineListAdapter homeAdapter = new TweetTimelineListAdapter.Builder(getActivity())
                                .setTimeline(homeTimeline)
                                .build();
                        setListAdapter(homeAdapter);
                    }

                    @Override
                    public void failure(TwitterException e) {
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_line, container, false);
    }

}
