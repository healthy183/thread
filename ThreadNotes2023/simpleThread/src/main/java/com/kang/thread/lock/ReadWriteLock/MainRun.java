package com.kang.thread.lock.ReadWriteLock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/19.
 */

class CountWirterRunnable implements  Runnable{
    private  Counter counter;
    public CountWirterRunnable(Counter counter){
        this.counter = counter;
    }
    @Override
    public void run() {
        counter.writer();
    }
}

class CountReadRunnable implements  Runnable{
    private  Counter counter;
    public CountReadRunnable(Counter counter){
        this.counter = counter;
    }
    @Override
    public void run() {
        counter.read();
    }
}

public class MainRun {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<Thread>();
        Set<Thread> set = new HashSet<Thread>();
        int forCount = 50000;
        for (int i = 0; i <= forCount; i++) {
            Thread threadR = new Thread(new CountWirterRunnable(counter));
            Thread threadW = new Thread(new CountReadRunnable(counter));
            set.add(threadR);
            set.add(threadW);
        }
        for(Thread thread : set){
            thread.start();
            thread.join();
        }
        System.out.println("count:"+counter.getCount());
    }
}
