package com.kang.concurrency.completedTaskCount;

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
        log.info("completedTaskCount[{}]",threadPoolExecutor.getCompletedTaskCount());
        threadPoolExecutor.execute(simpleRunnable);
        Thread.sleep(7000);
        log.info("completedTaskCount[{}]",threadPoolExecutor.getCompletedTaskCount());
        simpleRunnable = new SimpleRunnable();
        threadPoolExecutor.execute(simpleRunnable);
        Thread.sleep(8000);
        log.info("completedTaskCount[{}]",threadPoolExecutor.getCompletedTaskCount());
    }
}
