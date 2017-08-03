package com.kang.concurrency.simple;

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
public class SimpleCallable implements Callable<String> {

    private Integer sleepTime;
    private String threadName;

    @Override
    public String call() throws Exception {
        Thread.sleep(sleepTime);
        return  threadName;
    }
}
