package com.kang.concurrency.allCoreTimeOut;

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
                (1,10,1L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        threadPoolExecutor.allowCoreThreadTimeOut(true);//aliveTime must be over 1 timeUitl
        threadPoolExecutor.setThreadFactory(new SimpleFactory());
        threadPoolExecutor.execute(simpleRunnable);
        Thread.sleep(8000);
        log.info("poolSize[{}]",threadPoolExecutor.getPoolSize());
    }
}
