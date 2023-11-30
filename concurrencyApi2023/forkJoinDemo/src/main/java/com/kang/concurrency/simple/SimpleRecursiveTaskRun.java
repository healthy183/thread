package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
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
public class SimpleRecursiveTaskRun {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new SimpleRecursiveTask(8000));
        Integer result = forkJoinTask.join();
        log.info("join result[{}]",result);

        ForkJoinTask<Integer> forkJoinTaskGet = forkJoinPool.submit(new SimpleRecursiveTask(8000));
        Integer resultGet = null;
        try {
            resultGet = forkJoinTaskGet.get();
            log.info("get result[{}]",resultGet);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        } catch (ExecutionException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }


    }
}
