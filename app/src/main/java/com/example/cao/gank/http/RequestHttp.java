package com.example.cao.gank.http;

/**
 * Created by Administrator on 2017/8/2.
 */

import com.example.cao.gank.model.ItemBean;
import com.example.cao.gank.model.ReadBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 获取分类数据
 * 示例：http://gank.io/api/data/Android/10/1
 */
public interface RequestHttp {
    /**
     *  @param category 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param pageSize 请求个数： 数字，大于0
     * @param page     第几页：数字，大于0
     * @return
     */
    @GET("data/{category}/{pageSize}/{page}")
    Call<ItemBean> getCategoryData(@Path("category")String category,
                                   @Path("pageSize") String pageSize,
                                   @Path("page") int page);
    //read模块
    @GET("mobile/")
    Call<ReadBean>getReadData(@Query("key") String key,
                              @Query("num") int num,
                              @Query("page") int page);
    //搜索模块
    @GET("search/query/{searchKey}/category/all/count/10/page/{page}")
    Call<ItemBean> getSearchData(@Path("searchKey") String searchkey,
                                        @Path("page") int page);

}
