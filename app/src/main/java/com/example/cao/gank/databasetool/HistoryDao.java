package com.example.cao.gank.databasetool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.cao.gank.model.HIstory;
import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DaoSession;
import com.usher.greendao_demo.greendao.gen.HIstoryDao;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class HistoryDao {
    private Context mContext;
    private HIstoryDao hIstoryDao;
    public HistoryDao(Context mContext) {
        this.mContext = mContext;
        initDa0();
    }

    private void initDa0() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "history", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        hIstoryDao = daoSession.getHIstoryDao();
    }
    public void insert(HIstory history){
        hIstoryDao.insertOrReplace(history);
    }
    public void delete(Long id){
        hIstoryDao.deleteByKey(id);

    }
    public List<HIstory> query(){
        return hIstoryDao.queryBuilder().list();
    }
    public void deleteAll(){
        hIstoryDao.deleteAll();
    }
    //查询是否重复
    public boolean isDouble(String string){

        List<HIstory> list = hIstoryDao.queryBuilder().list();
        for (int i = 0; i < list.size(); i++) {
            HIstory hIstory = list.get(i);
            if (string.equals(hIstory.getKeyName())){
                return true;
            }
        }
        return false;
    }
}
