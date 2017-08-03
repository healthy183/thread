package com.kang.concurrency.schedule.cancel;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/1.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class CancelRun {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(10);
        //scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
        MyRunable myRunable = new MyRunable("abc");
        ScheduledFuture scheduledFuture =  scheduledThreadPoolExecutor.schedule(myRunable,2, TimeUnit.SECONDS);
        log.info("runnable cancel reuslt[{}]",scheduledFuture.cancel(true));//cancel work,but didnt remove from queue if setRemoveOnCancelPolicy false
        System.out.println();
        BlockingQueue<Runnable> blockingQueue =  scheduledThreadPoolExecutor.getQueue();
        Iterator<Runnable> iterator = blockingQueue.iterator();
        while(iterator.hasNext()){
            log.info((iterator.next()).toString());
        }

        try {
            MyRunable efgRunable = new MyRunable("efg");
            ScheduledFuture  scheduledFutureEfg =  scheduledThreadPoolExecutor.schedule(myRunable,0, TimeUnit.SECONDS);
            Thread.sleep(3000);
            log.info("runnable cancel reuslt[{}]",scheduledFutureEfg.cancel(true));//Interrupt thread through  isInterrupted
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }

    }
}
