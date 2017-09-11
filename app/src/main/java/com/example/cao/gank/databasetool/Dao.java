package com.example.cao.gank.databasetool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cao.gank.model.CollectionBean;
import com.usher.greendao_demo.greendao.gen.CollectionBeanDao;
import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class Dao {
    private CollectionBeanDao collectionBeanDao;
    private long id ;
    private int count = 0;
    private Context context;
    private String TAG = "daoben";

    public Dao(Context context) {
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
//        id = id +1;
//        count++;
        Log.d(TAG, "insert: "+bean.getId());
//        Log.d(TAG, "count: "+count);
        collectionBeanDao.insert(bean);
    }
    public void delete(CollectionBean id){
        //id++;
        collectionBeanDao.delete(id);
    }
    public  List<CollectionBean> query(){
        //id++;
        QueryBuilder<CollectionBean> qb = collectionBeanDao.queryBuilder();
        List<CollectionBean> list = qb.list();
        return list;
    }
}
