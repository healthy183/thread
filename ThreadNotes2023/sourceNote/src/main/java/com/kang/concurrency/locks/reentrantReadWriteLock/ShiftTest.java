package com.kang.concurrency.locks.reentrantReadWriteLock;

public class ShiftTest {

    static final int SHARED_SHIFT   = 16; // 读锁（共享）的移位
    // 读锁（共享）的增量单位：00000000 00000001 00000000 00000000
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT); //1向左移动16位，就是 65536
    // 读锁（共享）的最大获取数量 或 写锁（独占）的最大可重入次数
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1; // 就是 65535
    // 写锁（独占）的掩码：00000000 00000000 11111111 111111111
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1; // 就是 65535

    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }

    static void  exclusiveCountTest(){

        int stat = 0;  // 返回 0
        int i = exclusiveCount(stat); // 1、2则返回0,0则返回0， -1则返回 65535
        System.out.println("exclusiveCount:"+i);

        stat = 65537; // 返回 1
        i = exclusiveCount(stat);
        System.out.println("65537exclusiveCount:"+i);

        stat = SHARED_UNIT;  // SHARED_UNIT 是65536，返回 0
        i = exclusiveCount(stat);
        System.out.println("65536exclusiveCount:"+i);

        stat = 65535; // 返回 65535
        i = exclusiveCount(stat);
        System.out.println("65535exclusiveCount:"+i);

        stat = 65534; // 返回 65534
        i = exclusiveCount(stat);
        System.out.println("65534exclusiveCount:"+i);
    }

    static void  sharedCountTest(){
        int stat = 0;
        int i = sharedCount(stat); // 1、2则返回0,0则返回0， -1则返回 65535,MAX_COUNT是65535
        System.out.println("sharedCount:"+i);

        stat = 65538; // 返回 1
        i = sharedCount(stat);
        System.out.println("65538 sharedCount:"+i);

        stat = 65537; // 返回 1
        i = sharedCount(stat);
        System.out.println("65537 sharedCount:"+i);

        stat = SHARED_UNIT ;  // SHARED_UNIT 是65536，返回 1
        i = sharedCount(stat);
        System.out.println("65536 sharedCount:"+i);

        stat = 65535; // 返回 0
        i = sharedCount(stat);
        System.out.println("65535 sharedCount:"+i);

        stat = 65534; // 返回 0
        i = sharedCount(stat);
        System.out.println("65534 sharedCount:"+i);
    }



    public static void main(String[] args) {
        /*System.out.println("SHARED_UNIT:"+SHARED_UNIT); // 65536
        System.out.println("MAX_COUNT:"+MAX_COUNT); // 65535
        System.out.println("EXCLUSIVE_MASK:"+EXCLUSIVE_MASK); // 65535
        System.out.println("sharedCount:"+(16 >>> SHARED_SHIFT)); // 0
        System.out.println("exclusiveCount:"+(16 & EXCLUSIVE_MASK)); // 16*/
        exclusiveCountTest();
       // sharedCountTest();

    }
}
