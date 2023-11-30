package com.kang.concurrency.invokeAny.simple;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/20.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRunInterrupt {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CallableA callableA = new CallableA(5000,"callableA");
        CallableAllableInterrupt callableb = new CallableAllableInterrupt(500,"callableb");
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
        //executorService.shutdown();
    }
}
