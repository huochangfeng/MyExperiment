package com.huochangfeng.myexperiment.epub.db;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 作者：霍昌峰 on 2016/7/8 13:24<p>
 * 邮箱：553805864@qq.com<p>
 */
@Entity
public class BookInfo {
    @Id
    private long id;
    private String name;
    private String author;
    private String progress;
    @ToOne(joinProperty = "id")
    private Chapter c;
    /** Used for active entity operations. */
    @Generated(hash = 56468943)
    private transient BookInfoDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    @Generated(hash = 265055626)
    private transient Long c__resolvedKey;
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getProgress() {
        return this.progress;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1863671907)
    public void setC(@NotNull Chapter c) {
        if (c == null) {
            throw new DaoException(
                    "To-one property 'id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.c = c;
            id = c.getId();
            c__resolvedKey = id;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2027956455)
    public Chapter getC() {
        long __key = this.id;
        if (c__resolvedKey == null || !c__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChapterDao targetDao = daoSession.getChapterDao();
            Chapter cNew = targetDao.load(__key);
            synchronized (this) {
                c = cNew;
                c__resolvedKey = __key;
            }
        }
        return c;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 472309544)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookInfoDao() : null;
    }
    @Generated(hash = 1381597889)
    public BookInfo(long id, String name, String author, String progress) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.progress = progress;
    }
    @Generated(hash = 1952025412)
    public BookInfo() {
    }
}
