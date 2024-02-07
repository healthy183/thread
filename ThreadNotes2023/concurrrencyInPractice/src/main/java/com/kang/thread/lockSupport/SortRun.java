package com.kang.thread.lockSupport;

import java.util.concurrent.locks.LockSupport;

public class SortRun {

    private void printA(Thread thread){
        try {
            Thread.sleep(20);
            System.out.print("A");
            LockSupport.unpark(thread);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void printB(Thread thread){
        try {
            Thread.sleep(10);
            LockSupport.park();
            System.out.print("B");
            LockSupport.unpark(thread);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printC(){
        try {
            Thread.sleep(5);
            LockSupport.park();
            System.out.print("C");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        SortRun t = new SortRun();
        Thread tC = new Thread(t::printC);
        Thread tB = new Thread(()->t.printB(tC));
        Thread tA = new Thread(()->t.printA(tB));
        tA.start();
        tB.start();
        tC.start(); // 顺序打印出ABC

    }

}
