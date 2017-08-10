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
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/1.
 */

public class ReadCopyFragment extends Fragment {
    private Context mContext;
    private int page = 1;
   // private SwipeRefreshLayout refreshLayout;
    private LRecyclerView recyclerView;
    private List<ReadBean.NewslistBean> mlist = new ArrayList<>();
    private LinearLayoutManager manager;
    private Read2Adapter Read2Adapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                page=1;
                mlist.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                initdata();
                //Read2Adapter.notifyDataSetChanged();
                //refreshLayout.setRefreshing(false);
                recyclerView.refreshComplete(10);
            }else if (msg.what==2){
                initdata();
                recyclerView.refreshComplete(10);
                //Read2Adapter.notifyDataSetChanged();
            }

        }
    };
    private String TAG = "caorui";
    private ProgressBar progressBar;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_copyread,container,false);
        initView(view);
        initdata();

        initRead();
        return view;
    }

    private void initRead() {
        if (mlist==null){

        }

        recyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
            @Override
            public void reload() {
                initdata();
            }
        });
//        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                page++;
//                mHandler.sendEmptyMessage(2);
//            }
//        });

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = mlist.get(position).getUrl();
                Intent intent = new Intent(mContext, InfoActivity.class);
                intent.putExtra("url",url);
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
                lRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ReadBean> call, Throwable t) {
                //ToastUtils.showShortSafe("服务器异常，请稍后再试");
            }
        });

    }

    private void initView(View view) {
        if (mlist==null){
            progressBar = new ProgressBar(mContext);
            progressBar.setVisibility(View.VISIBLE);
        }

        recyclerView = view.findViewById(R.id.copyread_recyclerview);
        Read2Adapter = new Read2Adapter(mContext,mlist);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(Read2Adapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
//        refreshLayout = view.findViewById(R.id.read_swip_refresh);
//        refreshLayout.setColorSchemeColors(Color.RED);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新Progress的样式
        recyclerView.setArrowImageView(R.drawable.menu_action_download);  //设置下拉刷新箭头
        manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessage(1);
            }
        });
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                mHandler.sendEmptyMessage(2);
            }
        });
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                page=1;
//                mHandler.sendEmptyMessage(1);
//            }
//        });
    }
}
