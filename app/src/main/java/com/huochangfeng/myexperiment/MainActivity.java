package com.huochangfeng.myexperiment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huochangfeng.myexperiment.qrcode.BarCodeTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();

        switch (1) {
            case 1:
                intent.setClass(this, BarCodeTestActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);

        finish();
    }
}
