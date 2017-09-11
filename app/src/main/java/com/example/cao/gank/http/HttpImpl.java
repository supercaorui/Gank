package com.example.cao.gank.http;

import com.example.cao.gank.congiure.GankUrl;
import com.example.cao.gank.model.ItemBean;
import com.example.cao.gank.model.ReadBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/2.
 */

public class HttpImpl {
    public static void loadCategory(String category, String pageSize, int page, Callback<ItemBean> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestHttp requestHttp = retrofit.create(RequestHttp.class);
        Call<ItemBean> categoryData = requestHttp.getCategoryData(category, pageSize, page);
        categoryData.enqueue(callback);
    }
    public static void loadRead(String key, int pageSize, int page, Callback<ReadBean> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankUrl.BASE_URL_READ)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestHttp requestHttp = retrofit.create(RequestHttp.class);
        Call<ReadBean> readData = requestHttp.getReadData(key, pageSize, page);
        readData.enqueue(callback);
    }
    //加载搜索页面
    public static void loadSearch(String searchKey,int page,Callback<ItemBean> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestHttp http = retrofit.create(RequestHttp.class);
        Call<ItemBean> searchData = http.getSearchData(searchKey, page);
        searchData.enqueue(callback);
    }
}
