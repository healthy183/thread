package com.kang.concurrency.invokeAll.simple;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/12.
 * @Author Healthy
 * @Version
 */
@Slf4j
@AllArgsConstructor
public class CallableException implements Callable<String> {

    private Integer sleepTime;
    private String threadName;

    @Override
    public String call() throws Exception {
        Math.random();
        Math.random();
        Math.random();
        Math.random();
        log.info("[{}]wake up!",threadName);
        if(true){
            throw new RuntimeException("runtimeException");
        }
        return  threadName;
    }
}
