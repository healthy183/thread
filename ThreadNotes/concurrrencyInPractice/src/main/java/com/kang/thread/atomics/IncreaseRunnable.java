package com.kang.thread.atomics;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/18.
 * @Author Healthy
 * @Version
 */
@AllArgsConstructor
@Slf4j
public class IncreaseRunnable implements  Runnable {

    private IncreaseAtomic increaseAtomic;

    private CyclicBarrier cyclicBarrier;

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            increaseAtomic.increase();
        } catch (InterruptedException | BrokenBarrierException  e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
