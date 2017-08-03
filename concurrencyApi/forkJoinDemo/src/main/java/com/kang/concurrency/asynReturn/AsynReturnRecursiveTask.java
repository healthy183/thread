package com.kang.concurrency.asynReturn;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.RecursiveTask;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/14.
 * @Author Healthy
 * @Version
 */
@Slf4j
@AllArgsConstructor
public class AsynReturnRecursiveTask extends RecursiveTask<Integer> {

    private Integer sleepTime;

    @Override
    protected Integer compute() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
        log.info("[{}-{}] run end at [{}]!",Thread.currentThread().getName(),Thread.currentThread().getId(),
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        return 100;
    }
}
