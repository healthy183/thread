package com.kang.concurrency.shutdown;

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
public class ShutdownRunnable implements  Runnable{
    @Override
    public void run() {
        log.info("start!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
        log.info("end!");
    }
}
