package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/12.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleRun {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (2,2,5, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
        //simpleCallable demo
        SimpleCallable simpleCallable = new SimpleCallable();
        Future<String> simleFuture = threadPoolExecutor.submit(simpleCallable);
        String str = simleFuture.get();//block method
        log.info("simleFuture.get[{}]",str);

        //simpleRunnable demo
        SimpleRunnable simpleRunnable  = new SimpleRunnable();
        Future runnableFuture = threadPoolExecutor.submit(simpleRunnable);//return null;
        log.info("runnableFuture.get[{}],isDone[{}]",runnableFuture.get(),runnableFuture.isDone());

        //simpleRunnable with return args
        SimpleDto simpleDto = new SimpleDto();
        SimpleDtoRunnable simpleDtoRunnable = new SimpleDtoRunnable(simpleDto);
        Future<SimpleDto> runnableDtoFuture = threadPoolExecutor.submit(simpleDtoRunnable,simpleDto);
        SimpleDto dto  = runnableDtoFuture.get();
        log.info(dto.getName());
        log.info(simpleDto.getName());

        Thread.sleep(5000);
        //get timeout
        SimpleCallable timeoutCallable = new SimpleCallable();
        Future<String> timeoutFuture = threadPoolExecutor.submit(timeoutCallable);
        try {
            String timeoutStr = timeoutFuture.get(1,TimeUnit.SECONDS);
            log.info("timeoutStr.get[{}]",timeoutStr);
        } catch (TimeoutException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }

        Thread.sleep(1000);
        //exception demo;
        try{
            ExceptionCallable exceptionCallable = new ExceptionCallable();
            Future<String> exceptionFuture = threadPoolExecutor.submit(exceptionCallable);
            exceptionFuture.get();
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
        }




        threadPoolExecutor.shutdown();
    }
}
