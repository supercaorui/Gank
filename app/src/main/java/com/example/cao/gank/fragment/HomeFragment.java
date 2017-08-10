package com.example.cao.gank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cao.gank.R;
import com.example.cao.gank.adapter.HomeViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class HomeFragment extends Fragment {
    private Context mContext;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment>mList = new ArrayList<>();
    private List<String> tabList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private HomeViewpagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tabList.clear();
        mList.clear();
        tabList.add("Android");
        tabList.add("Ios");
        tabList.add("福利");
        mList.add(new AndroidFragment());
        mList.add(new IosFragment());
        mList.add(new FuliFragment());
        adapter = new HomeViewpagerAdapter(fragmentManager,tabList,mList,mContext);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.home_viewpager);
        tabLayout = view.findViewById(R.id.home_tablayout);
        fragmentManager = getFragmentManager();
    }
}
