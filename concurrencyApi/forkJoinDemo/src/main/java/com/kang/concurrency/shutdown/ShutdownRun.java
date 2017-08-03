package com.kang.concurrency.shutdown;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/17.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ShutdownRun {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new ShutdownRunnable());
        Thread.sleep(1000);
        forkJoinPool.shutdown();//will not Interrupted the running sub thread;
        //forkJoinPool.submit(new ShutdownRunnable());//the running  sub thread will be kill because the submit exception;
        Thread.sleep(1000);
        /*try{
            forkJoinPool.submit(new ShutdownRunnable());
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
        }*/
        if(!forkJoinPool.isShutdown()){
            forkJoinPool.submit(new ShutdownRunnable());
        }
        log.info("end!");
    }
}
