package com.kang.concurrency.threshold;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ThresholdRun {

    public static void main(String[] args) {
        int count =  6;
        int threshold = 2;
        List<ThresholdThread> list  = new ArrayList<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threshold,()->{
            log.info("run!");
        });
        /*CyclicBarrier cyclicBarriers = new CyclicBarrier(count,() -> {
                log.info("run");
        });*/

        for(int i = 0;i<count;i++){
            ThresholdThread simpleThread = new ThresholdThread(cyclicBarrier);
            list.add(simpleThread);
        }
        for( ThresholdThread simpleThread  : list){
            simpleThread.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
