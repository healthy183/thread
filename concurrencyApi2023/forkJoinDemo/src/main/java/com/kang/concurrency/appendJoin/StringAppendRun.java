package com.kang.concurrency.appendJoin;

import com.kang.concurrency.simple.SimpleRecursiveTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/14.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class StringAppendRun {

    public static void main(String[] args) {
        StringRecursiveTask stringRecursiveTask = new StringRecursiveTask(1,20);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<String> forkJoinTask = forkJoinPool.submit(stringRecursiveTask);
        String result = forkJoinTask.join();
        log.info(result);
    }
}
