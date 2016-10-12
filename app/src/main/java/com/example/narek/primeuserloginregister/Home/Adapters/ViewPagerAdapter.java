package com.example.narek.primeuserloginregister.Home.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.narek.primeuserloginregister.Home.Fragments.NewsFragment.NewsFragment;
import com.example.narek.primeuserloginregister.Home.Fragments.TransactionFragment;
import com.example.narek.primeuserloginregister.Home.Fragments.SettingsFragment;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;



    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }


    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            NewsFragment bonus = new NewsFragment();
            return bonus;
        }
        if(position == 1){

            TransactionFragment payment = new TransactionFragment();
            return payment;
        }
        else
        {
            SettingsFragment settings = new SettingsFragment();
            return settings;
        }


    }



    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }



    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}