package com.kang.java7.threadManage.interrupt;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/7/12.
 */
@Slf4j
public class InterruptedExceptionThread extends Thread {

    @Override
    public void run(){
        try {
            for(int i = 0; i<10000;i++){
                    doSth();
                    sleep(1000);
                 }
        } catch (InterruptedException e) {
            log.info("InterruptedException exception:"+e.getMessage());
        }
    }


    public void doSth() throws InterruptedException {
        log.info("1");
        if(isInterrupted()){
            throw new InterruptedException();
        }

    }
}
