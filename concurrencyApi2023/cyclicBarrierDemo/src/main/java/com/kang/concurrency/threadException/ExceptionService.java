package com.kang.concurrency.threadException;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ExceptionService {
    private CyclicBarrier cyclicBarrier;
    public  ExceptionService(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier =  cyclicBarrier;
    }
    public void testRun(){
        try {
            if(Thread.currentThread().getName().equals("ThreadA")){
                Thread.sleep(5000);
                //case 1,main run await forever;
                //throw new RuntimeException("ThreadAException");
                //case 2,broken out all the thread,this thread throw InterruptedException,other BrokenBarrierException
                Thread.currentThread().interrupt();
            }
            log.info("[{}]  had await() ", Thread.currentThread().getName());
            cyclicBarrier.await();
            //case 3,if over time,broken out all the thread,this thread throw TimeoutException,other BrokenBarrierException
            //cyclicBarrier.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
                log.info("InterruptedException! [{}] isBroken?[{}]",
                        Thread.currentThread().getName(),cyclicBarrier.isBroken());
        } catch (BrokenBarrierException e) {
            log.info("BrokenBarrierException! [{}] isBroken?[{}]",
                    Thread.currentThread().getName(),cyclicBarrier.isBroken());
        } /*catch (TimeoutException e) {
            log.info("TimeoutException [{}] isBroken?[{}]",
                    Thread.currentThread().getName(),cyclicBarrier.isBroken());
        }*/
    }
}
