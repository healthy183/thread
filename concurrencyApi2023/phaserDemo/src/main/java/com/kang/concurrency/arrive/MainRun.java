package com.kang.concurrency.arrive;

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

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(2){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                log.info("arrive phase:[{}],registeredParties[{}]",phase,registeredParties);
                return super.onAdvance(phase, registeredParties);
            }
        };
        phaser.arrive();
        Thread.sleep(2000);
        phaser.arrive();
        Thread.sleep(2000);
        log.info("first!");
        phaser.arrive();
        Thread.sleep(2000);
        phaser.arrive();
        Thread.sleep(2000);
        log.info("second!");
    }
}
