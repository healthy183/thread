package com.kang.concurrency.locks.reentrantLock;

public class FairSync extends  Sync {
    private static final long serialVersionUID = -3000897897090466540L;

    final void lock() {
        acquire(1);
    }

    /**
     * 尝试获取锁
     * Fair version of tryAcquire.  Don't grant access unless
     * recursive call or no waiters or is first.
     */
    protected final boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        // 获取当前锁状态
        int c = getState();
        if (c == 0) { // 表示可以没有占用
            //  hasQueuedPredecessors()
            //  1，有没有其他线程在等待，没的话再往下执行抢锁；
            //  2，如果当前线程排第一位也继续往下执行抢锁；
            if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } // 如果是当前线程则状态state+1
        else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0)  // int的max+1就变成负数，理论上不会发生
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
}

