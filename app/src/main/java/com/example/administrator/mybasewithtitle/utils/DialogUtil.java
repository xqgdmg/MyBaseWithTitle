package com.lingshangmen.androidlingshangmen.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lingshangmen.androidlingshangmen.R;
import com.lingshangmen.androidlingshangmen.receiver.JPushAction;

/**
 * Created by liangyaotian on 11/7/13.
 */
public final class DialogUtil {

    private static int clickItem = 0;

    public static int chooseSex(final Context context, final int item, final DialogInterface.OnClickListener listener1,
                                final DialogInterface.OnClickListener listener, String title, final int contentArray) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(contentArray, item, listener1)
                .setPositiveButton("确定", listener).create().show();
        return clickItem;
    }

    public static void showSimpleDialog(Context context, String message,
                                        DialogInterface.OnClickListener listener,
                                        DialogInterface.OnClickListener listener1) {
        new AlertDialog.Builder(context)
                .setTitle(message)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", listener1)
                .show();
    }

    public static void showPushDialog(final Context context, JPushAction jPushAction, DialogInterface.OnClickListener listener) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.view_push_summary, null);
        final TextView tvSummary = (TextView) view.findViewById(R.id.tvSummary);

        tvSummary.setText(jPushAction.msgContent);

        String title = jPushAction.title;//如果没有标题就用应用名称
        title = TextUtils.isEmpty(title) ? context.getString(R.string.app_name) : title;

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setView(view)
                .setCancelable(false);
        if (listener == null) {
            builder.setNegativeButton("确定", null);
        } else {
            builder.setPositiveButton("查看", listener)
                    .setNegativeButton("忽略", null);
        }
        builder.create().show();
    }
}
