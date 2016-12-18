package com.example.administrator.mybasewithtitle.utils;

import android.util.Log;

/**
 * 作者：Chris
 * 创建时间: 2016/12/18 21:37
 * 邮箱：395932265@qq.com
 * 描述: 如果 showLog == true，则打印 log
 */
public class LogUtil {
    private static boolean showLog = true;
    public static void logD(String tag,String msg){
        if(showLog) {
            Log.d(tag, msg);
        }
    }

    public static void logE(String tag,String msg){
        if(showLog) {
            Log.e(tag, msg);
        }
    }
}
