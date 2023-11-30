package com.kang.concurrency.lockCondition;

import com.google.common.base.Throwables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
public class ListPool {

    private  int poolMaxSize = 3;
    private int semaphorePermits = 5;
    private List<String> list = new ArrayList<String>();
    private Semaphore semaphore = new Semaphore(semaphorePermits);
    private ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public ListPool(){
        for(int i = 0;i<poolMaxSize;i++){
            list.add("i"+i);
        }
    }

    public String get(){
        String getString  = null;
        try {
            semaphore.acquire();
            lock.lock();
            while(list.size() == 0){
                condition.await();
            }
            getString = list.remove(0);
            Thread.currentThread().sleep(2000);
            lock.unlock();
        } catch (InterruptedException e) {
            Throwables.getStackTraceAsString(e);
        }
        return getString;
    }

    public void put(String  val){
        lock.lock();
        list.add(val);
        condition.signalAll();
        lock.unlock();
        semaphore.release();
    }

}
