package com.kang.concurrency.complex;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainThread extends Thread {

    private CountDownLatch comingTag;//途中
    private CountDownLatch waitTag;//等待发号施令
    private CountDownLatch waitRunTag;//等待跑
    private CountDownLatch beginTag;//开始跑
    private CountDownLatch endTag;//到达

    public MainThread(CountDownLatch comingTag,CountDownLatch waitTag,
                      CountDownLatch waitRunTag,CountDownLatch beginTag,
                      CountDownLatch endTag){
        this.comingTag = comingTag;
        this.waitTag = waitTag;
        this.waitRunTag = waitRunTag;
        this.beginTag = beginTag;
        this.endTag = endTag;

    }

    @Override
    public void run(){
        try {
            log.info("[{}-{}] is coming!",Thread.currentThread().getName(),Thread.currentThread().getId());
            Thread.sleep((int)Math.random()*10000);
            comingTag.countDown();
            log.info("[{}-{}] is waiting Tag!",Thread.currentThread().getName(),Thread.currentThread().getId());
            waitTag.await();
            log.info("[{}-{}] is be  ready!",Thread.currentThread().getName(),Thread.currentThread().getId());
            Thread.sleep((int)Math.random()*10000);
            waitRunTag.countDown();
            beginTag.await();
            log.info("[{}-{}] is  running!",Thread.currentThread().getName(),Thread.currentThread().getId());
            Thread.sleep((int)Math.random()*10000);
            endTag.countDown();
            log.info("[{}-{}] is had arrived!",Thread.currentThread().getName(),Thread.currentThread().getId());
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }

    }
}
