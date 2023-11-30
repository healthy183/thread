package com.kang.concurrency.simple;

import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/12.
 * @Author Healthy
 * @Version
 */
public class ExceptionCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        int i =  1/0;
        return "simple";
    }
}
