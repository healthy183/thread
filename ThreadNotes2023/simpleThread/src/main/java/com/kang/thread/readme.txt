记录以下简单的线程用法
1,simpleThread
-| com.kang.thread
   -|atomic:  atom class  http://ifeve.com/java-atomic/
   -|create: the different between extends and implements;
   -|daemon:  daemon thread demo(守护线程)
   -|lock
     -|nestedMonitorLockout 嵌套管程锁死 demo
     -|fairLock : a fail(公平) lock demo
     -|ReadWriteLock: simple  ReadWriteLock demo;
     -|reentrantLock  : ReentrantLock source code;
     -|reentrantLockDealth  : a dealth lock without ReentrantLock
     -|simpleLock : a simple lock demo;
     -|slippedConditions 串行条件脏读
   -|signal: thread signal include  wait(),notify()和notifyAll();
   -|startAndRun: the different between start() and run();
   -|synchronizeds:
     -|lump: Synchronized  lump
     -|method: Synchronized method
   -|threadLocal: only own thread can visit the val even if the same threadLocal object;
                  but what the different between threadLocal and volatile?volatile seem unsafe;
