package com.kang.concurrency.threadPoolExecutors;

import java.util.concurrent.atomic.AtomicInteger;

public class ValCheck {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3; // 32-3=29
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    private static int runStateOf(int c)     { return c & ~CAPACITY; }

    private static int workerCountOf(int c)  { return c & CAPACITY; }

    private static int ctlOf(int rs, int wc) { return rs | wc; }

    private static boolean runStateLessThan(int c, int s) {
        return c < s;
    }

    private static boolean runStateAtLeast(int c, int s) {
        return c >= s;
    }

    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }


    public static void main(String[] args) {

        System.out.println((RUNNING)+":RUNNING");
        System.out.println((TERMINATED)+":TERMINATED");
        System.out.println((TIDYING)+":TIDYING");
        System.out.println((STOP)+":STOP");
        System.out.println((SHUTDOWN)+":SHUTDOWN");

        System.out.println("================二进制===============");
        System.out.println(Integer.toBinaryString(RUNNING)+":RUNNING");
        System.out.println(Integer.toBinaryString(TERMINATED)+":TERMINATED");
        System.out.println(Integer.toBinaryString(TIDYING)+":TIDYING");
        System.out.println(Integer.toBinaryString(STOP)+":STOP");
        System.out.println(Integer.toBinaryString(SHUTDOWN)+":SHUTDOWN");

        System.out.println("================RUNNING===============");
        System.out.println(Integer.toBinaryString(runStateOf(RUNNING+1))+":RUNNING");
        System.out.println(Integer.toBinaryString(runStateOf(TERMINATED+2))+":TERMINATED");
        System.out.println(Integer.toBinaryString(runStateOf(TIDYING+3))+":TIDYING");
        System.out.println(Integer.toBinaryString(runStateOf(STOP+4))+":STOP");
        System.out.println(Integer.toBinaryString(runStateOf(SHUTDOWN+5))+":SHUTDOWN");

        System.out.println("================workerCountOf===============");
        System.out.println(Integer.toBinaryString(workerCountOf(RUNNING+1))+":RUNNING");
        System.out.println(Integer.toBinaryString(workerCountOf(TERMINATED+2))+":TERMINATED");
        System.out.println(Integer.toBinaryString(workerCountOf(TIDYING+3))+":TIDYING");
        System.out.println(Integer.toBinaryString(workerCountOf(STOP+4))+":STOP");
        System.out.println(Integer.toBinaryString(workerCountOf(SHUTDOWN+5))+":SHUTDOWN");

        System.out.println("================workerCountOf===============");
        System.out.println((workerCountOf(RUNNING+1))+":RUNNING");
        System.out.println((workerCountOf(TERMINATED+2))+":TERMINATED");
        System.out.println((workerCountOf(TIDYING+3))+":TIDYING");
        System.out.println((workerCountOf(STOP+4))+":STOP");
        System.out.println((workerCountOf(SHUTDOWN+5))+":SHUTDOWN");


        System.out.println("================ctl===============");
        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        System.out.println(ctl);

        ctl = new AtomicInteger(ctlOf(RUNNING+1, 0));
        System.out.println(ctl);

        ctl = new AtomicInteger(ctlOf(RUNNING, 1));
        System.out.println(ctl);


        /*System.out.println(Integer.toBinaryString(RUNNING+2));
      */

       /* ThreadPoolExecutorKang threadPoolExecutorKang
                = new ThreadPoolExecutorKang
                (1,2,
                        2000, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());*/

    }
}
