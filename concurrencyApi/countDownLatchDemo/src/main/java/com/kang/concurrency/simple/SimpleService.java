package com.kang.concurrency.simple;

import java.util.concurrent.CountDownLatch;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
public class SimpleService {
    private CountDownLatch countDownLatch;

    public SimpleService(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }
    public void  testRun(){
        try {
            System.out.println("ready?");
            countDownLatch.await();//if count <=0; await stop!
            System.out.println("running!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void countDown(){
        System.out.println("go!");
        countDownLatch.countDown();//count -1
    }
}
