package com.kang.concurrency.rejectedExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/12.
 * @Author Healthy
 * @Version
 */
@Setter
@AllArgsConstructor
@Slf4j
public class RejectRunnable implements  Runnable {

    private Integer  sleepTime;

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
            log.info("{} finish!",sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
