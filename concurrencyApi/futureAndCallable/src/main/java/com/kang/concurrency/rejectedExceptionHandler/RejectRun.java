package com.kang.concurrency.rejectedExceptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/15.
 * @Author Healthy
 * @Version
 */
public class RejectRun {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService;
        threadPoolExecutor.setRejectedExecutionHandler(new SimpleRejectedExceptionHandler());
        threadPoolExecutor.shutdown();

        executorService.submit(new RejectCallable(1));
        executorService.submit(new RejectRunnable(2));
    }
}
