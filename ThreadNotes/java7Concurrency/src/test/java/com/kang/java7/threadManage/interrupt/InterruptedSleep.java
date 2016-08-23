package com.kang.java7.threadManage.interrupt;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/13.
 */
public class InterruptedSleep extends Thread {

    @Override
    public void run(){
        for(int i =0 ;i<5;i++){
            Date time = new Date();
            System.out.println("sleep time is "+new Date());
            try {
                //TimeUnit.SECONDS.sleep(5);
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                System.err.println("interrupt time is "+new Date());
                e.printStackTrace();
            }
        }
    }



}
