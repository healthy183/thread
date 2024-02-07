package com.kang.thread.lock.slippedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */

class QueueObject {}

public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        synchronized (this) {
            waitingThreads.add(queueObject);
        }
        boolean mustWait = true;
        while (mustWait) {
            synchronized (this) {
                mustWait = isLocked || waitingThreads.get(0) != queueObject;
                //将最后synchronized (this)移上即可避免slippedConditions
                /*if(!mustWait){
                    waitingThreads.remove(queueObject);
                    isLocked = true;
                    lockingThread = Thread.currentThread();
                    return;
                }*/
            }
            synchronized (queueObject) {
                if (mustWait) {
                    try {
                        queueObject.wait();
                    } catch (InterruptedException e) {
                        waitingThreads.remove(queueObject);
                        throw e;
                    }
                }
            }
        }
        synchronized (this) {
            waitingThreads.remove(queueObject);
            isLocked = true;
            lockingThread = Thread.currentThread();
        }
    }

    public synchronized void unlock(){
        if(this.lockingThread != Thread.currentThread()){
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked      = false;
        lockingThread = null;
        if(waitingThreads.size() > 0){
            QueueObject queueObject = waitingThreads.get(0);
            synchronized(queueObject){
                queueObject.notify();
            }
        }
    }
}

