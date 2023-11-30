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
public class SimpleRecursiveTaskRunException {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTaskGet = forkJoinPool.submit(new SimpleRecursiveTaskException(8000));
        Integer resultGet = null;
        try {
            resultGet = forkJoinTaskGet.get();//throws exception  compulsively
            log.info("getException result[{}]",resultGet);
        } catch (InterruptedException e) {
            log.info("InterruptedException cause\n[{}]",Throwables.getStackTraceAsString(e));
        } catch (ExecutionException e) {
            log.info("ExecutionException cause\n[{}]",Throwables.getStackTraceAsString(e));
        }
        log.info("===========================================");
        ForkJoinTask<Integer>  ifForkJoinTask = forkJoinPool.submit(new SimpleRecursiveTaskException(8000));
        Thread.sleep(9000);
        //it seem  insignificance,becuase i dont know how long the task will excuete!!!!
        if(!ifForkJoinTask.isCompletedNormally()){
            log.info("exception completed,cuase:\n{}",ifForkJoinTask.getException());
        }
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new SimpleRecursiveTaskException(8000));
        Integer result = forkJoinTask.join();
        log.info("joinException result[{}]",result);
    }
}
