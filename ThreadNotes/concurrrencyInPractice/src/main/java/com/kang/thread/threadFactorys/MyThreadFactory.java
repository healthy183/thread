package com.kang.thread.threadFactorys;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;

/**
 * @Title 类名
 * @Description 包含线程创建数的MyThreadFactory
 * @Date 2017/7/25.
 * @Author Healthy
 * @Version
 */
@AllArgsConstructor
@Slf4j
public class MyThreadFactory implements ThreadFactory {
    @Getter
    private String poolName;
    @Override
    public Thread newThread(Runnable r) {
        MyAppThread myAppThread = new MyAppThread(r);
        myAppThread.setName(poolName+myAppThread.getCREATE_COUNT());
        myAppThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.info("thread {} throw unknowException,cause:\n{}",t.getName(),
                        Throwables.getStackTraceAsString(e));
            }
        });
        return myAppThread;
    }
}
