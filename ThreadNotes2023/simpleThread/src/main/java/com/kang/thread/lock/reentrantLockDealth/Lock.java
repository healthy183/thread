package com.kang.thread.lock.reentrantLockDealth;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/6/3.
 */
@Slf4j
public class Lock {

    private boolean isLocked = false;

    public synchronized  void lock() throws InterruptedException {
        while(isLocked){
            wait();
        }
        isLocked =true;
        log.info("thread [{}] is  here!",Thread.currentThread().getId());
    }

    public synchronized void unlock(){
        isLocked =false;
        notify();
        log.info("thread [{}] is  say bye bye!",Thread.currentThread().getId());
    }
}
