package com.kang.java7.threadManage.threadLocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/14.
 */
public class SafeTask implements  Runnable {
    /*private static ThreadLocal<Date> startDate= new ThreadLocal<Date>() {
        protected Date initialValue(){
            return new Date();
        }
    };*/
    private  ThreadLocal<Date> startDate = new ThreadLocal<Date>();

    @Override
    public void run() {
        Date newDate = new Date();
        startDate.set(newDate);
        System.out.printf("Starting Thread: %s : %s\n",Thread.currentThread().getId(),newDate);
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n",Thread. currentThread().getId(),startDate.get());
    }
}
