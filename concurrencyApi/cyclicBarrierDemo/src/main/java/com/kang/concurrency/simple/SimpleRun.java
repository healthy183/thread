package com.kang.concurrency.simple;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleRun {

    public static void main(String[] args) {
        int count =  5;
        List<SimpleThread> list  = new ArrayList<SimpleThread>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                log.info("run!");
            };
        });
        CyclicBarrier cyclicBarriers = new CyclicBarrier(count,() -> {
                log.info("run");
        });
        for(int i = 0;i<count;i++){
            SimpleThread simpleThread = new SimpleThread(cyclicBarrier);
            list.add(simpleThread);
        }
        for( SimpleThread simpleThread  : list){
            simpleThread.start();
            try {
                Thread.sleep(2000);
                log.info("{} threads is waitting!",cyclicBarrier.getNumberWaiting());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
