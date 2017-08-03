package com.kang.concurrency.advance;

import com.google.common.base.Throwables;
import com.kang.concurrency.simple.AbstractTerminatableThread;
import com.kang.concurrency.simple.TerminationToken;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/22.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class WorkSteamlingExample {

    private final WorkStealingEnabledChannel<String> channel;
    @Getter
    private final TerminationToken terminationToken = new TerminationToken();

    public WorkSteamlingExample(){
        int cpuCount = Runtime.getRuntime().availableProcessors();
        int consumerCount = cpuCount / 2 + 1;
        BlockingDeque[] blockingDeque = new LinkedBlockingDeque[consumerCount];
        this.channel = new WorkStealingChannel(blockingDeque);
        Consumer[] consumers = new Consumer[consumerCount];
        for(int i = 0;i<consumerCount;i++){
            blockingDeque[i] = new LinkedBlockingDeque<String>();
            consumers[i] = new Consumer(channel,terminationToken,blockingDeque[i]);
        }

        for(int i = 0;i<cpuCount;i++){
            new Producer().start();
        }

        for(int i = 0;i<consumerCount;i++){
            consumers[i].start();
        }

    }

    private  class Producer extends AbstractTerminatableThread{
        private int i = 0;
        @Override
        public void doRun() throws Exception {
            Integer val = new Random(99999).nextInt();
            channel.put(String.valueOf(val));
            terminationToken.reservation.incrementAndGet();
            log.info("product:"+val);
        }
        @Override
        public void docleanup(Exception e) {
        }
    }
}









