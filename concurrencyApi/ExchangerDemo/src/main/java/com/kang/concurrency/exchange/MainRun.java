package com.kang.concurrency.exchange;

import java.util.concurrent.Exchanger;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
public class MainRun {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        ThreadA threadA = new ThreadA(exchanger);
        threadA.setName("threadA");
        ThreadB threadB = new ThreadB(exchanger);
        threadB.setName("threadb");
        threadA.start();
        threadB.start();
    }
}
