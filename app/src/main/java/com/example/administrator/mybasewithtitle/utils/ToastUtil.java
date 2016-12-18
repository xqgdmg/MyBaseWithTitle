package com.example.administrator.mybasewithtitle.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：Chris
 * 创建时间: ${DATE} ${HOUR}:${MINUTE}
 * 邮箱：395932265@qq.com
 * 描述:
 *      弹出 Toast 的工具类
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
