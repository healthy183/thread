package com.kang.thread.lock.simpleLock;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/18.
 */
@Slf4j
public class Lock {

    private boolean isLocked = false;

    public synchronized  void lock() throws InterruptedException {
        //if (isLocked){//？？？
        while (isLocked){//自旋锁
           // Thread.sleep(500);
            //log.info("thread [{}] will wait!",Thread.currentThread().getId());
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}
