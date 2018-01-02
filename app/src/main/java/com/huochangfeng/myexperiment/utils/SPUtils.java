package com.huochangfeng.myexperiment.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    private static SharedPreferences sharedPreferences = null;

    /**
     * @param ctx   上下文
     * @param key   存储boolean变量方法
     * @param value 值
     */
    public static void putBoolean(Context ctx, String key, boolean value) {

        if (sharedPreferences == null) {
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * @param ctx      上下文
     * @param key      读取boolean变量方法
     * @param defValue 默认值
     * @return 存入的值或默认值
     */
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * @param ctx   上下文
     * @param key   存String变量
     * @param value 值
     */

    public static void putString(Context ctx, String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * @param ctx      上下文
     * @param key      取String变量
     * @param defValue 默认值
     * @return 存入的值或默认值
     */
    public static String getString(Context ctx, String key, String defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defValue);
    }


    /**
     * 存放token的方法
     *
     * @param token   token
     * @param context 上下文
     */
    public static void setToken(String token, Context context) {
        sharedPreferences = context.getSharedPreferences("tokenflag", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("token", token).apply();

    }

    /**
     * 获取token的方法
     *
     * @param context 上下文
     * @return token
     */
    public static String getToken(Context context) {
        sharedPreferences = context.getSharedPreferences("tokenflag", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "0");
    }

    /**
     * 获取密码的方法
     */
    public static String getPassword(Context context) {
        sharedPreferences = context.getSharedPreferences("tokenflag", Context.MODE_PRIVATE);
        return sharedPreferences.getString("password", "0");
    }

    /**
     * 存放密码的方法
     */
    public static void setPassword(String token, Context context) {
        sharedPreferences = context.getSharedPreferences("tokenflag", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("password", token).apply();

    }


    /**
     * 存储用户设置
     *
     * @param context 上下文
     * @return 返回创建好文件的sp对象
     */
    public static SharedPreferences userSetting(Context context) {
        return context.getSharedPreferences("user_setting", Context.MODE_PRIVATE);
    }

    /**
     * 存储常用设置
     *
     * @param context 上下文
     * @return 返回创建好文件的sp对象
     */
    public static SharedPreferences commonSetting(Context context) {
        return context.getSharedPreferences("common_setting", Context.MODE_PRIVATE);
    }


}
