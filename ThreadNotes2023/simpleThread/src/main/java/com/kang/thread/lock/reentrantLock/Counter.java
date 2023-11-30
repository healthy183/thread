package com.kang.thread.lock.reentrantLock;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Counter {

    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;//volatile

    public int inc() {
        try {
            lock.lock();
            ++count;
            dec();
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
