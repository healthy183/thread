package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
public class SimpleRecursiveTaskException extends RecursiveTask<Integer> {

    private Integer sleepTime;

    @Override
    protected Integer compute() {
        log.info("[{}-{}] is running!",Thread.currentThread().getName(),Thread.currentThread().getId());
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
        int i = 2/0;
        return 100;
    }
}
