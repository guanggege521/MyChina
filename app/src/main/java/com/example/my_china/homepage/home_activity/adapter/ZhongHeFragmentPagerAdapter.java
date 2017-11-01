package com.example.my_china.homepage.home_activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 吴肖光 on 2017/11/1.
 */
public class ZhongHeFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<String> title;
    ArrayList<Fragment> fragment;

    public ZhongHeFragmentPagerAdapter(FragmentManager fm, ArrayList<String> title, ArrayList<Fragment> fragment) {
        super(fm);
        this.title = title;
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
