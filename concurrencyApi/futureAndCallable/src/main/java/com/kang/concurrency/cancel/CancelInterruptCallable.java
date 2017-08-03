package com.kang.concurrency.cancel;

import com.google.common.base.Throwables;
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
public class CancelInterruptCallable implements Callable<String> {

    private Integer sleepTime;

    @Override
    public String call() throws Exception {
        while(true){

                if(!Thread.currentThread().isInterrupted()){
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                    log.info("sleep run!");
                }else{
                    log.info("[{}] had interrupt!",sleepTime);
                    throw new RuntimeException("exception!");
                }
       }
    }
}
