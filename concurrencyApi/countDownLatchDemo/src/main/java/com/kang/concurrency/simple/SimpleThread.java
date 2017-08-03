package com.kang.concurrency.simple;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
public class SimpleThread extends  Thread {

    private SimpleService simpleService;

    public SimpleThread(SimpleService simpleService){
        this.simpleService = simpleService;
    }
    @Override
    public void run(){
        simpleService.testRun();
    }
}
