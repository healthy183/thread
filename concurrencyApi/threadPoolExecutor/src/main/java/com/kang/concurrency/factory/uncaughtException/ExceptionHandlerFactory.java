package com.kang.concurrency.factory.uncaughtException;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/9.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ExceptionHandlerFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("creteByExceptionHandlerFactory");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.info("[{}] throws unknow exception!",t.getName());
                log.info(Throwables.getStackTraceAsString(e));
            }
        });
        return thread;
    }
}
