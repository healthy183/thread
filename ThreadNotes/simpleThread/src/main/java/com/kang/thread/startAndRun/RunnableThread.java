package com.kang.thread.startAndRun;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/15.
 */
@Slf4j
public class RunnableThread implements  Runnable {
    @Override
    public void run() {
         try {
             Thread thread = Thread.currentThread();
             log.info(thread.getName()+"-"+thread.getId() +" is running!");
             Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
