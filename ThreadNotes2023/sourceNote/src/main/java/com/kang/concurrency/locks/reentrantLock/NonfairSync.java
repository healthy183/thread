package com.kang.concurrency.locks.reentrantLock;


public  class NonfairSync extends Sync {
    private static final long serialVersionUID = 7316153563782823691L;

    /**
     * Performs lock.  Try immediate barge, backing up to normal
     * acquire on failure.
     */
    final void lock() {
        //请求说，except是0表示无占用，1表示更新后锁被占有
        if (compareAndSetState(0, 1)){
            setExclusiveOwnerThread(Thread.currentThread());
        }else{
            acquire(1);
        }
    }
    // 尝试获取锁
    protected final boolean tryAcquire(int acquires) {
        return nonfairTryAcquire(acquires);
    }
}

