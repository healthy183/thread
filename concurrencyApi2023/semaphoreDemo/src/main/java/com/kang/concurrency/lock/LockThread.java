package com.kang.concurrency.lock;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
public class LockThread extends  Thread {

    private LockService lockService;

    public LockThread(LockService lockService){
        this.lockService =lockService;
    }
    @Override
    public void run(){
        lockService.sayHello();
    }

}
