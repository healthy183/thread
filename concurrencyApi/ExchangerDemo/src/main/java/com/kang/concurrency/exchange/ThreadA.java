package com.kang.concurrency.exchange;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Exchanger;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ThreadA extends  Thread {

    private Exchanger<String> exchanger;

    public ThreadA( Exchanger<String> exchange){
        this.exchanger = exchange;
    }

    @Override
    public void run(){
        try {
            String val = exchanger.exchange("setByA");
            log.info("[{}-{}],[{}]",Thread.currentThread().getName(),
                    Thread.currentThread().getId(),val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
