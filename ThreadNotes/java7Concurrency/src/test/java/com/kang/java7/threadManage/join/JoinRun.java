package com.kang.java7.threadManage.join;

import org.junit.Test;

/**
 * Created by Administrator on 2016/7/13.
 */
public class JoinRun {

    @Test
    public void test(){


        JoinRunnable joinRunnable = new JoinRunnable();
        Thread thread = new Thread(joinRunnable);
        JoinRunnableCope joinRunnableCope = new JoinRunnableCope();
        Thread threadCope = new Thread(joinRunnableCope);
        thread.start();
        threadCope.start();
        //join等待其他线程运行完才停止
        /**/
        try {
            thread.join();
            threadCope.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /*try {
            Thread threads =  Thread.currentThread();
            threads.setDaemon(true);
            threads.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
