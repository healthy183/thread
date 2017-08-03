package com.kang.concurrency.channel;

import java.util.concurrent.BlockingQueue;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/22.
 * @Author Healthy
 * @Version
 */
public class BlockingQueueChannel<P> implements Channel<P> {

    private final BlockingQueue<P> blockingQueue;

    public BlockingQueueChannel(BlockingQueue<P> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public P take() throws InterruptedException {
        return blockingQueue.take();
    }

    @Override
    public void put(P p) throws InterruptedException {
         blockingQueue.put(p);
    }
}
