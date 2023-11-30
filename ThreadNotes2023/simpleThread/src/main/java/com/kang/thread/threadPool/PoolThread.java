package com.kang.thread.threadPool;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2016/6/13.
 */
public class PoolThread extends Thread {

    private BlockingQueue<Runnable> taskQueue = null;
    private boolean isStopped = false;

    public PoolThread(BlockingQueue<Runnable> taskQueue){
        this.taskQueue = taskQueue;
    }

    public void run(){
        while(!isStopped()){
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void toStop(){
        isStopped = true;
        this.interrupt();// 打断池中线程的 dequeue() 调用.
    }

    public synchronized  boolean isStopped(){
        return isStopped;
    }
}
