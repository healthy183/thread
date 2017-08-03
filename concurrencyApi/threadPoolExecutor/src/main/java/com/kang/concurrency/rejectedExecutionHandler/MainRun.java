package com.kang.concurrency.rejectedExecutionHandler;

import com.kang.concurrency.shutdown.ShutdownRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
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
        RejectRunnable shutdownRunnable = new RejectRunnable("shutdownRunnable");
        RejectRunnable shutdownRunnable2 = new RejectRunnable("shutdownRunnable2");
        RejectRunnable shutdownRunnable3 = new RejectRunnable("shutdownRunnable3");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (2,2,999L, TimeUnit.SECONDS,new SynchronousQueue<>());
        threadPoolExecutor.setRejectedExecutionHandler(new SimpleRejectedExecutionHandler());
        threadPoolExecutor.execute(shutdownRunnable);
        threadPoolExecutor.execute(shutdownRunnable2);
        threadPoolExecutor.execute(shutdownRunnable3);



    }
}
