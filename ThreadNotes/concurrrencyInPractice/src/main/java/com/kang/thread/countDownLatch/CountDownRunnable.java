package com.kang.thread.countDownLatch;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class CountDownRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
