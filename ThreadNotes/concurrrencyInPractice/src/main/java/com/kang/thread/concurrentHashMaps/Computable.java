package com.kang.thread.concurrentHashMaps;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
public interface Computable<A,V> {

    V compute(A arg) throws  InterruptedException;
}
