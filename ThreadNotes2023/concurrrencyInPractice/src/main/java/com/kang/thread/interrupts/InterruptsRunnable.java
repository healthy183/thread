package com.kang.thread.interrupts;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class InterruptsRunnable implements  Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();//继续向外说明此线程已经interrupt
            log.info("interrupt!");//依然会执行
        }
    }

    public static void main(String[] args) {
        InterruptsRunnable interruptsRunnable = new InterruptsRunnable();
        Thread thread = new Thread(interruptsRunnable);
        thread.start();
        thread.interrupt();
        try {
            Thread.sleep(2000);
            //interrupt()不具传播性,这怎么玩？
            if(thread.isInterrupted()){
                System.out.println("thead had Interrupted");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
