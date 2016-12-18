package com.example.administrator.mybasewithtitle.utils;

import com.example.administrator.mybasewithtitle.factory.ThreadPoolProxyFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：Chris
 * 创建时间: 2016/12/18 21:37
 * 邮箱：395932265@qq.com
 * 描述: 很方便的开启新的线程执行耗时任务的辅助类，和 new Thread 的用法类似
 */
public class ThreadUtil {

    /*//创建线程池  线程池中的最大线程数是手机核心数加1 eg：2+1=3
    private static final ExecutorService exector = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()+1);


    public static void runOnNewThread1(Runnable runnable){
        exector.execute(runnable);
    }*/

    public static void runOnNewThread(Runnable runnable){
        ThreadPoolProxyFactory.createDownloadThreadPoolProxy().submit(runnable);
    }
}
