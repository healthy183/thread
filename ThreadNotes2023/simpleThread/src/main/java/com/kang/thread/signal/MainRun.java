package com.kang.thread.signal;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/5/16.
 */
@Getter
 class  MainRunnable implements  Runnable{
   private  SimpleWaitAndNotify simpleWaitAndNotify;
    public MainRunnable(SimpleWaitAndNotify simpleWaitAndNotify){
        this.simpleWaitAndNotify = simpleWaitAndNotify;
    }
    @Override
    public void run() {
        try {
            simpleWaitAndNotify.show();
            //  java.lang.IllegalMonitorStateException
            //beacuse you did not had the lock;
            simpleWaitAndNotify.wait();
            //int index = new Random().nextInt(5000);
           // Thread.sleep(index);
           // simpleWaitAndNotify.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class MainRun {

    public static void main(String[] args) {

        final  SimpleWaitAndNotify simpleWaitAndNotify = new SimpleWaitAndNotify();
        List<Thread> threadList = new ArrayList<Thread>();
        int count = 0;
        for(int i = 0;i<=count;i++){
            MainRunnable mainRunnable = new MainRunnable(simpleWaitAndNotify);
            Thread thread = new Thread(mainRunnable);
            threadList.add(thread);
        }

        for(int i = 0;i<=count;i++){
            threadList.get(i).start();
        }
    }
}
