package com.kang.concurrency.simple;

import java.util.concurrent.ForkJoinPool;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/13.
 * @Author Healthy
 * @Version
 */
public class SimpleRecursiveActionRun {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new SimpleRecursiveAction(1,5));
        Thread.currentThread().join();
    }
}
