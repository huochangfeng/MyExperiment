package com.huochangfeng.myexperiment.application;

import android.app.Application;

import com.huochangfeng.myexperiment.BuildConfig;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * 加载全局设置
 *
 * @author 霍昌峰
 * @date 2017/10/16 0016 17:56
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //网络框架初始化
        OkGo.getInstance().init(this);
        //日志打印框架初始化
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

    }
}
