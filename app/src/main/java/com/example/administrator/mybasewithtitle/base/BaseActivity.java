package com.example.administrator.mybasewithtitle.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mybasewithtitle.MyApplication;
import com.example.administrator.mybasewithtitle.R;

import com.example.administrator.mybasewithtitle.component.ActivityHeaderView;
import com.example.administrator.mybasewithtitle.utils.AlertUtil;
import com.example.administrator.mybasewithtitle.utils.LogUtil;
import com.example.administrator.mybasewithtitle.utils.ToastUtil;

import butterknife.ButterKnife;
import retrofit2.Retrofit;

/**
 * 作者：Chris
 * 创建时间: 2016/12/18 21:42
 * 邮箱：395932265@qq.com
 * 描述:
 *      regCommonBtn 处理了返回按键
 *       processClick 方法，处理除了back按钮外的点击事件
 *      保存 Activity 到 Application ，
 *      跳转到另一个 Activity ，可以关闭的 Log
 *      此Activity 有布局 xml 文件
 *      创建一个 progressdialog
 *      UI 线程中弹出 toast
 *      提供头部的设置方法
 *      提供没有数据的视图
 *
 *      abstract 类不需要在清单文件注册
 */
public abstract class BaseActivity  extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView ivAdd;
    private TextView tvTitle;
    private FrameLayout flContentLayout;
    private RelativeLayout rlNoDataLayout;
    private TextView tvNoData;
    private RelativeLayout rlLoading;
    private InputMethodManager inputMethodManager;
    private ActivityHeaderView activityHeader;
    private int loadingCount;// 计算加载了多少次

    /**
     * onCreate 没有任何操作
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * setContentView
     * 处理布局
     * 后续的操作都在这里实现
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);// 加载自己的布局

        findView();// 找自己的控件

        // 子类需要依附的控件
        LayoutInflater layoutInflater = getLayoutInflater();
        layoutInflater.inflate(layoutResID, flContentLayout, true);// 子类layoutResID 参数，容器 ，依附

        /**********************************以下处理子类的内容****************************************************/
        // ButterKnife绑定,子类不需要再次绑定
        ButterKnife.bind(this);

        //添加到 Application
        ((MyApplication)getApplication()).insertActivity(this);

        // 这些方法是否还有必要？
        initView();
        initListener();
        initData();
        regCommonBtn();
    }

    /**
     * 重载方法，处理布局
     * 后续的操作也可以都在这里实现
     */
    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.activity_base);
        findView();
        flContentLayout.addView(view);
    }

    /**
     * 找自己的控件
     */
    private void findView() {
        // 头部只要找到这个就行，面向对象的思想
        activityHeader = (ActivityHeaderView) findViewById(R.id.activityHeaderView);

        flContentLayout = (FrameLayout) findViewById(R.id.flContentLayout);// 容器
        rlNoDataLayout = (RelativeLayout) findViewById(R.id.rlNoDataLayout);
        tvNoData = (TextView) findViewById(R.id.tvNoData);
        rlLoading = (RelativeLayout) findViewById(R.id.rlLoading);

        // 管理输入法
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    /**
     * 是否显示标题栏
     */
    public void isShowHeader(boolean isShowHeader) {
        activityHeader.isShowHeader(isShowHeader);
    }

    /**
     * 设置标题的文字
     */
    public void setTitle(String string) {
        activityHeader.setTitle(string);
    }

    /**
     * s设置右边的文字
     */
    public void setRightText(String str, View.OnClickListener listener) {
        activityHeader.setRightText(str, listener);
    }

    /**
     * 设置右边的图片
     */
    public void setRightIcon(int resId, View.OnClickListener listener) {
        activityHeader.setRightIcon(resId, listener);
    }

    /**
     * 隐藏右边的文字
     */
    public void hideRightText() {
        activityHeader.hideRightText();
    }

    /**
     * 显示没有数据的文字提示，没有参数
     */
    public void showNoDataTxt() {
        if (rlNoDataLayout != null) {
            rlNoDataLayout.setVisibility(View.VISIBLE);
            showNoDataTxt(null);
        }
    }

    /**
     * 显示没有数据的文字提示，带参数
     */
    public void showNoDataTxt(String noTxt) {
        if (rlNoDataLayout != null && tvNoData != null) {
            rlNoDataLayout.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(noTxt)) {
                tvNoData.setText("目前没有数据");
            } else {
                tvNoData.setText(noTxt);
            }
        }
    }

    /**
     * 隐藏没有数据的文字提示
     */
    public void hideNoDataTxt() {
        if (rlNoDataLayout != null) {
            rlNoDataLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 显示加载中布局 RelativeLayout
     * 并计算加载了多少次
     */
    public void showLoading() {
        if (rlLoading == null) {
            return;
        }
        loadingCount++;
        rlLoading.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加载中布局 RelativeLayout
     * 并计算加载了多少次
     */
    public void hideLoading() {
        if (rlLoading == null) {
            return;
        }
        loadingCount--;
        if (loadingCount <= 0) {// 隐藏比显示还多
            loadingCount = 0;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rlLoading.setVisibility(View.GONE);// gone 掉
                }
            }, 500);
        }
    }


    /**
     * 处理公用按钮,返回键
     */
    private void regCommonBtn() {
        View back = findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(this);
        }
    }

    /**
     * 初始化值
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取布局id
     */
    protected abstract int getLayoutId();

    /**
     * 打印 log.d
     * 自动获取类名为 TAG
     */
    protected void logD(String msg) {
        LogUtil.logD(getClass().getName(), msg);
    }

    /**
     * 打印 log.e
     * 自动获取类名为 TAG
     */
    protected void logE(String msg) {
        LogUtil.logE(getClass().getName(), msg);
    }

    /**
     * 创建一个progressdialog
     */
    protected ProgressDialog makeDialog(String msg) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(msg);
        return dialog;
    }



    /**
     * UI 线程中弹出 toast
     */
    protected void toast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        } else {
            processClick(v);
        }
    }

    /**
     * 处理除了back按钮外的点击事件
     */
    protected abstract void processClick(View v);

    /**
     * 跳转到新界面  是否关闭自己
     */
    protected void startNewActivity(Class clazz, boolean finishCurrent) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (finishCurrent) {
            finish();
        }
    }

    /**
     * 处理事件的分发，只要是为了隐藏键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getAction() == MotionEvent.ACTION_MOVE
                || event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {

            View view = getCurrentFocus();// 获取当前获取到焦点的控件

            // 判断是否该 隐藏键盘
            if (isHideInput(view, event) && inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            return super.dispatchTouchEvent(event);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(event) || onTouchEvent(event);
    }

    /**
     * 通过点击区域，判断是否隐藏输入键盘
     */
    public boolean isHideInput(View view, MotionEvent event) {
        if (view != null && (view instanceof EditText)) {// EditText
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置，上左下右
            view.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();

            if (event.getX() > left && event.getX() < right // 表示没有超过 et 的左右边界
                    && event.getY() > top && event.getY() < bottom) { // 表示没有超过 et 的上下边界
                // 点击的是输入框区域，保留点击EditText的事件，显示键盘
                return false;
            } else {
                return true;// 隐藏键盘
            }
        }
        return false;
    }

    /**
     * 请求网络用到
     *
     * @param error
     */

//    public void showError(RetrofitError error) {
//        if (error.isNetworkError()) {
//            AlertUtil.show(this, "请求数据失败，请重试");
//            return;
//        }
//        AlertUtil.show(this, error + "");
//    }
//
//    public boolean hasError(RequestResult result) {
//        if (result != null) {
//            String message = result.message.descript;
//            if (!result.succeeded) {
//                AlertUtil.show(this, message);//错误用AlertDialog
//            }
//            return !result.succeeded;
//        }
//        return true;
//    }
//
//    public boolean hasError(RequestListResult result) {
//        if (result != null) {
//            String message = result.message.descript;
//            if (!result.succeeded) {
//                AlertUtil.show(this, message);
//            }
//            return !result.succeeded;
//        }
//        return true;
//    }


    /**
     * onDestroy
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication)getApplication()).deleteActivity(this);// 将 Activity 从 Application 中移除
        ButterKnife.unbind(this);//解除 ButterKnife 绑定
    }
}
