package com.kang.concurrency.getMethod;

import com.google.common.base.Throwables;
import com.kang.concurrency.shutdown.ShutdownRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
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
class GetRunnable implements  Runnable{
    private String name;
    public GetRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}


@Slf4j
public class MainRun {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (5,10,0L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(500));
        log.info("getPoolSize[{}]",threadPoolExecutor.getPoolSize());//当前线程数
        log.info("getMaximumPoolSize[{}]",threadPoolExecutor.getMaximumPoolSize());
        log.info("getCorePoolSize[{}]",threadPoolExecutor.getCorePoolSize());
        GetRunnable GetRunnable = new GetRunnable("GetRunnable");
        threadPoolExecutor.execute(GetRunnable);
        log.info("getActiveCount[{}]",threadPoolExecutor.getActiveCount());
        GetRunnable GetRunnable2 = new GetRunnable("policyRunnabl2");
        GetRunnable GetRunnable3 = new GetRunnable("policyRunnabl3");
        GetRunnable GetRunnable4 = new GetRunnable("policyRunnabl4");
        GetRunnable GetRunnable5 = new GetRunnable("GetRunnable5");
        threadPoolExecutor.execute(GetRunnable2);

        threadPoolExecutor.execute(GetRunnable3);
        log.info("getTaskCount[{}]",threadPoolExecutor.getTaskCount());//有多少任务发给线程池
        threadPoolExecutor.execute(GetRunnable4);
        threadPoolExecutor.execute(GetRunnable5);
        log.info("getPoolSize[{}]",threadPoolExecutor.getPoolSize());
        Thread.sleep(10000);
        log.info("getCompletedTaskCount[{}]",threadPoolExecutor.getCompletedTaskCount());
        log.info("getTaskCount[{}]",threadPoolExecutor.getTaskCount());//有多少任务发给线程池

        threadPoolExecutor.shutdown();

    }
}
