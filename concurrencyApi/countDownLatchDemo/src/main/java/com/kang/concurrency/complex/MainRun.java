package com.kang.concurrency.complex;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
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
        int count = 10;
        CountDownLatch comingTag = new CountDownLatch(count);//途中
        CountDownLatch waitTag =new CountDownLatch(1);//等待发号施令
        CountDownLatch waitRunTag = new CountDownLatch(count);//等待跑
        CountDownLatch beginTag = new CountDownLatch(1);//开始跑
        CountDownLatch endTag = new CountDownLatch(count);//到达
        for(int i =0;i<count;i++){
            MainThread mainThread = new MainThread
                    (comingTag, waitTag, waitRunTag, beginTag, endTag);
            mainThread.start();
        }
        try {
            log.info("wait all runner!");
            comingTag.await();
            log.info("wait 5s!");
            Thread.sleep(5000);
            waitTag.countDown();
            waitRunTag.await();
            Thread.sleep(2000);
            log.info("go!");
            beginTag.countDown();
            endTag.await();
            log.info("arrived!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
