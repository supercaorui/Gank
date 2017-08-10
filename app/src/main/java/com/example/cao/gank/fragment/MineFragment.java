package com.example.cao.gank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cao.gank.R;
import com.example.cao.gank.ui.MainActivity;

/**
 * Created by Administrator on 2017/8/1.
 */

public class MineFragment extends Fragment {
    private MainActivity mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);

        return view;
    }
}
