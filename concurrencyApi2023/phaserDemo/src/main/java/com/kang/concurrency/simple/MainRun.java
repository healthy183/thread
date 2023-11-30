package com.kang.concurrency.simple;

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
        SoonThread soonThread = new  SoonThread(simplePrintTools);
        SoonThread soonThreadCope = new  SoonThread(simplePrintTools);
        AwaitThread awaitThread = new  AwaitThread(simplePrintTools);
        soonThread.start();
        awaitThread.start();
        soonThreadCope.start();
    }
}
