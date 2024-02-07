package com.kang.concurrency.reset;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class MyFollower implements Runnable {
    private CyclicBarrier barrier;
    private int no;

    public MyFollower(CyclicBarrier barrier, int no) {
        this.barrier = barrier;
        this.no = no;
    }

    @Override
    public void run() {
        log.info("亲信:输入密码ing");
        if (no == 3) {
            log.info("亲信:输错了...重置一下");
            barrier.reset();
            return;
        }
        if (no == 2) {
            log.info("亲信:输慢了...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            barrier.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.info("InterruptedException临死前:骂骂咧咧地退出了游戏");
            return;
        } catch (BrokenBarrierException e) {
            log.info("BrokenBarrierExceptionOS:哪个傻子错了啊");
            return;
        } catch (TimeoutException e) {
            log.info("TimeoutException亲信:是谁输入慢了");
            return;
        }
        log.info("亲信:成了!!!!!");
    }
}