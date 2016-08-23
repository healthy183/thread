package com.kang.thread.lock.reentrantLock;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ReentrantLock {
    boolean isLocked = false;
    Thread lockBy = null;
    int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callinggThread = Thread.currentThread();

        while (isLocked && lockBy != callinggThread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockBy = callinggThread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
