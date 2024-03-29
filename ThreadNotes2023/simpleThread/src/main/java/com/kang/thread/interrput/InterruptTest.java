package com.kang.thread.interrput;

import java.util.concurrent.locks.LockSupport;

public class InterruptTest {

    public static void main(String[] args) {
        System.out.println("线程开始状态1"+Thread.currentThread().isInterrupted()); // false
        // 为线程打中断标记
        Thread.currentThread().interrupt();
        // 当前线程状态是否中断，上一行打中断标记后就是true
        System.out.println("线程打中断标记状态2"+Thread.currentThread().isInterrupted());   // true
        //获取线程中断状态true，并清除
        boolean interrupted = Thread.interrupted();
        System.out.println("中断标记"+interrupted);  // true
        // 清除完中断标记，中断状态false
        System.out.println("线程清除中断标记后状态3"+Thread.currentThread().isInterrupted());  // false

        // 再拿一次
        boolean interrupted4 = Thread.interrupted();
        System.out.println("中断标记4"+interrupted4);  // false

        LockSupport.park(); // 线程中断过,即执行Thread.currentThread().interrupt(),这里就不会阻塞
        boolean interrupted5 = Thread.interrupted(); //
        System.out.println("继续运行"+interrupted5);
    }
}
