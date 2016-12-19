package com.example.administrator.mybasewithtitle.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;


/**
 * Created by chenxiabin on 2016/4/26 0026.
 */
public class AlertUtil {

    /**
     * 显示对话框,context 必须是 Activity
     */
    public static void show(Context context,  String message) {

        if (context == null) {
            return;
        }
//        if (TextUtils.isEmpty(title)) {
//            return;
//        }
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (((Activity)context).isFinishing()){
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
//        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

       /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();*/
    }
}
