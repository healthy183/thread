package com.kang.concurrency.factory.simple;

import java.util.concurrent.ThreadFactory;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/9.
 * @Author Healthy
 * @Version
 */
public class SimpleFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("creteBySimple");
        return thread;
    }
}
