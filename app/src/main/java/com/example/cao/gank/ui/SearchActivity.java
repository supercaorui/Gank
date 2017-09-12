package com.example.cao.gank.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cao.gank.R;
import com.example.cao.gank.adapter.SearchAdapter;
import com.example.cao.gank.databasetool.HistoryDao;
import com.example.cao.gank.model.HIstory;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

//搜索界面调到搜索列表界面，用activity的启动模式
public class SearchActivity extends AppCompatActivity implements SearchAdapter.DeleteItem{
    private ImageView img_back,delete;
    private EditText edit;
    private TextView search_bt;
    private FlexboxLayout flex;
    private RecyclerView recycle;
    private String[] hotName;
    private List<HIstory> mList = new ArrayList<>();
    private List<String> mKeyList = new ArrayList<>();
    private HistoryDao historyDao;
    private SearchAdapter searchAdapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            searchAdapter.notifyDataSetChanged();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initFlag();
        initData();
        searchAdapter = new SearchAdapter(mKeyList,this);
        recycle.setAdapter(searchAdapter);
        initListener();
    }

    private void initListener() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //初始化历史记录
    private void initData() {
       mList = historyDao.query();
        for (int i = 0; i < mList.size(); i++) {
            mKeyList.add(mList.get(i).getKeyName());
        }
    }

    //初始化热门搜索
    private void initFlag() {
        hotName = new String[]{"RxJava","RxAndroid","数据库","自定义控件","下拉刷新","mvp","直播","权限管理","Retrofit","OkHttp","WebWiew","热修复"};
        for (int i = 0; i < hotName.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(hotName[i]);
            textView.setBackgroundResource(R.drawable.search_shape);
            textView.setGravity(Gravity.CENTER);
            textView.setClickable(true);
            textView.setFocusable(true);
            textView.setPadding(30,30,30,30);
            textView.setTextColor(getResources().getColor(R.color.secondary_text));
            flex.addView(textView);
            //设置子item之间的距离
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            if (params instanceof FlexboxLayout.LayoutParams) {
                FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
                //layoutParams.setFlexBasisPercent(0.5f);
                layoutParams.setMargins(10, 10, 20, 10);
            }
        }
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.search_back);
        edit = (EditText) findViewById(R.id.search_edit);
        search_bt = (TextView) findViewById(R.id.search_bt);
        delete = (ImageView) findViewById(R.id.search_delete);
        flex = (FlexboxLayout) findViewById(R.id.search_flex);
        recycle= (RecyclerView) findViewById(R.id.search_recycle);
        historyDao = new HistoryDao(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(linearLayoutManager);

    }
    //点击x图片删除该行数据
    @Override
    public void deleteItem(int position) {
        mKeyList.remove(position);
        handler.sendEmptyMessage(1);
    }
}
