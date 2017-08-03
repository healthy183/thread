package com.kang.thread.atomics;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title 类名
 * @Description 测试并发访问IncreaseAtomic
 * @Date 2017/7/18.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) {
        int runCount = 511;
        IncreaseAtomic increaseAtomic = new IncreaseAtomic();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runCount);
        ExecutorService executorService = Executors.newFixedThreadPool(runCount);
        for(int i =0;i<runCount;i++){
            executorService.execute(new IncreaseRunnable(increaseAtomic,cyclicBarrier));
        }
        //executorService.shutdown();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*while(cyclicBarrier.getNumberWaiting() != 0){
        }*/
        log.info("{},{}",increaseAtomic.getAtomicTim(),increaseAtomic.getAtomicTom());
    }
}
