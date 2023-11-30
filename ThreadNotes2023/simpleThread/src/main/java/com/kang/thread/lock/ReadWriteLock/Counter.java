package com.kang.thread.lock.ReadWriteLock;

import lombok.Getter;

/**
 * Created by Administrator on 2016/5/19.
 */
@Getter
public class Counter {

    private SimpleReadWriteLock simpleReadWriteLock
            = new SimpleReadWriteLock();
    private int count = 0;

    public void read(){
        try {
            simpleReadWriteLock.lockRead();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            simpleReadWriteLock.unlockRead();
        }
    }

    public void writer(){
        try {
            simpleReadWriteLock.lockWriter();
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            simpleReadWriteLock.unlockWrite();
        }
    }
}
