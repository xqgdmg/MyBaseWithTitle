package com.example.administrator.mybasewithtitle.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mybasewithtitle.R;
import com.example.administrator.mybasewithtitle.utils.StringUtil;

/**
 * 自定义头部 View
 */
public class ActivityHeaderView extends RelativeLayout {

    private ImageView ivLeft;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvTitle;
    protected ImageView ivRight;

    public ActivityHeaderView(Context context) {
        super(context);
        init();
    }

    public ActivityHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ActivityHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_activity_header, this);
        initView(view);
        registerListener();
    }

    /**
     * 找控件
     */
    private void initView(View view) {
        ivLeft = (ImageView) view.findViewById(R.id.ivHeaderLeft);
        ivRight = (ImageView) view.findViewById(R.id.ivRight);
        tvLeft = (TextView) view.findViewById(R.id.tvHeaderLeft);
        tvRight = (TextView) view.findViewById(R.id.tvHeaderRight);
        tvTitle = (TextView) view.findViewById(R.id.tvHeaderTitle);
    }

    /**
     * 设置监听
     */
    private void registerListener() {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tvLeft) {

                }
            }
        };
        tvLeft.setOnClickListener(listener);
    }

    /**
     * 时候显示 这个 头部View
     */
    public void isShowHeader(boolean isShowHeader) {
        this.setVisibility(isShowHeader ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置标题
     */
    public void setTitle(int stringId) {
        setTitle(getResources().getString(stringId));
    }

    /**
     * 设置标题
     */
    public void setTitle(String txt) {
        if (tvTitle == null) {
            return;
        }
        tvTitle.setText(StringUtil.null2EmptyString(txt));
        showTitle();
    }

    /**
     * 设置右边的tv enable
     */
    public void setRightEnable(boolean enable) {
        tvRight.setEnabled(enable);
    }

    /**
     * 设置右边的图片
     */
    public void setRightIcon(int resId, OnClickListener listener) {
        if (ivRight != null) {
            ivRight.setVisibility(VISIBLE);
            ivRight.setImageResource(resId);
            ivRight.setOnClickListener(listener);
        }
    }

    /**
     * 隐藏右边的图片
     */
    public void hideRightIcon() {
        ivRight.setVisibility(GONE);
    }

    /**
     * 设置左边的文本
     */
    public void setLeftText(int stringId) {
        setLeftText(getResources().getString(stringId));
    }

    /**
     * 设置左边的文本
     */
    public void setLeftText(String txt) {
        tvLeft.setText(StringUtil.null2EmptyString(txt));
        showLeftText();
    }

    /**
     * 设置左边的文字，并同时添加点击事件
     */
    public void setLeftText(String txt, OnClickListener listener) {
        if (tvLeft == null) {
            return;
        }
        tvLeft.setText(StringUtil.null2EmptyString(txt));
        tvLeft.setOnClickListener(listener);
        showLeftText();
    }

    /**
     * 设置左边的图片
     */
    public void setLeftIcon(int resId, OnClickListener listener) {
        if (ivLeft == null) {
            return;
        }
        ivLeft.setImageResource(resId);
        ivLeft.setOnClickListener(listener);
        showLeftIcon();
    }

    /**
     * 设置右边的文本
     */
    public void setRightText(int resId) {
        setRightText(getResources().getString(resId));
    }

    /**
     * 设置右边的文本
     */
    public void setRightText(String txt) {
        setRightText(txt, null);
    }

    /**
     * 设置右边的文字，并同时添加点击事件
     */
    public void setRightText(String txt, OnClickListener listener) {
        if (tvRight == null) {
            return;
        }
        tvRight.setText(StringUtil.null2EmptyString(txt));
        tvRight.setOnClickListener(listener);
        showRightText();
    }

    /**
     * 显示标题
     */
    public void showTitle() {
        if (tvTitle != null) {
            tvTitle.setVisibility(VISIBLE);
        }
    }

    /**
     * 隐藏 标题
     */
    public void hideTitle() {
        if (tvTitle != null) {
            tvTitle.setVisibility(GONE);
        }
    }

    /**
     * 显示左边的图片
     */
    public void showLeftIcon() {
        if (ivLeft != null) {
            ivLeft.setVisibility(VISIBLE);
        }
    }

    /**
     * 隐藏左边的图片
     */
    public void hideLeftIcon() {
        if (ivLeft != null) {
            ivLeft.setVisibility(GONE);
        }
    }

    /**
     * 显示左边的文字
     */
    public void showLeftText() {
        if (tvLeft != null) {
            tvLeft.setVisibility(VISIBLE);
        }
    }

    /**
     * 隐藏左边的文字
     */
    public void hideLeftText() {
        if (tvLeft != null) {
            tvLeft.setVisibility(GONE);
        }
    }

    /**
     * 显示右边的文本
     */
    public void showRightText() {
        if (tvRight != null) {
            tvRight.setVisibility(VISIBLE);
        }
    }

    /**
     * 隐藏右边的文本
     */
    public void hideRightText() {
        if (tvRight != null) {
            tvRight.setVisibility(GONE);
        }
    }
}
