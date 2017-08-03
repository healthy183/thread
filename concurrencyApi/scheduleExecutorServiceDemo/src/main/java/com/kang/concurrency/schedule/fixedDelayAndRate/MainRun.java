package com.kang.concurrency.schedule.fixedDelayAndRate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/23.
 * @Author Healthy
 * @Version
 */
public class MainRun {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //every 4 seconds will execute 1 times;
        /*executorService.scheduleAtFixedRate
                (new FixedDelayRunnable("fixedDelayRunnable",3000),1,4, TimeUnit.SECONDS);*/

        //thread  will execute 1 times after last thread execute completely at 4 seconds;
        executorService.scheduleWithFixedDelay
                (new FixedDelayRunnable("fixedDelayRunnable",3000),1,4, TimeUnit.SECONDS);
    }
}


