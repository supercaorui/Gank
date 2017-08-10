package com.example.cao.gank.DatabaseTool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.cao.gank.model.CollectionBean;
import com.example.cao.gank.model.ItemBean;
import com.usher.greendao_demo.greendao.gen.CollectionBeanDao;
import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class Dao {
    private CollectionBeanDao collectionBeanDao;
    private long id;
    private Context context;
    public Dao(CollectionBeanDao collectionBeanDao ,Context context) {
        this.collectionBeanDao = collectionBeanDao;
        this.context = context;
        initDao();
    }

    private void initDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "collection", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        collectionBeanDao = daoSession.getCollectionBeanDao();
    }

    public void insert(CollectionBean bean){
        id++;
        collectionBeanDao.insert(bean);
    }
    public void delete(long id){
        id++;
        collectionBeanDao.deleteByKey(id);
    }
    public  List<CollectionBean> query(){
        id++;
        QueryBuilder<CollectionBean> qb = collectionBeanDao.queryBuilder();
        List<CollectionBean> list = qb.list();
        return list;
    }
}
