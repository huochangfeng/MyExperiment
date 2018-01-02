package com.huochangfeng.myexperiment.utils;

import android.text.TextUtils;

public class MatchUtils {

    public static boolean isMobileNO(String mobiles) {

        String telRegex = "[1][345678]\\d{9}";
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }

    public static boolean isIDCardNO(String number) {
//		String isIDCard1="/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/";
        String isIDCard = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
        return !TextUtils.isEmpty(number) && number.matches(isIDCard);
    }

}
