package com.kang.concurrency.excuete;

import com.google.common.base.Throwables;
import com.kang.concurrency.simple.SimpleRecursiveTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/17.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SImpleExceuteRun {

    public static void main(String[] args) {
        try {
            ForkJoinPool forkJoin = new ForkJoinPool();
            SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(5000);
            forkJoin.execute(simpleRecursiveTask);
            Integer reuslt = simpleRecursiveTask.get();
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        } catch (ExecutionException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
