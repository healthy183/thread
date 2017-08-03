package com.kang.concurrency.schedule.simple;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/23.
 * @Author Healthy
 * @Version
 */
@Slf4j
@AllArgsConstructor
public class SimpleCallable implements Callable<String> {
    private String name;
    private Integer sleepTime;
    @Override
    public String call() throws Exception {
        Thread.sleep(sleepTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String startDate  = simpleDateFormat.format(new Date());
        log.info("{} had wake up {}",name,startDate);
        return name;
    }
}
