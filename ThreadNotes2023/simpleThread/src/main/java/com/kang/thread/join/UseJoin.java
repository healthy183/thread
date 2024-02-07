package com.kang.thread.join;

import com.kang.thread.SleepTools;

/**
 *类说明：演示Join（）方法的使用
 */
public class UseJoin {
	
    static class Goddess implements Runnable {
        private Thread goddessBoyFriednThread;

        public Goddess(Thread thread) {
            this.goddessBoyFriednThread = thread;
        }

        public Goddess() {
        }

        public void run() {
            System.out.println("Goddess开始排队打饭.....");
            try {
                if(goddessBoyFriednThread !=null) {
                    goddessBoyFriednThread.join();
                }
            } catch (InterruptedException e) {
            }
            SleepTools.second(2);//休眠2秒
            System.out.println(Thread.currentThread().getName()
                    + " Goddess打饭完成.");
        }
    }

    static class GoddessBoyfriend implements Runnable {

        public void run() {
            SleepTools.second(2);//休眠2秒
            System.out.println("GoddessBoyfriend开始排队打饭.....");
            System.out.println(Thread.currentThread().getName()
                    + " GoddessBoyfriend打饭完成.");
        }
    }

    /**
     * 执行顺序： GoddessBoyfriend、Goddess、主线程
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Thread zhuGe = Thread.currentThread();
        GoddessBoyfriend goddessBoyfriend = new GoddessBoyfriend(); // GoddessBoyfriend是Runnable
        Thread gbfThread = new Thread(goddessBoyfriend);
        Goddess goddess = new Goddess(gbfThread); // Goddess是Runnable
        Thread goddessThread = new Thread(goddess);
        goddessThread.start();
        gbfThread.start();
        System.out.println(" .....");
        goddessThread.join();
        SleepTools.second(2);//让主线程休眠2秒
        System.out.println(zhuGe.getName() + " zhuGe打饭完成.");
    }
}
