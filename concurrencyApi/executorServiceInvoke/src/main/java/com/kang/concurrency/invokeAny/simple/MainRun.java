package com.kang.concurrency.invokeAny.simple;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/20.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRun {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CallableA callableA = new CallableA(10000,"callableA");
        CallableA callableb = new CallableA(5000,"callableb");
        List<Callable<String>> list = new ArrayList<Callable<String>>();
        list.add(callableA);
        list.add(callableb);
        try {
            String str =  executorService.invokeAny(list);
            log.info("str:{}",str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
