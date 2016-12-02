package com.huochangfeng.myexperiment.epub;

import android.app.Activity;
import android.os.Bundle;

import com.huochangfeng.myexperiment.R;

/**
 * <br><br>
 * 作者：霍昌峰 on 2016/7/20 14:00<p>
 * 邮箱：553805864@qq.com<p>
 */
public class BatteryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        BatteryView bv = (BatteryView) findViewById(R.id.bv);
        bv.setPower(100f);

    }
}
