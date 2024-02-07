package com.kang.thread.sleep;

import lombok.extern.slf4j.Slf4j;

/**
 *类说明：测试Sleep对锁的影响
 */
@Slf4j
public class SleepLock {
    private Object lock = new Object();

    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();
        try {
            Thread.sleep(1000);
            log.info(" Main slept!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }

    private class ThreadSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            log.info(threadName+" will take the lock");
            try {
                synchronized(lock) {
                    log.info(threadName+" taking the lock");
                    Thread.sleep(5000);
                    log.info("Finish the work: "+threadName);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            log.info(threadName+" will take the lock time="+System.currentTimeMillis());
            synchronized(lock) {
                log.info(threadName+" taking the lock time="+System.currentTimeMillis());
                log.info("Finish the work: "+threadName);
            }
        }
    }
}
