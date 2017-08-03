package com.kang.thread.cancalablesocket;

import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/24.
 * @Author Healthy
 * @Version
 */
public class CancellingExecutor extends ThreadPoolExecutor {
    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                              TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if(callable instanceof CancellableTask){
            return ((CancellableTask)callable).newTask();
        }else{
            return super.newTaskFor(callable);
        }
    }
}
