package com.huochangfeng.myexperiment.epub.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 作者：霍昌峰 on 2016/7/8 13:41<p>
 * 邮箱：553805864@qq.com<p>
 */
public class DBManager {
    private final static String dbName = "book_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context 上下文
     * @return DBManager
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        return openHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        return openHelper.getWritableDatabase();
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(BookInfo user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        BookInfoDao userDao = daoSession.getBookInfoDao();
//        userDao.insert(user);
        userDao.insertOrReplace(user);
    }

    public DaoSession insert() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession;
    }

    /**
     * 查询用户列表
     */
    public List<BookInfo> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        BookInfoDao userDao = daoSession.getBookInfoDao();
        QueryBuilder<BookInfo> qb = userDao.queryBuilder();
        List<BookInfo> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public QueryBuilder<BookInfo> query() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        BookInfoDao userDao = daoSession.getBookInfoDao();
        return userDao.queryBuilder();
    }
}
