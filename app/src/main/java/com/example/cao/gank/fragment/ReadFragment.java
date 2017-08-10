package com.example.cao.gank.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.cao.gank.R;
import com.example.cao.gank.adapter.Read2Adapter;
import com.example.cao.gank.congiure.GankUrl;
import com.example.cao.gank.http.HttpImpl;
import com.example.cao.gank.listener.EndLessOnScrollListener;
import com.example.cao.gank.model.ItemBean;
import com.example.cao.gank.model.ReadBean;
import com.example.cao.gank.ui.InfoActivity;
import com.example.cao.gank.ui.MainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/1.
 */

public class ReadFragment extends Fragment {
    private Context mContext;
    private int page = 1;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<ReadBean.NewslistBean> mlist = new ArrayList<>();
    private LinearLayoutManager manager;
    private Read2Adapter Read2Adapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){

                mlist.clear();
                initdata();
                //Read2Adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }else if (msg.what==2){
                initdata();
                //Read2Adapter.notifyDataSetChanged();
            }

        }
    };
    private String TAG = "caorui";
    private ProgressBar progressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,container,false);
        initView(view);
        initdata();

        initRead();
        return view;
    }

    private void initRead() {
        if (mlist==null){

        }
        Read2Adapter = new Read2Adapter(mContext,mlist);
        recyclerView.setAdapter(Read2Adapter);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                page++;
                mHandler.sendEmptyMessage(2);
            }
        });
        //每个item的点击事件
        Read2Adapter.setOnItemClickListener(new Read2Adapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, ReadBean.NewslistBean data) {
                String url = data.getUrl();
                Intent intent = new Intent(mContext, InfoActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",data.getTitle());
                intent.putExtra("author",data.getDescription());
                intent.putExtra("time",data.getCtime());
                if (data.getPicUrl()!=null){

                    intent.putExtra("imgurl",data.getPicUrl());
                }else {
                    intent.putExtra("imgurl","");
                }
                mContext.startActivity(intent);
            }

        });
    }

    private void initdata() {
        HttpImpl.loadRead(GankUrl.APIKEY, 10, page, new Callback<ReadBean>() {
            @Override
            public void onResponse(Call<ReadBean> call, Response<ReadBean> response) {
                ReadBean itemBean = response.body();
                Log.d(TAG, "onResponse: "+itemBean);
                mlist.addAll(itemBean.getNewslist());
                Read2Adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ReadBean> call, Throwable t) {
                ToastUtils.showShortSafe("服务器异常，请稍后再试");
            }

        });

    }

    private void initView(View view) {
        if (mlist==null){
            progressBar = new ProgressBar(mContext);
            progressBar.setVisibility(View.VISIBLE);
        }

        recyclerView = view.findViewById(R.id.read_recyclerview);
        refreshLayout = view.findViewById(R.id.read_swip_refresh);
        refreshLayout.setColorSchemeColors(Color.RED);
        manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                mHandler.sendEmptyMessage(1);
            }
        });
    }
}
