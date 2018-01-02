package com.huochangfeng.myexperiment.netframe;

import android.content.Context;

import com.huochangfeng.myexperiment.utils.ToastUtils;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 网络异常提示
 *
 * @author 霍昌峰
 * @date 2017/10/17 0017 10:21
 */
public class NetExceptionToast {

    public static void netExceptionToast(Throwable exception, Context context) {
        if (exception != null) {
            exception.printStackTrace();
            Logger.e(exception,"net");
        } else {
            return;
        }

        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            ToastUtils.showToast(context, "网络连接失败");
        } else if (exception instanceof SocketTimeoutException) {
            ToastUtils.showToast(context, "网络连接超时");
        } else if (exception instanceof HttpException) {
            ToastUtils.showToast(context, "服务器连接异常");
        } else if (exception instanceof StorageException) {
            ToastUtils.showToast(context, "SD卡不存在或者没有权限");
        } else {
            ToastUtils.showToast(context, "发生未知错误");
        }
    }
}
