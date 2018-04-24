package com.huochangfeng.myexperiment.shadow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.huochangfeng.myexperiment.R;


public class ShadowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);

        ImageView iv = (ImageView) findViewById(R.id.iv);

        ShadowDrawableWrapper wrapper = new ShadowDrawableWrapper(this, ContextCompat.getDrawable(this, R.mipmap.ic_launcher),
                10, 20, 20);

        iv.setBackground(wrapper);

    }

}
