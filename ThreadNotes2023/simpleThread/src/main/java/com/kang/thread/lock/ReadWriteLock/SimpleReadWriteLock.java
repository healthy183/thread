package com.kang.thread.lock.ReadWriteLock;

/**
 * Created by Administrator on 2016/5/19.
 */
public class SimpleReadWriteLock {

    private int readers = 0;
    private int writer = 0;
    private int writerRequests = 0;

    public synchronized  void lockRead() throws InterruptedException {
        while(writer > 0 || writerRequests > 0){
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized  void lockWriter() throws InterruptedException{
        writerRequests++;
        while(readers > 0 || writer > 0){
            wait();
        }
        writerRequests--;
        writer++;
    }

    public synchronized  void unlockWrite(){
        writer--;
        notifyAll();
    }

}
