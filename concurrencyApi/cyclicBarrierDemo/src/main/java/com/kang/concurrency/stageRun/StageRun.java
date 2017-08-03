package com.kang.concurrency.stageRun;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class StageRun {

    public static void main(String[] args) {
      /*  CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
                log.info("congratulation!");
        });*/
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        try {
            StageRunService stageRunService = new StageRunService(cyclicBarrier);
            StageThread stageThread = new StageThread(stageRunService);
            stageThread.setName("A");
            stageThread.start();
            //Thread.sleep(2000);
            stageThread = new StageThread(stageRunService);
            stageThread.setName("B");
            stageThread.start();
            //Thread.sleep(2000);
            stageThread = new StageThread(stageRunService);
            stageThread.setName("C");
            stageThread.start();
            //Thread.sleep(2000);
            stageThread = new StageThread(stageRunService);
            stageThread.setName("D");
            stageThread.start();
            //Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
