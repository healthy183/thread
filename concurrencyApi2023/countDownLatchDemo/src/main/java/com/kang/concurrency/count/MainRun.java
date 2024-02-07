package com.kang.concurrency.count;

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
public class MainRun {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        log.info("{}",countDownLatch.getCount());
        countDownLatch.countDown();
        log.info("{}",countDownLatch.getCount());
        countDownLatch.countDown();
        log.info("{}",countDownLatch.getCount());
        countDownLatch.countDown();
        log.info("{}",countDownLatch.getCount()); // 再减下去也是0
        countDownLatch.countDown();
        log.info("{}",countDownLatch.getCount());
        countDownLatch.countDown();
        log.info("{}",countDownLatch.getCount());
        countDownLatch.countDown();


    }
}
