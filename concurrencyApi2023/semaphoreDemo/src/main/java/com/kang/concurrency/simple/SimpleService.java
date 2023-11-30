package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleService {
    private Semaphore semaphore = new Semaphore(3);//permits,it means max thread size
    public void sayHello(){
        try {
            semaphore.acquire();
            log.info("[{}-{}] i am in service!",Thread.currentThread().getName(),Thread.currentThread().getId());
            Thread.sleep(3000);
            semaphore.release();
        } catch (InterruptedException e) {
            Throwables.getStackTraceAsString(e);
        }
    }

}
