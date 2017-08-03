package com.kang.concurrency.awaitAdvanceInterruptibly;

import java.util.concurrent.Phaser;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
public class MainRun {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(1);
        InterruptThread interruptThread = new InterruptThread(phaser);
        interruptThread.start();
        Thread.sleep(5000);
        interruptThread.interrupt();
    }
}
