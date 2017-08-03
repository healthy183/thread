package com.kang.concurrency.simple;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/25.
 * @Author Healthy
 * @Version
 */
public interface Terminatable {

    void doRun() throws Exception;

    void docleanup(Exception e);

    void terminate();
}
