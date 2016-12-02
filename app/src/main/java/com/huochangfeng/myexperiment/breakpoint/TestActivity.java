package com.huochangfeng.myexperiment.breakpoint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.huochangfeng.myexperiment.R;

import java.io.File;

/**
 * <br><br>
 * 作者：霍昌峰 on 2016/10/9 17:02<p>
 * 邮箱：553805864@qq.com<p>
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener, ProgressResponseBody.ProgressListener {

    private static final String DOWNLOAD_URL = "http://admin.jicengyisheng.exieshou.com/upload_file/app/4af61a435079ef188d41bf1facc76e25_795222a8429c949f598ca479e02df3b69e5f4d21.apk";
    private ProgressBar pb_test_break_point;
    private Button bt_break_point_start;
    private Button bt_break_point_pause;
    private Button bt_break_point_continue;
    private long breakPoints;
    private ProgressDownloader downloader;
    private File file;
    private long totalBytes;
    private long contentLength;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_break_point);

        init();
    }

    private void init() {
        pb_test_break_point = (ProgressBar) findViewById(R.id.pb_test_break_point);
        bt_break_point_start = (Button) findViewById(R.id.bt_break_point_start);
        bt_break_point_start.setOnClickListener(this);
        bt_break_point_pause = (Button) findViewById(R.id.bt_break_point_pause);
        bt_break_point_pause.setOnClickListener(this);
        bt_break_point_continue = (Button) findViewById(R.id.bt_break_point_continue);
        bt_break_point_continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_break_point_start:
                // 新下载前清空断点信息
                breakPoints = 0L;
                if (downloader != null) {
                    downloader.clear();
                }
                file = new File(getFilesDir(), "test.apk");
                downloader = new ProgressDownloader(DOWNLOAD_URL, file, this);
                downloader.download(0L);
                break;
            case R.id.bt_break_point_pause:
                downloader.pause();
                Toast.makeText(this, "下载暂停", Toast.LENGTH_SHORT).show();
                // 存储此时的totalBytes，即断点位置。
                breakPoints = totalBytes;
                break;
            case R.id.bt_break_point_continue:
                downloader.download(breakPoints);
                break;
        }
    }

    @Override
    public void onPreExecute(final long contentLength) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TestActivity.this, "总长度" + contentLength, Toast.LENGTH_SHORT).show();
            }
        });
        if (this.contentLength == 0L) {
            this.contentLength = contentLength;
            pb_test_break_point.setMax((int) (contentLength / 1024));
        }
    }

    @Override
    public void update(long totalBytes, boolean done) {
        // 注意加上断点的长度
        this.totalBytes = totalBytes + breakPoints;
        pb_test_break_point.setProgress((int) (totalBytes + breakPoints) / 1024);
        if (done) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TestActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
