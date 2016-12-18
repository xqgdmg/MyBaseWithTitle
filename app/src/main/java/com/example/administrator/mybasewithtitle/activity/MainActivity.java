package com.example.administrator.mybasewithtitle.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.mybasewithtitle.R;
import com.example.administrator.mybasewithtitle.base.BaseActivity;
import com.example.administrator.mybasewithtitle.base.BaseFragment;
import com.example.administrator.mybasewithtitle.fragment.Fragment1;
import com.example.administrator.mybasewithtitle.fragment.Fragment2;
import com.example.administrator.mybasewithtitle.fragment.Fragment3;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 作者：Chris
 * 创建时间: 2016/12/18 21:37
 * 邮箱：395932265@qq.com
 * 描述:
 *      主页面
 */
public class MainActivity extends BaseActivity {


    @Bind(R.id.main_container)
    FrameLayout mainContainer;
    @Bind(R.id.tab_1)
    ImageView tab1;
    @Bind(R.id.main_unread)
    TextView mainUnread;
    @Bind(R.id.tab_2)
    ImageView tab2;
    @Bind(R.id.tab_3)
    ImageView tab3;

    private ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
    private int originalIndex = 0;// tab 的初始位置为 0

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

        //初始化界面
        initFragment();
    }

    @Override
    protected void initListener() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 点击事件 处理 tab 和 fragment 之间的切换
     */
    @Override
    protected void processClick(View v) {
        int currentIndex = 0;
        // 全部先设置为未选中的状态
        tab1.setSelected(false);
        tab2.setSelected(false);
        tab3.setSelected(false);

        // switch 点击哪个，哪个就是选中状态，更新 currentIndex
        switch (v.getId()) {
            case R.id.tab_1:
                currentIndex = 0;
                tab1.setSelected(true);
                break;
            case R.id.tab_2:
                tab2.setSelected(true);
                currentIndex = 1;
                break;
            case R.id.tab_3:
                tab3.setSelected(true);
                currentIndex = 2;
                break;
            default:
                break;
        }

        //判断是否已经显示了当前界面,如果显示正确了，后面就不需要执行了
        if (currentIndex == originalIndex) {
            return;
        }

        //判断当前的fragment是否已经添加进来了，切换fragment
        if (fragments.get(currentIndex).isAdded()) {
            //已经添加进来
            getSupportFragmentManager().beginTransaction().hide(fragments.get(originalIndex)).show(fragments.get(currentIndex)).commit();
        } else {
            //还没有添加
            getSupportFragmentManager().beginTransaction().hide(fragments.get(originalIndex)).add(R.id.main_container, fragments.get(currentIndex)).commit();
        }
        //标记原来的independencex
        originalIndex = currentIndex;

    }


    //初始化要显示的fragment
    private void initFragment() {
        fragments.clear();// 清空集合

        // 添加所有 fragment
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());


        //隐藏非第一个
        if (fragments.get(1).isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(fragments.get(1)).commit();
        }
        if (fragments.get(2).isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(fragments.get(2)).commit();
        }

        //显示第一个fragment
        if (fragments.get(0).isAdded()) { // 第一个fragment已经添加, 第一个按钮被选中
            getSupportFragmentManager().beginTransaction().show(fragments.get(0)).commit();
            tab1.setSelected(true);
        } else {
            //第一个fragment没有添加 添加并显示，第一个按钮被选中
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, fragments.get(0), "0").commit();
            tab1.setSelected(true);
        }
    }

}
