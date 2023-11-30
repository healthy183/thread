package com.kang.thread.cancalablesocket;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/24.
 * @Author Healthy
 * @Version
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}




