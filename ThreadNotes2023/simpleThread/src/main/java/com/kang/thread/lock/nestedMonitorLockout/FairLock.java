package com.kang.thread.lock.nestedMonitorLockout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
class QueueObject{}

public class FairLock {

    private boolean isLocked = false;
    private Thread lockingThread  = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        synchronized (this){
            waitingThreads.add(queueObject);
            while(isLocked || waitingThreads.get(0) != queueObject){
                synchronized (queueObject){
                    try{
                        queueObject.wait();//释放synchronized (queueObject)锁，但是 synchronized (this)并没有释放
                    }catch(InterruptedException e){
                           waitingThreads.remove(queueObject);
                           throw e;
                    }
                }
            }
            waitingThreads.remove(queueObject);
            isLocked = true;
            lockingThread = Thread.currentThread();
        }
    }

    public synchronized  void unlock(){//lock的synchronized (this)并没有释放,故此将一直等待
        if(this.lockingThread != Thread.currentThread()){
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if(waitingThreads.size() > 0){
            QueueObject queueObject  = waitingThreads.get(0);
            synchronized (queueObject){
                queueObject.notify();
            }
        }
    }
}
