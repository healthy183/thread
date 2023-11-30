package com.kang.concurrency.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MycallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        log.info("a start!");
        Thread.sleep(1000);
        log.info("a end!");
        return "MycallableA";
    }
}
