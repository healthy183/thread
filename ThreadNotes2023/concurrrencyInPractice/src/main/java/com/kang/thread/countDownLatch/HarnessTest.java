package com.kang.thread.countDownLatch;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class HarnessTest {

    public Long timetasks(int nThreads,final Runnable runnable) throws  InterruptedException{
        final CountDownLatch startCountDown = new CountDownLatch(1);
        final CountDownLatch endCountDown = new CountDownLatch(nThreads);
        for(int i = 0;i<nThreads;i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try{
                        try {
                            startCountDown.await();
                            runnable.run();
                        } finally {
                            endCountDown.countDown();
                        }
                    }catch(InterruptedException e){
                    log.info(Throwables.getStackTraceAsString(e));
                    }
                }
            };
            thread.start();
        }
        Long startTime = System.nanoTime();
        startCountDown.countDown();
        Long endTime = System.nanoTime();
        endCountDown.await();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        CountDownRunnable countDownRunnable = new CountDownRunnable();
        HarnessTest harnessTest = new HarnessTest();
        try {
            Long times  = harnessTest.timetasks(5,countDownRunnable);
            log.info("{}",times);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }

    }


}
