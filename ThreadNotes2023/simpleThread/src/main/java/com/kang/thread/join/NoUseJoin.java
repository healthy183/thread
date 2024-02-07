package com.kang.thread.join;

import com.kang.thread.SleepTools;

/**
 *类说明：无Join时线程的表现:Goddess、GoddessBoyfriend、主线程随机先后运行
 */
public class NoUseJoin {
	
    static class Goddess implements Runnable {
        private Thread thread;

        public Goddess(Thread thread) {
            this.thread = thread;
        }

        public Goddess() {
        }

        public void run() {
            System.out.println("Goddess开始排队打饭.....");
            try {
                if(thread!=null){
                    thread.join();
                }
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " Goddess打饭完成.");
        }
    }

    static class GoddessBoyfriend implements Runnable {

        public void run() {
            System.out.println("GoddessBoyfriend开始排队打饭.....");
            SleepTools.second(2);//休眠2秒
            System.out.println(Thread.currentThread().getName()
                    + " GoddessBoyfriend打饭完成.");
        }
    }


    /**
     * Goddess、GoddessBoyfriend、主线程随机先后运行
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Thread zhuGe = Thread.currentThread();
        GoddessBoyfriend goddessBoyfriend = new GoddessBoyfriend();
        Thread gbfThread = new Thread(goddessBoyfriend);
        Goddess goddess = new Goddess();
        //Goddess goddess = new Goddess(gbfThread); //如果是子线程，gbfThread.join()就会让gbfThread先执行
        Thread goddessThread = new Thread(goddess);
        goddessThread.start();
        gbfThread.start();
        System.out.println(zhuGe.getName() + " zhuGe开始排队打饭.....");
        //SleepTools.second(2);//让主线程休眠2秒
        System.out.println(zhuGe.getName() + " zhuGe打饭完成.");
    }
}
