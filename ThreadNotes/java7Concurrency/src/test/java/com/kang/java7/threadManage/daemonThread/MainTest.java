package com.kang.java7.threadManage.daemonThread;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Administrator on 2016/7/13.
 *
 * 当守护线程是程序里唯一在运行的线程时，JVM会结束守护线程并终止程序。
 */
public class MainTest {

    @Test
    public void testRun(){

        Deque<Event> deque=new ArrayDeque<Event>();
        WriterTask writer=new WriterTask(deque);
        for (int i=0; i<3; i++){
            Thread thread=new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner=new CleanerTask(deque);
        cleaner.start();
    }
}
