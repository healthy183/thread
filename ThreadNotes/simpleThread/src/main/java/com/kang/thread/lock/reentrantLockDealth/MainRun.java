package com.kang.thread.lock.reentrantLockDealth;

/**
 * Created by Administrator on 2016/6/3.
 */
class SimpleThread implements Runnable{
    private Lock lock;
    public SimpleThread(Lock lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            lock.lock();
            //lock.unlock(); //if unlock, if will be dealth lock by himself!
            lock.lock();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class MainRun {

    public static void main(String[] args) {
        Lock lock = new Lock();
        SimpleThread simpleThread = new SimpleThread(lock);
        Thread thread = new Thread(simpleThread);
        thread.start();
    }
}
