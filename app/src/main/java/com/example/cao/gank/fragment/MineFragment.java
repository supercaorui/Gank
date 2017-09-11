package com.example.cao.gank.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cao.gank.R;
import com.example.cao.gank.ui.CollectionActivity;
import com.example.cao.gank.ui.MainActivity;
import com.leon.lib.settingview.LSettingItem;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/1.
 */

public class MineFragment extends Fragment {
    private MainActivity mContext;
    private CircleImageView imageView;
    private TextView name;
    private LSettingItem myblog,version,follow,collection;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        myblog.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {

            }
        });
        version.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {

            }
        });
        follow.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {

            }
        });
        collection.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Intent intent = new Intent(mContext, CollectionActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        imageView = view.findViewById(R.id.iv_header);
        name = view.findViewById(R.id.tv_nickname);
        myblog = view.findViewById(R.id.item_blog);
        version = view.findViewById(R.id.item_version);
        follow = view.findViewById(R.id.item_follow);
        collection = view.findViewById(R.id.item_save);

    }
}
