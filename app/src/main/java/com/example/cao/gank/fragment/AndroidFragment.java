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
import com.example.cao.gank.adapter.ReadAdapter;
import com.example.cao.gank.congiure.GankUrl;
import com.example.cao.gank.http.HttpImpl;
import com.example.cao.gank.listener.EndLessOnScrollListener;
import com.example.cao.gank.model.ItemBean;
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

public class AndroidFragment extends Fragment {
    private Context mContext;
    private int page = 1;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<ItemBean.ResultsBean> mlist = new ArrayList<>();
    private LinearLayoutManager manager;
    private ReadAdapter readAdapter;
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
    private String TAG = "caorui";
    private ProgressBar progressBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
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
        readAdapter = new ReadAdapter(mContext,mlist);
        recyclerView.setAdapter(readAdapter);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                page++;
                mHandler.sendEmptyMessage(2);
            }
        });

        readAdapter.setOnItemClickListener(new ReadAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, ItemBean.ResultsBean data) {
                String url = data.getUrl();
                Intent intent = new Intent(mContext, InfoActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",data.getDesc());
                intent.putExtra("author",data.getWho());
                intent.putExtra("time",data.getPublishedAt());
                if (data.getImages()!=null){

                    intent.putExtra("imgurl",data.getImages().get(0));
                }else {
                    intent.putExtra("imgurl","");
                }
                mContext.startActivity(intent);
            }
        });
    }

    private void initdata() {
        HttpImpl.loadCategory(GankUrl.Android, GankUrl.PAGESIZE, page, new Callback<ItemBean>() {
            @Override
            public void onResponse(Call<ItemBean> call, Response<ItemBean> response) {
                ItemBean itemBean = response.body();
                Log.d(TAG, "onResponse: "+itemBean);
                mlist.addAll(itemBean.getResults());
                readAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ItemBean> call, Throwable t) {
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
