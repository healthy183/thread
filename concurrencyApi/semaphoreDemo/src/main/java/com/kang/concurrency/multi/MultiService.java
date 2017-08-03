package com.kang.concurrency.multi;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MultiService {

    volatile  private  Semaphore setSemaphore = new Semaphore(10);
    volatile  private Semaphore getSemaphore = new Semaphore(20);
    volatile  private ReentrantLock lock = new ReentrantLock();
    volatile  private Condition setCondition = lock.newCondition();
    volatile  private Condition getCondition = lock.newCondition();
    volatile private Object[]  productPosition = new Object[4];

    public boolean isEmpty(){
        boolean isEmpty  = true;
        for(int i = 0;i<productPosition.length;i++){
            if(productPosition[i] != null){
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public boolean isFull(){
        boolean isFull  = true;
        for(int i = 0;i<productPosition.length;i++){
            if(productPosition[i] == null){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public void set(){
        try {
            setSemaphore.acquire();// max 10  wait  to product;
            lock.lock();
            while(isFull()){
                setCondition.await();
            }
            for(int i = 0;i<productPosition.length;i++){
                if(productPosition[i] == null){
                    productPosition[i] = "data";
                    log.info("[{}-{}] set index[{}] data!",
                            Thread.currentThread().getName(),Thread.currentThread().getId(),i);
                    break;
                }
            }
            getCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
           log.info(Throwables.getStackTraceAsString(e));
        }finally {
            setSemaphore.release();
        }
    }

    public void get(){
        try {
            getSemaphore.acquire();//max 16  wait  to  consume
            lock.lock();
            while(isEmpty()){
                getCondition.await();
            }
            for(int i = 0;i<productPosition.length;i++){
                if(productPosition[i] != null){
                    productPosition[i] = null;
                    Thread.currentThread().sleep(2000);
                    log.info("[{}-{}] get index[{}] data!",
                            Thread.currentThread().getName(),Thread.currentThread().getId(),i);
                    break;
                }
            }
            setCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }finally {
            getSemaphore.release();
        }
    }



}
