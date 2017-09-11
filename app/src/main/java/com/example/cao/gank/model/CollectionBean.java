package com.example.cao.gank.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 * 数据库存储实体类
 * 收藏模块中的类对象
 */
@Entity
public class CollectionBean {
    @Id(autoincrement = true)
    private Long id;
    private String title,dec,time,imgUrl,url;

    @Generated(hash = 1682822730)
    public CollectionBean(Long id, String title, String dec, String time,
            String imgUrl, String url) {
        this.id = id;
        this.title = title;
        this.dec = dec;
        this.time = time;
        this.imgUrl = imgUrl;
        this.url = url;
    }
    @Generated(hash = 1423617684)
    public CollectionBean() {
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
