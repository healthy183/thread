package com.kang.concurrency.channel;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/22.
 * @Author Healthy
 * @Version
 */
public interface Channel<P> {

    public P take() throws  InterruptedException;

    public void put(P p) throws  InterruptedException;
}
