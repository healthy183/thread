package com.kang.concurrency.threadException;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
public class ExceptionThread extends Thread {

    private ExceptionService exceptionService;

    public ExceptionThread(ExceptionService exceptionService){
        this.exceptionService = exceptionService;
    }

    @Override
    public void run(){
        exceptionService.testRun();
    }
}
