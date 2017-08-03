package com.kang.concurrency.advance;

import com.google.common.base.Throwables;
import com.kang.concurrency.simple.AbstractTerminatableThread;
import com.kang.concurrency.simple.TerminationToken;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/23.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class Consumer extends AbstractTerminatableThread {

    private final BlockingDeque<String> workDeque;
    WorkStealingEnabledChannel<String> channel;

    public Consumer(WorkStealingEnabledChannel<String> channel, TerminationToken terminationToken, BlockingDeque<String> workDeque){
        super(terminationToken);
        this.channel = channel;
        this.workDeque = workDeque;
    }

    @Override
    public void doRun() throws Exception {
        String product = channel.take(workDeque);
        try{
            log.info("cunsumer:"+product);
            Thread.sleep(1000);//模拟处理1秒
        }catch(InterruptedException e){
            log.info(Throwables.getStackTraceAsString(e));
        }finally {
            terminationToken.reservation.decrementAndGet();
        }
    }

    @Override
    public void docleanup(Exception e) {

    }
}
