package com.kang.thread.lock.reentrantLock;

import lombok.Getter;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Counter {

    private ReentrantLockMyself lock = new ReentrantLockMyself();

    @Getter
    private int count = 0;//volatile

    public int inc() {
        try {
            lock.lock();
            ++count;
            //dec();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return count;
    }

    public synchronized int dec()  {
        try {
            lock.lock();
            --count;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return count;
    }
}
