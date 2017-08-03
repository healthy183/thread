package com.kang.concurrency.giveup;

import java.util.concurrent.Phaser;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
public class MainRun {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        SimplePrintTools simplePrintTools = new SimplePrintTools(phaser);
        SoonThread soonThread = new SoonThread(simplePrintTools);
        soonThread.setName("soonThread");
        SoonThread soonThreadCope = new SoonThread(simplePrintTools);
        soonThreadCope.setName("soonThreadCope");
        AwaitThread awaitThread = new AwaitThread(simplePrintTools);
        awaitThread.setName("awaitThread");
        soonThread.start();
        awaitThread.start();
        soonThreadCope.start();
    }
}
