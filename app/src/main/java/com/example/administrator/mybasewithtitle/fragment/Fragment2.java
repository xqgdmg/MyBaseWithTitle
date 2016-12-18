package com.example.administrator.mybasewithtitle.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.mybasewithtitle.R;
import com.example.administrator.mybasewithtitle.base.BaseFragment;

/**
 * 作者：Chris
 * 创建时间: 2016/12/18 21:42
 * 邮箱：395932265@qq.com
 * 描述:
 */
public class Fragment2 extends BaseFragment {


    @Override
    protected void initHeader(ImageView back, TextView title, ImageView add) {
        add.setVisibility(View.INVISIBLE);
        title.setText("Fragment2");
    }

    @Override
    protected void initData() {
    }

    /**
     * 条目点击 进入聊天的页面
     */
    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab2;
    }

    @Override
    public void onClick(View v) {
    }
}
