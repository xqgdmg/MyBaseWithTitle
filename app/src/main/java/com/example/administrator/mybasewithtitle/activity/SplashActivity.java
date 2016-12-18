package com.example.administrator.mybasewithtitle.activity;

import android.os.Handler;
import android.view.View;
import com.example.administrator.mybasewithtitle.R;
import com.example.administrator.mybasewithtitle.base.BaseActivity;


/**
 * 作者：Chris
 * 创建时间: 2016/12/18 21:42
 * 邮箱：395932265@qq.com
 * 描述:
 */
public class SplashActivity extends BaseActivity {
    private Handler handler = new Handler();

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //当前是否登录过
        if (isLogingBefore()) {
            //已经登录 直接进入主界面
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startNewActivity(MainActivity.class, true);
                }
            }, 2000);
        } else {
            //没有登录 进入登录界面
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startNewActivity(MainActivity.class, true);
                }
            }, 2000);
        }
    }

    //判断当前是否登录  true为已经登录
    private boolean isLogingBefore() {
            return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);// removeCallbacksAndMessages
    }
}
