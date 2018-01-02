package com.huochangfeng.myexperiment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huochangfeng.myexperiment.netframe.NetFrameActivity;
import com.huochangfeng.myexperiment.qrcode.BarCodeTestActivity;
import com.huochangfeng.myexperiment.rxjava.RxJavaTest;

/**
 * @author 霍昌峰
 * @date 2017/10/17 0017
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();

        switch (2) {
            case 1:
                intent.setClass(this, BarCodeTestActivity.class);
                break;
            case 2:
                intent.setClass(this, NetFrameActivity.class);
                break;
            case 3:
                intent.setClass(this, RxJavaTest.class);
                break;
            default:
                break;
        }

        startActivity(intent);

        finish();
    }
}
