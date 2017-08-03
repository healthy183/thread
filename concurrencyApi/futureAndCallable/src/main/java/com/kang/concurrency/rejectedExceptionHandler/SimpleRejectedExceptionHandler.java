package com.kang.concurrency.rejectedExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/15.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleRejectedExceptionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("{} had reject!",((FutureTask)r).toString());
    }
}
