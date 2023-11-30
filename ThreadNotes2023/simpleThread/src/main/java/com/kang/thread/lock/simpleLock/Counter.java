package com.kang.thread.lock.simpleLock;

import lombok.Getter;

/**
 * Created by Administrator on 2016/5/18.
 */

public class Counter {

    private Lock lock = new Lock();
    @Getter
    private  int count = 0;//volatile

    public int inc() throws InterruptedException {
        lock.lock();
       // Thread.sleep(1000);
        int newCount = ++count;
        lock.unlock();
        return newCount;
    }

}
