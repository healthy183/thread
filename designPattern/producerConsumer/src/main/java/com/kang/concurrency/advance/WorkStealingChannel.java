package com.kang.concurrency.advance;

import java.io.File;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/22.
 * @Author Healthy
 * @Version
 */
public class WorkStealingChannel<T> implements  WorkStealingEnabledChannel<T>{

    private final BlockingDeque<T>[] managedQueues;

    public WorkStealingChannel(BlockingDeque<T>[] managedQueues){
        this.managedQueues = managedQueues;
    }

    @Override
    public T take(BlockingDeque<T> preferredQueue) throws InterruptedException {
        BlockingDeque<T> targetQueue = preferredQueue;
        T product = null;
        if(targetQueue != null){
            product = preferredQueue.poll();
        }
        int queueIndex = -1;
        while(product == null){
            //从其他队列获取prodcut
            queueIndex = (queueIndex+1) % managedQueues.length;
            targetQueue = managedQueues[queueIndex];
            product = targetQueue.pollLast();//不阻塞
            if(preferredQueue  == targetQueue){
                break;
            }
        }

        if(product == null){
            //从其他队列获取prodcut
            queueIndex = (int)(System.currentTimeMillis() % managedQueues.length);
            targetQueue = managedQueues[queueIndex];
            product = targetQueue.takeLast();//阻塞
        }
        return product;
    }

    @Override
    public T take() throws InterruptedException {
        return null;
    }

    @Override
    public void put(T t) throws InterruptedException {
        Random randow = new Random();
        int index = randow.nextInt(managedQueues.length-1);
        BlockingDeque targetQueue = managedQueues[index];
        targetQueue.put(t);
    }
}
