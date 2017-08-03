package com.kang.concurrency.simple;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/25.
 * @Author Healthy
 * @Version
 */
public class TerminationToken {

    protected volatile  boolean toshutdown  =  false;
    public final AtomicInteger reservation = new AtomicInteger();

    private final Queue<WeakReference<Terminatable>> coordinatedThreads;

    public TerminationToken(){
        this.coordinatedThreads = new ConcurrentLinkedQueue<WeakReference<Terminatable>>();
    }

    public boolean isToshutdown(){
        return toshutdown;
    }

    protected void setToshutdown(){
        this.toshutdown = true;
    }

    protected void register(Terminatable thread){
        coordinatedThreads.add(new WeakReference<Terminatable>(thread));
    }

    protected  void notifyThreadTermination(Terminatable thread){
        WeakReference<Terminatable> wrThread;
        Terminatable otherThread;
        while(null != (wrThread = coordinatedThreads.poll())){
            otherThread = wrThread.get();
            if(otherThread != null && otherThread != thread ){
                otherThread.terminate();
            }
        }

    }

}
