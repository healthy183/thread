package com.kang.thread.lock.fairLock;

import com.kang.thread.lock.simpleLock.Lock;
import lombok.Getter;

/**
 * Created by Administrator on 2016/5/18.
 */

public class Counter {

    private FairLock lock = new FairLock();
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
