package com.nearwander.application.nearwander.tabbed;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;


import com.google.android.gms.common.api.GoogleApiClient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.nearwander.application.nearwander.MainActivity;
import com.nearwander.application.nearwander.R;
import com.nearwander.application.nearwander.chat.ChatActivity;
import com.nearwander.application.nearwander.tabbed.Settings.SettingsActivity;

public class HomePageActivity extends AppCompatActivity{

    //To add Icons to tabs
    /*in MainActivity.java file after tabLayout.setupWithViewPager(mViewPager);
    add below lines: tabLayout.getTabAt(0).setIcon(R.drawable.image_name_1); tabLayout.getTabAt(1).setIcon(R.drawable.image_name_2); tabLayout.getTabAt(2).setIcon(R.drawable.image_name_3);*/

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "HomePage_Activity";
    //private Button mButtonMessage;
    private FloatingActionButton mButtonMessage;

    /*private FirebaseAuth mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mButtonMessage = (FloatingActionButton) findViewById(R.id.fab);
        mButtonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication().getApplicationContext(), ChatActivity.class);
                startActivity(i);
            }
        });


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_timeline_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_event_note_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_notifications_black_24dp);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_account_circle_black_24dp);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        }else if (id == R.id.sign_out_menu){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Deleted PlaceholderFragment class from here

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //returning the current tabs
            switch (position){
                case 0:
                    Tab1Home tab1 = new Tab1Home();
                    return tab1;
                case 1:
                    Tab2Timeline tab2 = new Tab2Timeline();
                    return tab2;
                case 2:
                    Tab3MyPlans tab3 = new Tab3MyPlans();
                    return tab3;
                case 3:
                    Tab4Notification tab4 = new Tab4Notification();
                    return tab4;
                case 4:
                    Tab5Profile tab5 = new Tab5Profile();
                    return tab5;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Homepage";
                case 1:
                    return "Timeline";
                case 2:
                    return "My Plans";
                case 3:
                    return "Notification";
                case 4:
                    return "Profile";
            }
            return null;
        }*/
    }
}
