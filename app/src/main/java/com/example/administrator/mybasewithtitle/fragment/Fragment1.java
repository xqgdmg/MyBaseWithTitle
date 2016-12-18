package com.example.administrator.mybasewithtitle.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
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
public class Fragment1 extends BaseFragment {
    private String currentUser;
    private SwipeRefreshLayout contact_refresh;

    @Override
    protected void initHeader(ImageView back, TextView title, ImageView add) {
        back.setVisibility(View.INVISIBLE);
//        title.setText("Fragment1");
        add.setOnClickListener(this);
    }

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
        return R.layout.fragment_tab1;
    }

    /**
     * 点击事件
     *
     */
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add){
            //头部添加的点击事件,
            toast("add click");
        }
    }

}
