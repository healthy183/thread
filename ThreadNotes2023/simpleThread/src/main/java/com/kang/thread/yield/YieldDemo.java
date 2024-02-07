package com.kang.thread.yield;

/*
总结如下几点
1、yield 是一个静态的原生（native）方法。
2、yield 告诉当前正在执行的线程把运行机会交给线程池中拥有相同优先级的线程。
3、yield 不能保证使得当前正在运行的线程迅速转换到可运行的状态。
4、它仅能使一个线程从运行状态转到可运行状态，而不是等待或阻塞状态。
5、无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。*/
public class YieldDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable(), "Thread 1");
        Thread thread2 = new Thread(new MyRunnable(), "Thread 2");

        thread1.start();
        thread2.start();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                // 当 i 等于 3 时，调用 yield() 方法让出 CPU 执行权
                if (i == 3) {
                    Thread.yield();
                }
            }
        }
    }
}
