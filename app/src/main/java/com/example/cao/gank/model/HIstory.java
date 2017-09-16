package com.example.cao.gank.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/9/4.
 * 搜索的历史记录
 */
@Entity
public class HIstory {
    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private Long id;
    private String keyName;
    @Generated(hash = 1668228049)
    public HIstory(Long id, String keyName) {
        this.id = id;
        this.keyName = keyName;
    }

    @Generated(hash = 590567575)
    public HIstory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
