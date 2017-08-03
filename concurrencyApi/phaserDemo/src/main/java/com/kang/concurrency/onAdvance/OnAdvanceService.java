package com.kang.concurrency.onAdvance;

import com.google.common.base.Throwables;
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
public class OnAdvanceService {
    private Phaser phaser;
    public OnAdvanceService(Phaser phaser){
        this.phaser = phaser;
    }
    public void mainRun(){
        try {
            if(Thread.currentThread().getName().equals("threadA")){
                log.info("threadA sleep 5000ms!");
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            if(Thread.currentThread().getName().equals("threadB")){
                log.info("threadB sleep 5000ms!");
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            if(Thread.currentThread().getName().equals("threadA")){
                log.info("threadA sleep 5000ms!");
                Thread.sleep(5000);
                phaser.arriveAndAwaitAdvance();
            }
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
