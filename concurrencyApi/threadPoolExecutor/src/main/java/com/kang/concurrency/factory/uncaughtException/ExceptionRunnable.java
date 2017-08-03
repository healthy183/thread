package com.kang.concurrency.factory.uncaughtException;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ExceptionRunnable implements  Runnable{
    @Override
    public void run() {
        log.info("start,thread[{}]",Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
        if(1 == 1){
            throw new RuntimeException("exception!");
        }
        log.info("end!");
    }
}
