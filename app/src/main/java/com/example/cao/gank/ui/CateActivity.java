package com.example.cao.gank.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.example.cao.gank.R;
import com.example.cao.gank.adapter.ReadAdapter;
import com.example.cao.gank.congiure.GankUrl;
import com.example.cao.gank.http.HttpImpl;
import com.example.cao.gank.listener.EndLessOnScrollListener;
import com.example.cao.gank.model.ItemBean;
import com.example.cao.gank.model.ReadBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CateActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private String cate;
    private int page = 1;
    private ReadAdapter readAdapter;
    private LinearLayoutManager manager;
    private List<ItemBean.ResultsBean> mlist = new ArrayList<>();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){

                mlist.clear();
                initdata();
                //readAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }else if (msg.what==2){
                initdata();
                //readAdapter.notifyDataSetChanged();
            }

        }
    };
    private SwipeRefreshLayout refreshLayout;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);
        initView();
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.menu_action_back);
        initdata();
        initCate();
    }
    private void initCate() {
        if (mlist == null) {

        }
        readAdapter = new ReadAdapter(mContext, mlist);
        recycler.setAdapter(readAdapter);
        recycler.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                page++;
                mHandler.sendEmptyMessage(2);
            }
        });
    }

    private void initdata() {
        Intent intent = getIntent();
        cate = intent.getStringExtra("cate");
        HttpImpl.loadCategory(cate, GankUrl.PAGESIZE, page, new Callback<ItemBean>() {
            @Override
            public void onResponse(Call<ItemBean> call, Response<ItemBean> response) {
                ItemBean itemBean = response.body();
                //Log.d(TAG, "onResponse: "+itemBean);
                mlist.addAll(itemBean.getResults());
                readAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ItemBean> call, Throwable t) {
                ToastUtils.showShortSafe("服务器异常，请稍后再试");
            }
        });
    }

    private void initView() {
        mContext = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.cate_toolbar);
        recycler = (RecyclerView) findViewById(R.id.cate_recycle);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.cate_refresh);
        refreshLayout.setColorSchemeColors(Color.RED);
        manager = new LinearLayoutManager(mContext);
        recycler.setLayoutManager(manager);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                mHandler.sendEmptyMessage(1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}
