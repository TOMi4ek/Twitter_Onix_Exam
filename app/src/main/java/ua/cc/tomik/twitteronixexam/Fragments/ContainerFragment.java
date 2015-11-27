package ua.cc.tomik.twitteronixexam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.cc.tomik.twitteronixexam.R;


public class ContainerFragment extends Fragment {
    TimeLineFragment timeLineFragment;
    OneTweetFragment oneTweetFragment;
    FragmentManager fragmentManager;





    public ContainerFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeLineFragment = new TimeLineFragment();
        oneTweetFragment = new OneTweetFragment();
        fragmentManager = getFragmentManager();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_container, container, false);




        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        if (savedInstanceState == null){
            fragmentTransaction.add(R.id.container, timeLineFragment);
        }else{
            fragmentTransaction.replace(R.id.container, timeLineFragment);
        }

            fragmentTransaction.commit();
        return rootView;
    }

    public void setState (Boolean lineState){
        if (lineState){
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(R.id.container, timeLineFragment);
            fragmentTransaction.commit();

        }else{
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(R.id.container, oneTweetFragment);
            fragmentTransaction.commit();
        }
    }


}
