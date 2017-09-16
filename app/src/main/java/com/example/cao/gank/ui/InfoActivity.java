package com.example.cao.gank.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cao.gank.R;
import com.example.cao.gank.base.BaseActivity;
import com.example.cao.gank.databasetool.Dao;
import com.example.cao.gank.model.CollectionBean;

import java.util.List;

public class InfoActivity extends BaseActivity {

    private WebView webView;
    private Toolbar toolbar;
    private String url;
    private CollectionBean collectionBean;
    private Dao dao;
    private boolean isLike = false;
    private boolean isCollected;
    private String TAG = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initview();
        initWebView();
    }
    private Long id ;
    private void initview() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String time = intent.getStringExtra("time");
        String imgurl = intent.getStringExtra("imgurl");

        collectionBean = new CollectionBean(id,title,author,time,imgurl,url);
       // Log.d(TAG, "id: "+id);
        dao = new Dao(this);
        isCollected = isCollected();
        if (isCollected()){
            isLike = true;

        }else {
            isLike = false;
        }
        webView = (WebView) findViewById(R.id.webview);
        toolbar = (Toolbar) findViewById(R.id.info_toolbar);
        webView.loadUrl(url);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu_action_back);
        }
    }
    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        //支持js脚本
        webSettings.setJavaScriptEnabled(true);
        //支持缩放
        webSettings.setSupportZoom(true);
        //支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //多窗口
        webSettings.supportMultipleWindows();
        //当webview调用requestFocus时为webview设置节点
        webSettings.setNeedInitialFocus(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //优先使用缓存:
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //提高渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 开启H5(APPCache)缓存功能
        webSettings.setAppCacheEnabled(true);
        // 开启 DOM storage 功能
        webSettings.setDomStorageEnabled(true);
        // 应用可以有数据库
        webSettings.setDatabaseEnabled(true);
        // 可以读取文件缓存(manifest生效)
        webSettings.setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //网页加载完成
                    view.loadUrl("javascript:function setTop(){document.querySelector('collapse navbar-collapse').style.display=\"none\";}setTop();");
                }
            }
        });
    }

    //处理返回键，浏览器按返回键不是直接关闭
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK&& webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info,menu);
        MenuItem item = menu.findItem(R.id.menu_like);
        isCollected = isCollected();
        if (isCollected()){
            isLike = true;
            item.setIcon(R.drawable.likered);
        }else {
            isLike = false;
            item.setIcon(R.drawable.like);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_like:
                if (!isLike){
                    dao.insert(collectionBean);
                    item.setIcon(R.drawable.likered );
                    isLike = true;
                }else {
                    dao.delete(collectionBean);
                    item.setIcon(R.drawable.like);
                    isLike = false;
                }
                break;
            case R.id.menu_share:
                break;
            default:
                break;
        }
        return true;
    }
    //判断是否已经收藏此文章
    public boolean isCollected(){
        List<CollectionBean> list = dao.query();
        for (int i = 0; i < list.size(); i++) {
            if (url.equals(list.get(i).getUrl())){
                return true;
            }
        }
        return false;
    }


}
