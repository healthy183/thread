package com.kang.concurrency.asynReturn;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AsynRun {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new AsynReturnRecursiveTask(1000));
        ForkJoinTask<Integer> forkJoinTask2 = forkJoinPool.submit(new AsynReturnRecursiveTask(8000));
        Integer result = forkJoinTask.join();
        log.info("join result[{}] at [{}]",result,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        Integer result2 = forkJoinTask2.join();
        log.info("join result[{}] at [{}]",result2,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));


    }
}
