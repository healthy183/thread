package com.kang.thread.lock.slippedConditions;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/18.
 */
@Slf4j
public class Lock {
    private boolean isLocked = false;
    public   void lock() throws InterruptedException {
        synchronized(this){//if we had two different thread invoke 'lock()' at the same time,and isLocked is false ,
            while (isLocked){//自旋锁  //actually isLocked should be true after the first thread invoke lock()，
                wait();                 //but the second thread invoke 'while(isLocked)' immediately(直接)
                                        //before the first thread jump into next 'synchronized (this)',
                                       // so slippedConditions had happened;
            }
            //isLocked = true;//samply move code here will avoid 'slippedConditions'
        }

        synchronized (this){ //Slipped conditions
            isLocked = true;
        }
    }

    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}
