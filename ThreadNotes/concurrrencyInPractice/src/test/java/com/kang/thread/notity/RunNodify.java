package com.kang.thread.notity;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/24.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class RunNodify {

    private volatile Integer msg = 1;


    public  synchronized void  doWait(){
        try {
            while(msg == 1){
                wait();
            }
            msg  = 1;
            System.out.println(System.nanoTime()+"wait()");
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }

    public  synchronized void  doNotifyAll(){
        if(msg == 1){
            msg = 0;
            notifyAll();
        }
        System.out.println(System.nanoTime()+"NotifyAll()");
    }

    @AllArgsConstructor
    class WaitRunnable implements Runnable{
        private RunNodify runNodify;
        @Override
        public void run() {
            runNodify.doWait();
        }
    }

    @AllArgsConstructor
    class NodifyRunnable implements Runnable{
        private RunNodify runNodify;
        @Override
        public void run() {
            runNodify.doNotifyAll();
        }
    }

    public void mainRun() throws InterruptedException {
        RunNodify runNodify = new RunNodify();
        Thread nodifyThread = new Thread(new NodifyRunnable(runNodify));
        Thread waitThread = new Thread(new WaitRunnable(runNodify));
        waitThread.start();
        Thread.sleep(2000);
        nodifyThread.start();
    }

    public static void main(String[] args) {
        RunNodify runNodify = new RunNodify();
        try {
            runNodify.mainRun();
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
