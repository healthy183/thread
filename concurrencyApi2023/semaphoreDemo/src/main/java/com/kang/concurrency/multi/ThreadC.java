package com.kang.concurrency.multi;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
public class ThreadC extends Thread {

    private MultiService multiService;
    public ThreadC(MultiService multiService){
        this.multiService = multiService;
    }

    @Override
    public void run(){
        multiService.get();
    }
}
