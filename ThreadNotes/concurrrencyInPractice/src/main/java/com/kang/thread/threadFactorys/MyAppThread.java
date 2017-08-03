package com.kang.thread.threadFactorys;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/25.
 * @Author Healthy
 * @Version
 */
public class MyAppThread extends  Thread {
    @Getter
    private static final AtomicInteger CREATE_COUNT = new  AtomicInteger();
    @Getter
    private static final AtomicInteger ALIVE_COUNT = new  AtomicInteger();

    public  MyAppThread(Runnable runnable){
        super(runnable);
        CREATE_COUNT.incrementAndGet();
    }

    @Override
    public void run() {
        try{
            ALIVE_COUNT.incrementAndGet();
            super.run();
        }finally {
            ALIVE_COUNT.decrementAndGet();
        }
    }
}
