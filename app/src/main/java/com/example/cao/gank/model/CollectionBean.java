package com.example.cao.gank.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 * 数据库存储实体类
 * 收藏模块中的类对象
 */
@Entity
public class CollectionBean {
    @Id
    private long id;
    private String title,dec,time,imgUrl;
    @Generated(hash = 1423617684)
    public CollectionBean() {
    }
    @Generated(hash = 234522750)
    public CollectionBean(long id, String title, String dec, String time, String imgUrl) {
        this.id = id;
        this.title = title;
        this.dec = dec;
        this.time = time;
        this.imgUrl = imgUrl;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
