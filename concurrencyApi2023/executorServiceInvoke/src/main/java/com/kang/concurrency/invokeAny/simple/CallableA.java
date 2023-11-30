package com.kang.concurrency.invokeAny.simple;

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
public class CallableA implements Callable<String> {

    private Integer sleepTime;
    private String threadName;

    @Override
    public String call() throws Exception {
        Thread.sleep(sleepTime);
        log.info("[{}]wake up!",threadName);
        return  threadName;
    }
}
