package com.example.ns;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new Tab1();
        }
        if (position == 1) {
            return new Tab2();
        }
        if (position != 2) {
            return null;
        }
        return new Tab3();
    }

    public int getCount() {
        return this.mNoOfTabs;
    }
}
