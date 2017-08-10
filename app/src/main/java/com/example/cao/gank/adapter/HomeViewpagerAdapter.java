package com.example.cao.gank.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class HomeViewpagerAdapter extends FragmentPagerAdapter {
    private List<String> tabList = new ArrayList<>();
    private List<Fragment> mlist = new ArrayList<>();
    private Context context;
    FragmentManager fm;
    public HomeViewpagerAdapter(FragmentManager fm,List<String> tabList, List<Fragment> mlist, Context context) {
        super(fm);
        this.mlist = mlist;
        this.context = context;
        this.tabList = tabList;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Fragment fragment = mlist.get(position);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(fragment);
    }

}
