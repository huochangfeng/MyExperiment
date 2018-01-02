package com.huochangfeng.myexperiment.netframe;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huochangfeng.myexperiment.R;
import com.huochangfeng.myexperiment.base.BaseBean;
import com.huochangfeng.myexperiment.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

/**
 * 用来测试OkGo网络框架
 *
 * @author 霍昌峰
 * @date 2017/10/16 0016 17:24
 */
public class NetFrameActivity extends AppCompatActivity {

    private DrawerLayout dl_main;
    private Fragment currentFrg;
    private FrameLayout fl_main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        steepStatusBar();

        setContentView(R.layout.activity_drawlayout);

        initView();

        Glide.with(this).load("").into(new ImageView(this));

        OkGo.<BaseBean>post("www.baidu.com")
                .tag(this)
                .execute(new JsonCallBack<BaseBean>(BaseBean.class) {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {

                    }

                    @Override
                    public void onError(Response<BaseBean> response) {
                        NetExceptionToast.netExceptionToast(response.getException(), NetFrameActivity.this);
                    }
                });

    }

    private void initView() {
        dl_main = (DrawerLayout) findViewById(R.id.dl_main);
        NavigationView nv_main = (NavigationView) findViewById(R.id.nv_main);
        View headerView = nv_main.getHeaderView(0);
        ImageView iv_header = (ImageView) headerView.findViewById(R.id.iv_navigation_header);
        iv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("点击了");
                ToastUtils.showToast(NetFrameActivity.this, "点击了");
            }
        });

        nv_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        ToastUtils.showToast(NetFrameActivity.this, "HOME");
                        break;
                    case R.id.nav_friends:
                        ToastUtils.showToast(NetFrameActivity.this, "FRIENDS");
                        break;
                    case R.id.nav_discussion:
                        ToastUtils.showToast(NetFrameActivity.this, "DISCUSSION");
                        break;
                    case R.id.nav_messages:
                        ToastUtils.showToast(NetFrameActivity.this, "MESSAGE");
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        fl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_main.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 用于切换fragment
     *
     * @param frg 将要显示的fragment
     * @param bs  是否加入回退栈
     */
    public void switchFragment(Fragment frg, boolean bs) {
        if (currentFrg != frg) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!frg.isAdded()) {
                if (currentFrg != null) {
                    if (bs) {
                        ft.hide(currentFrg).add(R.id.fl_main, frg).addToBackStack(null).commit();
                    } else {
                        ft.hide(currentFrg).add(R.id.fl_main, frg).commit();
                    }
                } else {
                    if (bs) {
                        ft.add(R.id.fl_main, frg).addToBackStack(null).commit();
                    } else {
                        ft.add(R.id.fl_main, frg).commit();
                    }
                }
            } else {
                if (currentFrg != null) {
                    ft.hide(currentFrg).show(frg).commit();
                } else {
                    ft.show(frg).commit();
                }
            }
            currentFrg = frg;
        }
    }

    private void hideSoftInput() {
        //隐藏软键盘
        InputMethodManager imm1 = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(dl_main.getWindowToken(), 0);
    }
}
