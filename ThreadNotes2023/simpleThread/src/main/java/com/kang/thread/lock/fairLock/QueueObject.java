package com.kang.thread.lock.fairLock;

/**
 * Created by Administrator on 2016/6/5.
 */
public class QueueObject {
    private boolean isNotified = false;

    public synchronized  void dowait() throws InterruptedException {
        while(!isNotified){
            this.wait();
        }
        this.isNotified = false;
    }

    public synchronized  void doNotify(){
        this.isNotified =true;
        this.notify();
    }

    public  boolean equals(Object o){
        return this  ==  o;
    }
}

