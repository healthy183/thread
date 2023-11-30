package com.kang.concurrency.simple;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveAction;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/13.
 * @Author Healthy
 * @Version
 */
@AllArgsConstructor
@Slf4j
public class SimpleRecursiveAction extends RecursiveAction {

    private Integer begin;
    private Integer end;

    @Override
    protected void compute() {
        //log.info(Thread.currentThread().getName()+"--------");
        if(end - begin > 2){
            int middelNum = (end + begin)/2;
            SimpleRecursiveAction leftRecursiveAction = new SimpleRecursiveAction(begin,middelNum);
            SimpleRecursiveAction rightRecursiveAction = new SimpleRecursiveAction(middelNum+1,end);
            this.invokeAll(leftRecursiveAction,rightRecursiveAction);
        }else{
            log.info("thread[{}] begin:[{}] end:[{}]",Thread.currentThread().getName(),begin,end);
        }

    }
}
