package com.kang.concurrency.lockCondition;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class LcThread extends  Thread {
    private ListPool listPool;
    public LcThread( ListPool listPool){
        this.listPool = listPool;
    }
    @Override
    public void run(){
        log.info("[{}-{}] try to  get val!",Thread.currentThread().getName(),Thread.currentThread().getId());
        String val  = listPool.get();
        log.info("[{}-{}] have get val[{}]!",Thread.currentThread().getName(),Thread.currentThread().getId(),val);
        listPool.put(val);
    }
}
