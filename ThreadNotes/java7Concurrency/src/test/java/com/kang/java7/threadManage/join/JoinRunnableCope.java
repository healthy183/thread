package com.kang.java7.threadManage.join;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/13.
 */
public class JoinRunnableCope implements  Runnable {
    @Override
    public void run() {
        try {
            System.out.println("time is "+ new Date());
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
