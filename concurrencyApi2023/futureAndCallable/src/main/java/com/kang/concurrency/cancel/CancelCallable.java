package com.kang.concurrency.cancel;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/12.
 * @Author Healthy
 * @Version
 */
@Setter
@AllArgsConstructor
@Slf4j
public class CancelCallable implements Callable<String> {

    private Integer sleepTime;

    @Override
    public String call() throws Exception {
        Thread.sleep(sleepTime);
        log.info("finish!");
        return "simple";
    }
}
