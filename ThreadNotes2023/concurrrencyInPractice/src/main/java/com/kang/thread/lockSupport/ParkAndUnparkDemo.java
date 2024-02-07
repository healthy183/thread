package com.kang.thread.lockSupport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;


@Slf4j
public class ParkAndUnparkDemo {
    public static void main(String[] args) {
        ParkAndUnparkThread myThread = new ParkAndUnparkThread(Thread.currentThread());
        myThread.start();

        ParkAndUnparkThread myThread2 = new ParkAndUnparkThread(new Thread());
        myThread2.start();

        log.info(Thread.currentThread().getName()+" before park");
        // 获取许可
        LockSupport.park();  // 线程 Thread-0  unpark后main才会执行最后一步
        log.info(Thread.currentThread().getName()+" after park");
    }
}

@Slf4j
class ParkAndUnparkThread extends Thread {
    private Object object;

    public ParkAndUnparkThread(Object object) {
        this.object = object;
    }

    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(Thread.currentThread().getName()+" before unpark");
        // 释放许可
        /*if(Thread.currentThread().getName().equals("Thread-2")){
         log.info("unpark " + Thread.currentThread().getName());
            LockSupport.unpark((Thread) object);
        }*/
        log.info(Thread.currentThread().getName() + " unpark " );
        LockSupport.unpark((Thread) object);
        log.info(Thread.currentThread().getName()+" after unpark");
    }
}
