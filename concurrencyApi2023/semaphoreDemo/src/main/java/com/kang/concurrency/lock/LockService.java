package com.kang.concurrency.lock;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class LockService {

    private Semaphore semaphore = new Semaphore(3);
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void sayHello(){
        try {
            semaphore.acquire();
            log.info("[{}-{}] have get ready!",Thread.currentThread().getName(),Thread.currentThread().getId());
            reentrantLock.lock();
            log.info("[{}-{}] is runing!",Thread.currentThread().getName(),Thread.currentThread().getId());
            Thread.currentThread().sleep(2000);
            reentrantLock.unlock();
            semaphore.release();
        } catch (InterruptedException e) {
            Throwables.getStackTraceAsString(e);
        }
    }

}
