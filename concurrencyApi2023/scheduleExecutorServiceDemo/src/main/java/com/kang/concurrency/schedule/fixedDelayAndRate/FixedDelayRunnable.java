package com.kang.concurrency.schedule.fixedDelayAndRate;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/23.
 * @Author Healthy
 * @Version
 */
@Slf4j
@AllArgsConstructor
@Getter
public class FixedDelayRunnable implements Runnable {
    private String name;
    private Integer sleepTime;
    @Override
    public void run() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            log.info("{} had sleep {}",name,simpleDateFormat.format(new Date()));
            Thread.sleep(sleepTime);
            String startDate  = simpleDateFormat.format(new Date());
            log.info("{} had wake up {}",name,startDate);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }

    }
}
