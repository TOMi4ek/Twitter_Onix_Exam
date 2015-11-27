package ua.cc.tomik.twitteronixexam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.cc.tomik.twitteronixexam.Fragments.BlankFragment;
import ua.cc.tomik.twitteronixexam.Fragments.ContainerFragment;
import ua.cc.tomik.twitteronixexam.Fragments.MapFragment;
import ua.cc.tomik.twitteronixexam.Fragments.OneTweetFragment;
import ua.cc.tomik.twitteronixexam.Fragments.TimeLineFragment;
import ua.cc.tomik.twitteronixexam.Fragments.UserFragment;

public class MyAdapter extends FragmentPagerAdapter {
    boolean timeLineState;
    ContainerFragment containerFragment = new ContainerFragment();


    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: {
                return containerFragment;
            }
            case 1: return new UserFragment();
            case 2: return new MapFragment();
            case 3: return new BlankFragment();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    public void setTimelineState(boolean timelineState) {
        this.timeLineState = timelineState;
        containerFragment.setState(timelineState);
    }
}