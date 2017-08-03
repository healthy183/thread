package com.kang.concurrency.giveup;

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
public class SimplePrintTools {
    private Phaser phaser;
    public SimplePrintTools( Phaser phaser){
        this.phaser = phaser;
    }
    public void  soon(){
        phaser.arriveAndAwaitAdvance();
        log.info("[{}] arrive the first barrier by soon!",Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();
        log.info("[{}] arrive the second barrier by soon!",Thread.currentThread().getName());
    }
    public void await(){
        try {
            Thread.sleep(2000);
            log.info("[{}] the registeredParties is [{}]",Thread.currentThread().getName(),phaser.getRegisteredParties());
            phaser.arriveAndAwaitAdvance();
            log.info("[{}] arrive the first barrier by await() !",Thread.currentThread().getName());
            Thread.sleep(2000);
            log.info("[{}] the registeredParties is [{}]",Thread.currentThread().getName(),phaser.getRegisteredParties());
            phaser.arriveAndDeregister();//this thread give up!
            log.info("[{}] arrive the second barrier by await() !",Thread.currentThread().getName());
            log.info("[{}] the registeredParties is [{}]",Thread.currentThread().getName(),phaser.getRegisteredParties());
        } catch (InterruptedException e) {
           log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
