package com.kang.concurrency.deque;

import com.kang.concurrency.factory.simple.SimpleRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/10.
 * @Author Healthy
 * @Version
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        //simple();
        simpleReject();
    }

    public static void  simple(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,1,1L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(2));
        SimpleRunnable simpleRunnable = new SimpleRunnable();
        SimpleRunnable simpleRunnable2 = new SimpleRunnable();
        threadPoolExecutor.execute(simpleRunnable);
        threadPoolExecutor.execute(simpleRunnable2);
    }

    private static void simpleReject() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,1,1L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(2));
        SimpleRunnable simpleRunnable = new SimpleRunnable();
        SimpleRunnable simpleRunnable2 = new SimpleRunnable();
        SimpleRunnable simpleRunnable3 = new SimpleRunnable();
        SimpleRunnable simpleRunnable4 = new SimpleRunnable();
        threadPoolExecutor.execute(simpleRunnable);
        threadPoolExecutor.execute(simpleRunnable2);
        threadPoolExecutor.execute(simpleRunnable3);
        threadPoolExecutor.execute(simpleRunnable4);//reject

    }
}
