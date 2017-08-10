package com.example.cao.gank.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cao.gank.R;

public class InfoActivity extends AppCompatActivity {

    private WebView webView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initview();
        initWebView();
    }

    private void initview() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_like:
                break;
            case R.id.menu_share:
                break;
            default:
                break;
        }
        return true;
    }
}
