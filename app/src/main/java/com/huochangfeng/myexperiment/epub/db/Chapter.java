package com.huochangfeng.myexperiment.epub.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <br><br>
 * 作者：霍昌峰 on 2016/7/11 16:01<p>
 * 邮箱：553805864@qq.com<p>
 */
@Entity
public class Chapter {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String progress;
    public String getProgress() {
        return this.progress;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1426803253)
    public Chapter(Long id, String name, String progress) {
        this.id = id;
        this.name = name;
        this.progress = progress;
    }
    @Generated(hash = 393170288)
    public Chapter() {
    }
}