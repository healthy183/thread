package com.kang.java7.threadManage.threadGroup;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/14.
 */
public class MainRun  {

    @Test
    public void groupRun(){

        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result=new Result();
        SearchTask searchTask=new SearchTask(result);
        for (int i=0; i<5; i++) {
            Thread thread=new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Number of Threads: %d\n",threadGroup. activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list();

        Thread[] threads= new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i=0; i<threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
        }

        waitFinish(threadGroup);
        threadGroup.interrupt();
    }

    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount()>9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
