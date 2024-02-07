package com.kang.concurrency.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：取最快转换类
 *
 * applyToEither 会将已经完成任务的执行结果作为所提供函数的参数，且该方法有返回值
 */
public class ApplyToEither {

    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SleepTools.second(2);
            return "hello world";
        }), s -> s).join();
        System.out.println(result);
    }
}
