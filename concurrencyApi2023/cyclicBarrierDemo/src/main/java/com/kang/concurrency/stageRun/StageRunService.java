package com.kang.concurrency.stageRun;

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
public class StageRunService {

    private CyclicBarrier cyclicBarrier;

    public StageRunService(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
    }

    public void begin(){
        try {
            Thread.sleep((int)Math.random()*1000);
            cyclicBarrier.await();
            log.info("[{}] arrvice stage one!",Thread.currentThread().getName());
            Thread.sleep((int)Math.random()*1000);
            cyclicBarrier.await();
            log.info("[{}] arrvice stage two!",Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        } catch (BrokenBarrierException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }


    }
}
