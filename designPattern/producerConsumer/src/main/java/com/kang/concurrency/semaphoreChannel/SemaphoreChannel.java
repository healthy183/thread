package com.kang.concurrency.semaphoreChannel;

import com.kang.concurrency.channel.Channel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/22.
 * @Author Healthy
 * @Version
 */
public class SemaphoreChannel<P> implements Channel<P> {

    private final BlockingQueue<P> blockingQueue;
    private final Semaphore semaphore;

    public SemaphoreChannel(BlockingQueue<P> blockingQueue,Integer semaphoreLimit){
        this.blockingQueue = blockingQueue;
        this.semaphore = new Semaphore(semaphoreLimit);
    }

    @Override
    public P take() throws InterruptedException {
        return blockingQueue.take();
    }

    @Override
    public void put(P p) throws InterruptedException {
        semaphore.acquire();
        try{
            blockingQueue.put(p);
        }finally {
            semaphore.release();
        }
    }

}
