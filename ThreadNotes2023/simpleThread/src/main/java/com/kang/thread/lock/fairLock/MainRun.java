package com.kang.thread.lock.fairLock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */

@Slf4j
class CountRunnable implements Runnable{
    private Counter counter;
    public CountRunnable(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            int count =  counter.inc();
            log.info("count:"+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
public class MainRun {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<Thread>();
        int forCount = 50000;
        for(int i=0;i<=forCount;i++){
            Thread thread =  new Thread(new CountRunnable(counter));
            threadList.add(thread);
        }
        for(int i=0;i<=forCount;i++){
            threadList.get(i).start();
            //threadList.get(i).join();
        }
        //log.info("count is "+counter.getCount());
    }
}
