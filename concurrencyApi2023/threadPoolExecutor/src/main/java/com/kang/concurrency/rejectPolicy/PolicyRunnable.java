package com.kang.concurrency.rejectPolicy;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class PolicyRunnable implements  Runnable{
    private String name;
    public PolicyRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        log.info("start,thread[{}]",name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
        log.info("end,thread[{}]",name);
    }
}
