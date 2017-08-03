package com.kang.concurrency.simple;

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
        log.info("[{}] arrive the first barrier by soon!",Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();
        log.info("[{}] arrive the second barrier by soon!",Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();
    }
    public void await(){
        try {
            Thread.sleep(2000);
            log.info("[{}] arrive the first barrier by await() !",Thread.currentThread().getName());
            phaser.arriveAndAwaitAdvance();
            Thread.sleep(2000);
            log.info("[{}] arrive the second barrier by await() !",Thread.currentThread().getName());
            phaser.arriveAndAwaitAdvance();
        } catch (InterruptedException e) {
           log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
