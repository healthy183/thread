package com.kang.concurrency.shutdown;

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
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (7,10,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        threadPoolExecutor.execute(shutdownRunnable);
        threadPoolExecutor.shutdown();
        log.info("isTerminating?"+threadPoolExecutor.isTerminating());//isClosing?td
        log.info("isTerminated?"+threadPoolExecutor.isTerminated());//close completely?
        //threadPoolExecutor.shutdownNow();
        log.info("awaitTermination?"+threadPoolExecutor.awaitTermination(4000, TimeUnit.MILLISECONDS));
        Thread.sleep(2000);
        log.info("isTerminated?"+threadPoolExecutor.isTerminated());
        log.info("mainRun end!");
    }
}
