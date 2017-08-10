package com.example.cao.gank.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private List<Fragment>mlist = new ArrayList<>();
    private Context context;
    public MainAdapter(FragmentManager fm,List<Fragment> mlist,Context context) {
        super(fm);
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
