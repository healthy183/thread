package com.kang.concurrency.factory.uncaughtException;

import com.kang.concurrency.factory.simple.SimpleFactory;
import com.kang.concurrency.factory.simple.SimpleRunnable;

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
public class ExceptionRun {

    public static void main(String[] args) {
        ExceptionRunnable simpleRunnable = new ExceptionRunnable();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (7,10,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        threadPoolExecutor.setThreadFactory(new ExceptionHandlerFactory());
        threadPoolExecutor.execute(simpleRunnable);
    }
}
