package com.kang.concurrency.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：运行后记录结果类
 */
public class WhenComplete {

    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((sResult, t) -> {
            System.out.println("sResult:"+sResult); // 非异常情况下返回sResult
            if(t != null){ // 异常情况下t不为null
                System.out.println("whenComplete:"+t.getMessage());
            }
        }).exceptionally(e -> {
            System.out.println("exceptionally:"+e.getMessage());
            return "hello world";
        }).join();
        System.out.println("result:"+result);
    }
}
