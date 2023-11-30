package com.kang.concurrency.preStartCore;

import com.kang.concurrency.factory.simple.SimpleFactory;
import com.kang.concurrency.factory.simple.SimpleRunnable;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class SimpleRun {

    public static void main(String[] args) throws InterruptedException {
        SimpleRunnable simpleRunnable = new SimpleRunnable();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (2,2,1L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        log.info("poolSize[{}]",threadPoolExecutor.getPoolSize());
        threadPoolExecutor.prestartCoreThread();//start first core thread
        log.info("poolSize[{}]",threadPoolExecutor.getPoolSize());
        threadPoolExecutor.prestartCoreThread();//start second core thread
        log.info("poolSize[{}]",threadPoolExecutor.getPoolSize());
        threadPoolExecutor.prestartCoreThread();//invalid code
        log.info("poolSize[{}]",threadPoolExecutor.getPoolSize());
        threadPoolExecutor.prestartCoreThread();
        log.info("poolSize[{}]",threadPoolExecutor.getPoolSize());
        threadPoolExecutor.shutdown();

        threadPoolExecutor = new ThreadPoolExecutor
                (10,12,1L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        threadPoolExecutor.prestartAllCoreThreads();
        log.info("start all poolSize[{}]",threadPoolExecutor.getPoolSize());
        threadPoolExecutor.shutdown();
    }
}
