package com.kang.concurrency.invodeAlls;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/17.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class invodeAll {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<SimpleCallable> lists = new ArrayList<SimpleCallable>();
        lists.add(new SimpleCallable());
        lists.add(new SimpleCallable());
        List<Future<String>>  futureList = forkJoinPool.invokeAll(lists);
        for(Future<String> future : futureList){
            try {
                log.info(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
