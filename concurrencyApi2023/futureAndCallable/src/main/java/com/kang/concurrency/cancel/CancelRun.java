package com.kang.concurrency.cancel;

import com.kang.concurrency.simple.SimpleCallable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/13.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class CancelRun {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (2,2,5, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
        Future<String> simleFuture = null;
       /* CancelCallable cancelCallable = new CancelCallable(1);
       simleFuture = threadPoolExecutor.submit(cancelCallable);
        log.info( "simleFuture.get[{}]",simleFuture.get());
        log.info( "simleFuture.cancel[{}],simleFuture.isCancelled[{}]",
                simleFuture.cancel(true),simleFuture.isCancelled());

        cancelCallable = new CancelCallable(5000);
        simleFuture = threadPoolExecutor.submit(cancelCallable);
        log.info( "simleFuture.cancel[{}],simleFuture.isCancelled[{}]",
                simleFuture.cancel(true),simleFuture.isCancelled());*/

        //log.info( "simleFuture.get[{}]",simleFuture.get());
        CancelInterruptCallable cancelInterruptCallable = new CancelInterruptCallable(1);
        simleFuture = threadPoolExecutor.submit(cancelInterruptCallable);
        Thread.sleep(8000);
        log.info( "simleFuture.cancel[{}],simleFuture.isCancelled[{}]",
                simleFuture.cancel(true),simleFuture.isCancelled());

        /*Thread.sleep(1000);
        cancelInterruptCallable = new CancelInterruptCallable(1);
        simleFuture = threadPoolExecutor.submit(cancelInterruptCallable);
        Thread.sleep(8000);
        log.info( "simleFuture.cancel[{}],simleFuture.isCancelled[{}]",
                simleFuture.cancel(false),simleFuture.isCancelled());*/
        //why the second is true?




    }
}
