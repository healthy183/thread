package com.kang.concurrency.deque;

import com.kang.concurrency.factory.simple.SimpleRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/10.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SynchronousQueueTest {


    public static void main(String[] args) throws InterruptedException {
        //simple();
        simpleReject();
    }

    public static void  simple(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,1,1L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());//无界队列
        SimpleRunnable simpleRunnable = new SimpleRunnable();
        threadPoolExecutor.execute(simpleRunnable);
    }

    private static void simpleReject() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,1,1L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        SimpleRunnable simpleRunnable = new SimpleRunnable();

        SimpleRunnable simpleRunnable2 = new SimpleRunnable();

        threadPoolExecutor.execute(simpleRunnable);
        //once throw reject exception,follow core not execute again
        threadPoolExecutor.execute(simpleRunnable2);//reject
        //Thread.sleep(10000);
        log.info("wake up!");
        SimpleRunnable simpleRunnable3 = new SimpleRunnable();
        SimpleRunnable simpleRunnable4 = new SimpleRunnable();
        threadPoolExecutor.execute(simpleRunnable3);//reject
        threadPoolExecutor.execute(simpleRunnable4);//reject

    }

}
