package ua.cc.tomik.twitteronixexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.cc.tomik.twitteronixexam.Fragments.BlankFragment;
import ua.cc.tomik.twitteronixexam.Fragments.MapFragment;
import ua.cc.tomik.twitteronixexam.Fragments.OneTweetFragment;
import ua.cc.tomik.twitteronixexam.Fragments.TimeLineFragment;
import ua.cc.tomik.twitteronixexam.Fragments.UserFragment;

public class MyAdapter extends FragmentPagerAdapter {
    boolean timeLineState;


    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            if (timeLineState) {
                return new TimeLineFragment();
            } else {
                return new OneTweetFragment();
            }
        }else{
            if (position == 1){
                return new UserFragment();
            }else{
                if (position == 2){
                    return new MapFragment();
                }else{
                    if (position == 3){
                        return new BlankFragment();
                    }else{
                        return null;
                    }
                }
            }
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    public void setTimelineState(boolean timelineState) {
        this.timeLineState = timelineState;
    }
}