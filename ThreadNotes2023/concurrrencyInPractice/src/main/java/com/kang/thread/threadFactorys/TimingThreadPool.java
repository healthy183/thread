package com.kang.thread.threadFactorys;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/25.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class TimingThreadPool extends ThreadPoolExecutor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totailTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info("Thread {}: start {}",t,r);
        startTime.set(System.nanoTime());
    }


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try{
            Long endTime = System.nanoTime();
            Long taskTime  = endTime - startTime.get();
            numTasks.incrementAndGet();
            totailTime.addAndGet(taskTime);
            log.info("Thread {}: runnable {} taketime:{}",t,r,taskTime);
        }finally {
            super.afterExecute(r, t);
        }
    }


    @Override
    protected void terminated() {
        try{
            log.info("totailTime:{},avg time{}",totailTime,totailTime.get()/numTasks.get());
        }finally {
            super.terminated();
        }

    }
}
