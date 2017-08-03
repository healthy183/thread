package com.kang.concurrency.afterAndBeforeExecute;

import com.kang.concurrency.shutdown.ShutdownRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) throws InterruptedException {
        ShutdownRunnable shutdownRunnable = new ShutdownRunnable();
        MyThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor
                (7,10,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        threadPoolExecutor.execute(shutdownRunnable);
        threadPoolExecutor.shutdown();
    }
}
