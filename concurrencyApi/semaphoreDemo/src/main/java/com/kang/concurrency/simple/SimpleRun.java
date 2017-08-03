package com.kang.concurrency.simple;


/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
public class SimpleRun {
    public static void main(String[] args) {
        SimpleService simpleService = new SimpleService();
        for(int i=0;i<=10;i++){
            new SimpleThread(simpleService).start();
        }
    }
}
