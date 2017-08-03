package com.kang.concurrency.otherApi;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
//http://www.dewen.net.cn/q/9077
@Slf4j
public class LockInterruptiblyTest {

    public static void main(String[] args) {
        LockInterruptiblyTest lockInterruptiblyTest = new LockInterruptiblyTest();
        try {
            //lockInterruptiblyTest.testLock();
            lockInterruptiblyTest.testLockInterrupt();
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }

    public void testLockInterrupt() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread thread  = new Thread(){
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();//throws InterruptedException;
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    log.info(Throwables.getStackTraceAsString(e));
                }
            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        log.info("run finish!");
    }

    public void testLock() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread thread  = new Thread(){
            @Override
            public void run() {
                try {
                    lock.lock();//did not throws InterruptedException;
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    log.info(Throwables.getStackTraceAsString(e));
                }
            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        log.info("run finish!");
    }

}
