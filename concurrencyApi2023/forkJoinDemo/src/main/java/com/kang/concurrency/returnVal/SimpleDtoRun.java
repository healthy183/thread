package com.kang.concurrency.returnVal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/17.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleDtoRun {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SimpleDto simpleDto = new SimpleDto();
        SimpleDtoRunnable simpleDtoRunnable = new SimpleDtoRunnable(simpleDto);
        ForkJoinTask<SimpleDto> forkJoinTask = forkJoinPool.submit(simpleDtoRunnable,simpleDto);
        try {
            SimpleDto returnDto = forkJoinTask.get();//get() block method
            log.info(returnDto.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
