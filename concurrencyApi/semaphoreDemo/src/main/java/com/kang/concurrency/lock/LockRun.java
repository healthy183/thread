package com.kang.concurrency.lock;


/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
public class LockRun {
    public   static void main(String[] args) {
        LockService lockService = new LockService();
        for(int i=0;i<=10;i++){
            new LockThread(lockService).start();
        }
    }
}
