package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleThread  extends  Thread {

    private CyclicBarrier cyclicBarrier;

    public SimpleThread(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run(){
        try {
            log.info("get ready!");
            Thread.sleep(3000);
            cyclicBarrier.await();
        } catch (InterruptedException  e) {
            log.info(Throwables.getStackTraceAsString(e));
        } catch (BrokenBarrierException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
