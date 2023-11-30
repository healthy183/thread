package com.kang.concurrency.rejectedExecutionHandler;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/9.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.info("[{}] had been reject!",((RejectRunnable)r).getName());
        try {
            Thread.sleep(6000);
            executor.execute(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
