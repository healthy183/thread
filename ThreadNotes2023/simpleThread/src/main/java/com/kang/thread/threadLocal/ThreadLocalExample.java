package com.kang.thread.threadLocal;

/**
 * Created by Administrator on 2016/5/16.
 */
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable{
        private ThreadLocal<Integer>  threadLocal
                = new ThreadLocal<Integer>();
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            int val = (int)(Math.random() * 100D);
            System.out.println("thread detail:"+thread.getName()+"-"+thread.getId()+":"+val);
            threadLocal.set((int)(val));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread detail:"+thread.getName()+"-"+thread.getId()+":"+threadLocal.get());
        }
    }

    public static void main(String[] args) {

        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
