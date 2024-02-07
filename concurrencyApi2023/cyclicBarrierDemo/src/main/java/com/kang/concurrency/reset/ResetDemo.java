package com.kang.concurrency.reset;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

@Slf4j
public class ResetDemo {

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(3);
        if (!barrier.isBroken()) {
            log.info("保险箱:安全保护中");
        }
        log.info("亲信们:不成功便成仁!!!!!");
        for (int i = 0; i < 3; i++) {
            int n = i + 1;
            Thread thread = new Thread(new MyFollower(barrier,n  ),"Thread-"+n);
            thread.start();
            if (i == 0) {
                thread.interrupt();
            }
        }
        Thread.sleep(2000);
        if (barrier.isBroken()) {
            log.info("亲信:重置一下");
            barrier.reset();
        }

        if (!barrier.isBroken()) {
            log.info("保险箱:安全保护中");
            log.info("老板:很好,这东西不错");
        }
    }
}
