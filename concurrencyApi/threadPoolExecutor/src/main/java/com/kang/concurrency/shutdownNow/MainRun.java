package com.kang.concurrency.shutdownNow;

import com.kang.concurrency.shutdown.ShutdownRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
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
    public static void main(String[] args) {
        ShutdownRunnable shutdownRunnable = new ShutdownRunnable();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,10,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        threadPoolExecutor.execute(shutdownRunnable);
        threadPoolExecutor.execute(shutdownRunnable);
        threadPoolExecutor.execute(shutdownRunnable);
        //threadPoolExecutor.shutdown();//wait all thread includ which in deque execute completely,and shutdown threadPool;
        List<Runnable> dequeRunaable =  threadPoolExecutor.shutdownNow();//shut down the threadPool,and those executing threads will throw interrupt exception,the  threads which in  deque will cancel;
        log.info("the  threads which in  deque  have [{}]",dequeRunaable.size());
        log.info("mainRun end!");
        log.info("isShutdown?"+threadPoolExecutor.isShutdown());
    }
}
