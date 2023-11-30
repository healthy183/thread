package com.kang.concurrency.onAdvance;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                log.info("invode onAdvance()!");
                //return true;//after first arriveAndAwaitAdvance().then cancel  phaser;
                return false;
            }
        };
        OnAdvanceService onAdvanceService = new OnAdvanceService(phaser);
        OnAdvanceThread onAdvanceThread = new OnAdvanceThread(onAdvanceService);
        onAdvanceThread.setName("threadA");
        onAdvanceThread.start();

        onAdvanceThread = new OnAdvanceThread(onAdvanceService);
        onAdvanceThread.setName("threadB");
        onAdvanceThread.start();
    }
}
