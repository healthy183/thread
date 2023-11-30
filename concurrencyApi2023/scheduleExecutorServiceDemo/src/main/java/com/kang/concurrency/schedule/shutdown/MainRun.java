package com.kang.concurrency.schedule.shutdown;

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
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(10);
        //thread  will execute 1 times after last thread execute completely at 4 seconds;
        executorService.scheduleWithFixedDelay
                (new FixedDelayRunnable("fixedDelayRunnable",3000),1,10, TimeUnit.SECONDS);
        // Scheduled task alway execute even after call shutdown
        executorService.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        executorService.shutdown();

    }
}


