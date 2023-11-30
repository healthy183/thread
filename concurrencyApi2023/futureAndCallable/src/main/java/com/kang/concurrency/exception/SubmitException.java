package com.kang.concurrency.exception;

import com.google.common.base.Throwables;
import com.kang.concurrency.simple.ExceptionCallable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/15.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SubmitException {

    public static void main(String[] args) {
        //simpleCatch();
        simpleCatchByHandler();
    }

    public static void simpleCatch() {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1,1,100, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        try {
            ExceptionCallable exceptionCallable = new ExceptionCallable();
            Future<String> future = threadPoolExecutor.submit(exceptionCallable);
            future.get();
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        } catch (ExecutionException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }

    public static void simpleCatchByHandler() {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1,1,100, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

        threadPoolExecutor.setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                //invalid code
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        log.info("thread throws unknow exception,cause:\n{}",
                                Throwables.getStackTraceAsString(e));
                    }
                });
                return thread;
            }
        });
        try {
            ExceptionCallable exceptionCallable = new ExceptionCallable();
            Future<String> future = threadPoolExecutor.submit(exceptionCallable);
            future.get();
        } catch (InterruptedException e) {
            log.info("InterruptedException\n{}",Throwables.getStackTraceAsString(e));
        } catch (ExecutionException e) {
            log.info("ExecutionException\n{}",Throwables.getStackTraceAsString(e));
        }
    }
}
