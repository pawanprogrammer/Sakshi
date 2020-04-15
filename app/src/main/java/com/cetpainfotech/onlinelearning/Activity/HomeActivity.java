package com.cetpainfotech.onlinelearning.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cetpainfotech.onlinelearning.Authentication.LoginActivity;
import com.cetpainfotech.onlinelearning.Chat.UserActivity;
import com.cetpainfotech.onlinelearning.Chat.UserDetails;
import com.cetpainfotech.onlinelearning.Fragment.AndroidFragment;
import com.cetpainfotech.onlinelearning.R;
import com.cetpainfotech.onlinelearning.Helper.SlidingTabLayout;
import com.cetpainfotech.onlinelearning.Adapter.ViewPagerAdapter;


/**
 * Created by pankaj on 07-05-2016.
 */
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Android", "Java Tech", "Php Web", "Ios Apps", "Iot Tech", "Python", "Dotnet","Notes No", "InterView Ques"};
    int Numboftabs = 9;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide the status bar
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_home);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.color_black);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
        navigationDrawerAndCollapsingToolbar();
    }


    private void navigationDrawerAndCollapsingToolbar() {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        View headerview=navigationView.getHeaderView(0);
        ImageView iv_profile = headerview.findViewById(R.id.edit);
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        TextView name = headerview.findViewById(R.id.nav_name);
        TextView email = headerview.findViewById(R.id.nav_email);
        SharedPreferences sp = getSharedPreferences("profile", 0);
        String sname = sp.getString("name", UserDetails.username);
        String semail = sp.getString("email", null);
        name.setText(sname);
        email.setText(semail);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Interview/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_share:
                shareAppD();
                break;
            case R.id.action_chat:
                Intent chat=new Intent(getApplicationContext(), UserActivity.class);
                startActivity(chat);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Fragment fragment = null;
                fragment = new AndroidFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame, fragment).commit();

                break;

            case R.id.nav_aboutus:
                Intent about = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.nav_feedback:
                Intent feedback = new Intent(HomeActivity.this, FeedbackActivity.class);
                startActivity(feedback);
                break;
            case R.id.nav_share:
                shareAppD();
                break;

            case R.id.nav_contactus:
                Intent contact = new Intent(HomeActivity.this, ContactActivity.class);
                startActivity(contact);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareAppD() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent
                .putExtra(Intent.EXTRA_TEXT,
                        "I am using "
                                + getString(R.string.app_name)
                                + " App ! Why don't you try it out...\nInstall "
                                + getString(R.string.app_name)
                                + " now !\nhttps://play.google.com/store/apps/details?id="
                                + getPackageName());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) + " App !");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share App"));
    }
}