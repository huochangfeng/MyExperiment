package com.huochangfeng.myexperiment.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：霍昌峰 on 2016/5/31 18:11<p>
 * 邮箱：553805864@qq.com<p>
 */
public class GetMD5 {

    public static String getMD5(String val) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            //加密
            byte[] m = md5.digest();
            return getString(m);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte aB : b) {
            sb.append(aB);
        }
        return sb.toString();
    }
}
