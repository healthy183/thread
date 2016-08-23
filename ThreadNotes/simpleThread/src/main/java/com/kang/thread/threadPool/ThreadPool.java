package com.kang.thread.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2016/6/13.
 */
public class ThreadPool {

    private BlockingQueue taskQueue = null;
    private List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean isStopped = false;

    public  ThreadPool(int noOfThreads, int maxNoOfTasks){
        taskQueue = new LinkedBlockingDeque(maxNoOfTasks);
        for(int i = 0;i<noOfThreads;i++){
            threads.add(new PoolThread((taskQueue)));
        }
        for(PoolThread thread : threads){
            thread.start();
        }
    }

    public synchronized void  execute(Runnable task){
        if(this.isStopped)
            throw new IllegalStateException("ThreadPool is stopped");
        this.taskQueue.add(task);
    }

    public synchronized  boolean stop(){
        this.isStopped = true;
        for(PoolThread thread : threads){
            thread.toStop();
        }
       return isStopped;
    }

}
