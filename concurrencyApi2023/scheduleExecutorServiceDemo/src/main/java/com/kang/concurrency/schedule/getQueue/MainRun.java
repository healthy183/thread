package com.kang.concurrency.schedule.getQueue;

import com.kang.concurrency.schedule.fixedDelayAndRate.FixedDelayRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/23.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRun {
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(10);
        //thread  will execute 1 times after last thread execute completely at 4 seconds;
        executorService.scheduleWithFixedDelay
                (new FixedDelayRunnable("fixedDelayRunnable",3000),1,10, TimeUnit.SECONDS);
        //thread  will execute 1 times after last thread execute completely at 4 seconds;
        executorService.scheduleWithFixedDelay
                (new FixedDelayRunnable("fixedDelayRunnable2",3000),1,4, TimeUnit.SECONDS);

        FixedDelayRunnable fixedDelayRunnable3 = new FixedDelayRunnable("fixedDelayRunnable3",3000);

        ScheduledFuture scheduledFuture =  executorService.scheduleWithFixedDelay
                (fixedDelayRunnable3,10,10, TimeUnit.SECONDS);
        log.info("remove {}",fixedDelayRunnable3);
        executorService.remove((Runnable) scheduledFuture);//remove
        Thread.sleep(2000);
        BlockingQueue<Runnable> blockingQueue =  executorService.getQueue();
        Iterator<Runnable> iterator =  blockingQueue.iterator();
        while(iterator.hasNext()){
            Runnable runnable = iterator.next();
            log.info("{}",runnable.toString());
        }
    }
}


