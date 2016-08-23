package com.kang.thread.lock.fairLock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
public class FairLock {

    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads  = new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread  = true;
        synchronized (this){
            waitingThreads.add(queueObject);
        }

        while(isLockedForThisThread){
            synchronized (this){
                isLockedForThisThread = (isLocked || waitingThreads.get(0) != queueObject);
                if(!isLockedForThisThread){
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                    lockingThread =  Thread.currentThread();
                    return;
                }
            }
            try{
                queueObject.dowait();
            }catch(InterruptedException ex){
                synchronized (this){
                    waitingThreads.remove(queueObject);
                }
                throw ex;
            }
        }
    }

   public synchronized  void unlock(){
       if(this.lockingThread != Thread.currentThread()){
           throw new IllegalMonitorStateException
                   ("Calling thread has not locked this lock");
       }
       isLocked = false;
       lockingThread = null;
       if(waitingThreads.size() > 0 ){
           waitingThreads.get(0).doNotify();
       }
   }
}
