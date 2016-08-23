package com.kang.java7.threadManage.threadLocal;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/14.
 */
public class MainTest {

    @Test
    public void unsafeTest(){
        UnsafeTask task=new UnsafeTask();
        for (int i=0; i<10; i++){
            Thread thread=new Thread(task);//对个线程共用一个Runnable对象task,导致task的Date变量线程不安全
            thread.start();
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void safeTest(){
        SafeTask task=new SafeTask();
        for (int i=0; i<10; i++){
            Thread thread=new Thread(task);//对个线程共用一个Runnable对象task,但task的Date是ThreadLocal变量,所以安全
            thread.start();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
