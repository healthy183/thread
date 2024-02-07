package com.kang.concurrency;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：get和join方法的区别
 */
public class JoinAndGet {
    public static void main(String[] args)  {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
           int i = 1/0;
           return 100;
        });

        // t.ExecutionException 抛出的是经过检查的异常，ExecutionException, InterruptedException
        // 需要用户手动处理（抛出或者 try catch）
        /*try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }*/

        future.join();  // CompletionException 抛出的是uncheck异常（即RuntimeException),不会强制开发者抛出
    }
}
