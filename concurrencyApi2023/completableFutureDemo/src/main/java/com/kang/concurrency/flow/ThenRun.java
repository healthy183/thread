package com.kang.concurrency.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：执行操作类
 */
public class ThenRun {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            System.out.println("hi");
            return "hello";
        }).thenRun(() -> System.out.println("hello world"));
        SleepTools.second(2);
    }
}
