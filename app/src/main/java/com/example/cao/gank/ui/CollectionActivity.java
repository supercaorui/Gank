package com.example.cao.gank.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.cao.gank.R;
import com.example.cao.gank.adapter.CollectionAdapter;
import com.example.cao.gank.base.BaseActivity;
import com.example.cao.gank.databasetool.Dao;
import com.example.cao.gank.model.CollectionBean;
import com.example.cao.gank.model.ItemBean;

import java.util.List;


public class CollectionActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private Dao dao;
    private List<CollectionBean> mList;
    private CollectionAdapter collectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        initdata();
    }

    private void initdata() {
        mList = dao.query();
        collectionAdapter = new CollectionAdapter(this,mList);
        recycler.setAdapter(collectionAdapter);
        collectionAdapter.setOnItemClickListener(new CollectionAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, CollectionBean data) {

                Intent intent = new Intent(CollectionActivity.this, InfoActivity.class);
                intent.putExtra("url",data.getUrl());
                intent.putExtra("title",data.getTitle());
                intent.putExtra("author",data.getDec());
                intent.putExtra("time",data.getTime());
                if (data.getImgUrl()!=null){

                    intent.putExtra("imgurl",data.getImgUrl());
                }else {
                    intent.putExtra("imgurl","");
                }
                CollectionActivity.this.startActivity(intent);
            }

//            @Override
//            public void onItemClick(View view, ItemBean.ResultsBean data) {
//                Intent intent = new Intent(CollectionActivity.this, InfoActivity.class);
//                intent.putExtra("url",data.getUrl());
//                intent.putExtra("title",data.getDesc());
//                intent.putExtra("author",data.getWho());
//                intent.putExtra("time",data.getPublishedAt());
//                if (data.getImages()!=null){
//
//                    intent.putExtra("imgurl",data.getImages().get(0));
//                }else {
//                    intent.putExtra("imgurl","");
//                }
//                CollectionActivity.this.startActivity(intent);
//            }
       });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.collect_toolbar);
        recycler = (RecyclerView) findViewById(R.id.collect_recycler);
        dao = new Dao(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.menu_action_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}
