package com.cetpainfotech.onlinelearning.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cetpainfotech.onlinelearning.Fragment.AndroidFragment;
import com.cetpainfotech.onlinelearning.Fragment.DotnetFragment;
import com.cetpainfotech.onlinelearning.Fragment.InterviewFragment;
import com.cetpainfotech.onlinelearning.Fragment.IosFragment;
import com.cetpainfotech.onlinelearning.Fragment.IotFragment;
import com.cetpainfotech.onlinelearning.Fragment.JavaFragment;
import com.cetpainfotech.onlinelearning.Fragment.NotesFragment;
import com.cetpainfotech.onlinelearning.Fragment.PhpFragment;
import com.cetpainfotech.onlinelearning.Fragment.PythonFragment;


/**
 * Created by abhisharma on 07-08-2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }
    @Override
    public Fragment getItem(int position) {
        if(position == 0) // if the position is 0 we are returning the First tab
        {
            AndroidFragment tab1 = new AndroidFragment();
            return tab1;
        }

        else if (position==1){
            JavaFragment tab2 = new JavaFragment();
            return tab2;
        }
        else if (position==2){
            PhpFragment tab2 = new PhpFragment();
            return tab2;
        }
        else if (position==3){
            IosFragment tab2 = new IosFragment();
            return tab2;
        }
        else if (position==4){
            IotFragment tab2 = new IotFragment();
            return tab2;
        }
        else if (position==5){
            PythonFragment tab2 = new PythonFragment();
            return tab2;
        }
        else if (position==6){
            DotnetFragment tab=new DotnetFragment();
            return tab;
        }
        else if (position == 7){
            NotesFragment notesFragment=new NotesFragment();
            return notesFragment;
        }
        else {
            InterviewFragment notesFragment=new InterviewFragment();
            return notesFragment;
        }
        /*else if (position==3){
            LiveShows tab2 = new LiveShows();
            return tab2;
        }
        else {
            Movies tab3 = new Movies();
            return tab3;
        }*/
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount()
    {
        return NumbOfTabs;
    }
}
