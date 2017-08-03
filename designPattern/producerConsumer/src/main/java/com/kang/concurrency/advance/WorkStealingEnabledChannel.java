package com.kang.concurrency.advance;

import com.kang.concurrency.channel.Channel;

import java.util.concurrent.BlockingDeque;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/22.
 * @Author Healthy
 * @Version
 */
public interface WorkStealingEnabledChannel<P>  extends Channel<P> {

    P take(BlockingDeque<P> preferredQueue) throws InterruptedException;
}
