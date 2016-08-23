package com.kang.java7.threadManage.interrupt;

import org.junit.Test;

/**
 * Created by Administrator on 2016/7/11.
 */

public class MainTest {
    public static void main(String[] args) {
        //simpleInterrupt();
        //exceptionRun();
        //tnterruptedSleep();
    }

    @Test
    public   void simpleInterrupt(){
        Thread task = new IsInterruptThread();
        task.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();//设置中断
    }

    @Test
    public  void exceptionRun(){
        Thread thread  = new InterruptedExceptionThread();
        thread.start();
        try {
            //Thread.sleep(10500);
            //TimeUnit.MILLISECONDS.sleep(13500);
            Thread.currentThread().sleep(10500);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public   void tnterruptedSleep(){
        Thread thread = new InterruptedSleep();
        thread.start();
        try {
            Thread.currentThread().sleep(10800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();//如果睡眠线程被interrupt,马上抛出InterruptedException
    }

}
