package com.kang.thread.signal;

import com.kang.thread.LogUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/16.
 */
@Slf4j
public class SimpleWaitAndNotify {
    MyWaitNotify myWaitNotify = new MyWaitNotify();

    public synchronized void show(){
        synchronized (myWaitNotify){
            Thread thread = Thread.currentThread();
            log.info("test thread detail:"+thread.getName()+"-"+thread.getId());

        }

    }

    public void dowait(){
        synchronized (myWaitNotify){
            try{
                myWaitNotify.wait();
                LogUtils.showThreadDetail();
            }catch (InterruptedException e){
                log.info(e.toString());
            }
        }
    }

    public void doNotify(){
        synchronized (myWaitNotify){
            myWaitNotify.notify();
            LogUtils.showThreadDetail();
        }
    }
}


