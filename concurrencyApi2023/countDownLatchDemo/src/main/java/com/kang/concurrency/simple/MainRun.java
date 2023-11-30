package com.kang.concurrency.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
public class MainRun {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SimpleService simpleService = new SimpleService(countDownLatch);
        List<SimpleThread> simpleThreadList = new ArrayList<SimpleThread>();
        for(int i = 0;i<10;i++){
            SimpleThread simpleThread  = new  SimpleThread(simpleService);
            simpleThreadList.add(simpleThread);
        }
       ;for(SimpleThread simpleThread : simpleThreadList){
            simpleThread.start();
        }
        Thread.sleep(1000);
        simpleService.countDown();
    }
}
