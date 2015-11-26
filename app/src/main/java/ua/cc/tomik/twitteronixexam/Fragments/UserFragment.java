package ua.cc.tomik.twitteronixexam.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

import ua.cc.tomik.twitteronixexam.R;


public class UserFragment extends Fragment {
    ImageView profileImage,bannerImage;
    String profileImageURL, bannerURL;
    TextView nameTextView, loginTextView;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        profileImage =(ImageView) rootView.findViewById(R.id.profileImageView);
        bannerImage = (ImageView) rootView.findViewById(R.id.bannerImageView);
        nameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
        loginTextView = (TextView) rootView.findViewById(R.id.loginTextView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Twitter.getApiClient().getAccountService().verifyCredentials(true, true, new Callback<User>() {
            @Override
            public void success(Result<User> result) {
                profileImageURL = result.data.profileImageUrl;
                bannerURL = result.data.profileBannerUrl;
                Picasso.with(getActivity()).load(profileImageURL).into(profileImage);
                Picasso.with(getActivity()).load(bannerURL).into(bannerImage);
                nameTextView.setText(result.data.name);
                loginTextView.setText("@"+ result.data.screenName);
            }

            @Override
            public void failure(TwitterException e) {

            }
        });
    }
}
