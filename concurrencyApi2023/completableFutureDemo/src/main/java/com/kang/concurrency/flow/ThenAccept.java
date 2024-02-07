package com.kang.concurrency.flow;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：消费类，顺序执行
 */
public class ThenAccept {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenAccept(s -> System.out.println(s+" world"));
    }
}
