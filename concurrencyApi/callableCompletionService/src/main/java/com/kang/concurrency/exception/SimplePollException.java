package com.kang.concurrency.exception;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimplePollException {

    public static void main(String[] args) {
        try{
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                    (2,2,5, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
            CompletionService completionService =
                    new ExecutorCompletionService<String>(threadPoolExecutor);
            MycallableA mycallableA = new MycallableA();
            MycallableB mycallableB = new MycallableB();
            completionService.submit(mycallableB);
            Thread.sleep(10000);
            completionService.submit(mycallableA);
            Thread.sleep(2000);
            //1,no exception throws!
            /*
            for(int i =0;i<=1;i++){
                Future<String> future = completionService.take();
                log.info("future:{}",future.toString());
            }*/
            //MycallableB throws exception when invoke get() !
            /**/
            for(int i =0;i<=1;i++){
                Future<String> future = completionService.poll();
                try{
                    log.info("future:{}",future != null?future.get().toString():null);
                }catch (ExecutionException e) {
                    log.info(Throwables.getStackTraceAsString(e));
                }
            }
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }


    }
}
