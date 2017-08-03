package com.kang.concurrency.runnable;

import com.google.common.base.Throwables;
import com.kang.concurrency.simple.SimpleCallable;
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
        CompletionService completionService =
                new ExecutorCompletionService<String>(threadPoolExecutor);
        //simpleRunnable with return args
        SimpleDto simpleDto = new SimpleDto();
        SimpleDtoRunnable simpleDtoRunnable = new SimpleDtoRunnable(simpleDto);
        Future<SimpleDto> runnableDtoFuture = completionService.submit(simpleDtoRunnable,simpleDto);
        SimpleDto dto  = runnableDtoFuture.get();
        log.info(dto.getName());
        log.info(simpleDto.getName());
        threadPoolExecutor.shutdown();
    }
}
