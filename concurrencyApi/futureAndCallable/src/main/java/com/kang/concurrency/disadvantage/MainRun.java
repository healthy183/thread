package com.kang.concurrency.disadvantage;

import com.kang.concurrency.cancel.CancelCallable;
import com.kang.concurrency.cancel.CancelInterruptCallable;
import com.kang.concurrency.rejectedExceptionHandler.RejectCallable;
import com.kang.concurrency.rejectedExceptionHandler.RejectRunnable;
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
public class MainRun {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (2,2,5, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
        RejectCallable rejectCallable = new RejectCallable(5000);
        RejectCallable rejectCallable2 = new RejectCallable(2);
        RejectCallable rejectCallable3 = new RejectCallable(1);
        Future<String> simleFuture = threadPoolExecutor.submit(rejectCallable);
        Future<String> simleFuture2 = threadPoolExecutor.submit(rejectCallable2);
        Future<String> simleFuture3 = threadPoolExecutor.submit(rejectCallable3);
        log.info("simleFuture:{}:{}",simleFuture.get(),System.currentTimeMillis());
        log.info("simleFuture2:{}:{}",simleFuture2.get(),System.currentTimeMillis());
        log.info("simleFuture3:{}:{}",simleFuture3.get(),System.currentTimeMillis());
    }
}
