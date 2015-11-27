package ua.cc.tomik.twitteronixexam.Fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetEntities;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.CompactTweetView;

import java.util.List;

import ua.cc.tomik.twitteronixexam.R;
import ua.cc.tomik.twitteronixexam.ZoomableImageView;


public class OneTweetFragment extends Fragment implements View.OnClickListener{

    List<Tweet> mTweetList;
    FloatingActionButton fabNext, fabPrevious;
    int mCounter;
    CompactTweetView myTweetView;
    LinearLayout mClickerCrutch;
    ZoomableImageView fullImageZoomView;
    public Target target;

    public OneTweetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCounter = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_onetweet, container, false);
        StatusesService statusesService = Twitter.getApiClient().getStatusesService();

        mClickerCrutch = (LinearLayout) rootView.findViewById(R.id.myClickerCrutch);
        fabNext = (FloatingActionButton) rootView.findViewById(R.id.fabDown);
        fabPrevious = (FloatingActionButton) rootView.findViewById(R.id.fabUp);
        myTweetView = (CompactTweetView) rootView.findViewById(R.id.myCompactView);
        fullImageZoomView = (ZoomableImageView) rootView.findViewById(R.id.fullImageViewZoom);

        fabNext.setOnClickListener(this);
        fabPrevious.setOnClickListener(this);
        mClickerCrutch.setOnClickListener(this);
        fullImageZoomView.setOnClickListener(this);

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                fullImageZoomView.setImageBitmap(bitmap);
                fullImageZoomView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        statusesService.homeTimeline(500, null, null, null, null, null, null,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        mTweetList = listResult.data;
                        myTweetView.setTweet(mTweetList.get(mCounter));
                        myTweetView.setVisibility(View.VISIBLE);
                        if (mCounter == 0) fabPrevious.hide();
                    }

                    @Override
                    public void failure(TwitterException e) {
                    }
                }
        );
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabDown:{
                if (mCounter == 0){
                    fabPrevious.show();
                }
                mCounter++;
                myTweetView.setTweet(mTweetList.get(mCounter));
            }
            break;
            case R.id.fabUp:{
                if (mCounter == 1){
                    fabPrevious.hide();
                }
                mCounter--;
                myTweetView.setTweet(mTweetList.get(mCounter));
            }
            break;
            case R.id.myClickerCrutch:{
                TakePhotoFromTweet(mTweetList.get(mCounter));
            }
            break;
            case R.id.fullImageViewZoom:{
                fullImageZoomView.setVisibility(View.INVISIBLE);



            }
        }

    }

    public void TakePhotoFromTweet (Tweet tweet){
        MediaEntity photoEntity;
        TweetEntities entities = tweet.entities;

        if(entities != null){
            List mediaEntityList = entities.media;
            if(mediaEntityList != null && !mediaEntityList.isEmpty()) {
                for(int i = mediaEntityList.size() - 1; i >= 0; --i) {
                    MediaEntity entity = (MediaEntity)mediaEntityList.get(i);
                    if(entity.type != null && entity.type.equals("photo")) {
                        photoEntity = entity;
                        String photoURL = photoEntity.mediaUrlHttps;
                        Picasso.with(getActivity()).load(photoURL).into(target);
                    }
                }
            }
        }
    }
}
