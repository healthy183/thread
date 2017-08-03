package com.kang.concurrency.invokeAny.simple;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/20.
 * @Author Healthy
 * @Version
 */
@Slf4j
@AllArgsConstructor
public class CallableAllableInterrupt  implements Callable<String> {

    private Integer sleepTime;
    private String threadName;

    @Override
    public String call() throws Exception {
        while(true){
            if(!Thread.currentThread().isInterrupted()){
                log.info("[{}] sleep!",threadName);
                Math.random();
                Math.random();
                Math.random();
                Math.random();
            }else{
                log.info("[{}] had interrupt!",threadName);
                throw  new InterruptedException();
            }
        }
        //log.info("[{}]wake up!",threadName);
        //return  threadName;
    }
}
