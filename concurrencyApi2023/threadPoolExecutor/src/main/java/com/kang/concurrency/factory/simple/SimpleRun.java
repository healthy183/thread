package com.kang.concurrency.factory.simple;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/9.
 * @Author Healthy
 * @Version
 */
public class SimpleRun {

    public static void main(String[] args) {
        SimpleRunnable simpleRunnable = new SimpleRunnable();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (7,10,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        threadPoolExecutor.setThreadFactory(new SimpleFactory());
        threadPoolExecutor.execute(simpleRunnable);
    }
}
