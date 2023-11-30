package com.kang.concurrency.exception;

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
public class SimpleTake {

    public static void main(String[] args) {
        try{
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                    (2,2,5, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
            CompletionService completionService =
                    new ExecutorCompletionService<String>(threadPoolExecutor);
            MycallableA mycallableA = new MycallableA();
            MycallableB mycallableB = new MycallableB();
            completionService.submit(mycallableA);
            completionService.submit(mycallableB);
            //1,no exception throws!
            /*for(int i =0;i<=1;i++){
                Future<String> future = completionService.take();
                log.info("future:{}",future.toString());
            }*/
            //MycallableB throws exception when invoke get() !
            for(int i =0;i<=1;i++){
                Future<String> future = completionService.take();
                log.info("future:{}",future.get().toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
