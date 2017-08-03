package com.kang.concurrency.awaitAdvanceInterruptibly;

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
public class InterruptThread extends  Thread {
    private Phaser phaser;
    public InterruptThread( Phaser phaser){
        this.phaser = phaser;
    }

    @Override
    public void run(){
        phaser.awaitAdvance(0); //can not be Interruptted;
        /*try {
            phaser.awaitAdvanceInterruptibly(0);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }*/
    }

}
