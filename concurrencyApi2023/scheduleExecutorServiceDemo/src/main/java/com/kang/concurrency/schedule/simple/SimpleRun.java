package com.kang.concurrency.schedule.simple;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/23.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleRun {

    public static void main(String[] args) {

        List<SimpleCallable> callableStr = new ArrayList<SimpleCallable>();
        callableStr.add(new SimpleCallable("simpleCallableA",5000));
        callableStr.add(new SimpleCallable("simpleCallableB",2000));

        //ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        //4 second ,means all the callable thread will execute in 4 seconds;
        ScheduledFuture<String> stringScheduledFuture = executorService.schedule
                (callableStr.get(0),4L, TimeUnit.SECONDS);

        ScheduledFuture<String> stringScheduledFuture2 = executorService.schedule
                (callableStr.get(1),4L, TimeUnit.SECONDS);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String startDate  = simpleDateFormat.format(new Date());
        log.info("startDate:{}",startDate);

        try {
            String str1 = stringScheduledFuture.get();
            String minDate  = simpleDateFormat.format(new Date());
            log.info("minDate:{}",minDate);
            String str2 = stringScheduledFuture2.get();
            String endDate  = simpleDateFormat.format(new Date());
            log.info("endDate:{}",endDate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
