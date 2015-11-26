package ua.cc.tomik.twitteronixexam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String helpUrl = "http://onix-systems.com";
    ImageButton homeButton, mapButton, userButton, settingsButton, switchButton, menuButton;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    MyAdapter mAdapter;
    ViewPager pager;
    public TextView tabName;
    String[] tabNames = {"Home", "User Info", "Map", "Blank Page"};
    boolean homeTimeLineState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeButton = (ImageButton) findViewById(R.id.button_home);
        mapButton = (ImageButton) findViewById(R.id.button_map);
        userButton = (ImageButton) findViewById(R.id.button_user);
        settingsButton = (ImageButton) findViewById(R.id.button_settings);
        switchButton = (ImageButton) findViewById(R.id.switchHomeButton);
        menuButton = (ImageButton) findViewById(R.id.menuButton);
        tabName = (TextView) findViewById(R.id.tabNameTextView);

        mapButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        userButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        switchButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.menu_help: {
                                Intent helpintent = new Intent(Intent.ACTION_VIEW);
                                helpintent.setData(Uri.parse(helpUrl));
                                startActivity(helpintent);
                            }
                            break;
                            case R.id.menu_logout:
                                Twitter.getSessionManager().clearActiveSession();
                                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                                logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(logout);
                                break;
                            case R.id.menu_skills:
                                break;
                        }
                        closeNavDrawer();
                        return true;
                    }
                }
        );

        mAdapter = new MyAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(mAdapter);
        pager.setCurrentItem(0);
        tabName.setText(tabNames[0]);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabName.setText(tabNames[position]);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_map: {
                Toast.makeText(this, "map button", Toast.LENGTH_SHORT).show();
                pager.setCurrentItem(2);
            }
            break;
            case R.id.button_home: {
                Toast.makeText(this, "home button", Toast.LENGTH_SHORT).show();
                pager.setCurrentItem(0);
            }
            break;
            case R.id.button_user: {
                Toast.makeText(this, "user button", Toast.LENGTH_SHORT).show();
                pager.setCurrentItem(1);
            }
            break;
            case R.id.button_settings: {
                Toast.makeText(this, "settings button", Toast.LENGTH_SHORT).show();
                pager.setCurrentItem(3);
            }
            break;
            case R.id.switchHomeButton: {
                if (homeTimeLineState){
                    homeTimeLineState = false;
                    mAdapter.setTimelineState(homeTimeLineState);
                    pager.setAdapter(mAdapter);
                    switchButton.setImageResource(R.drawable.ic_view_list);

                }else{
                    homeTimeLineState = true;
                    mAdapter.setTimelineState(homeTimeLineState);
                    pager.setAdapter(mAdapter);
                    switchButton.setImageResource(R.drawable.ic_file);
                }
                pager.setCurrentItem(0);
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void closeNavDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
    }

    private boolean isNavDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.END);
    }


}
