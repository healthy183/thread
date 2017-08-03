package com.kang.concurrency.threadException;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ExceptionRun {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
           log.info("welcome!");
        });
        ExceptionService exceptionService = new ExceptionService(cyclicBarrier);
        ExceptionThread threadA = new ExceptionThread(exceptionService);
        threadA.setName("ThreadA");
        ExceptionThread threadB = new ExceptionThread(exceptionService);
        threadB.setName("ThreadB");
        threadA.start();
        threadB.start();
    }
}
