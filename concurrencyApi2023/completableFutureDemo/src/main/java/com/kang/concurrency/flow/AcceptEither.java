package com.kang.concurrency.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：取最快消费类,即取最快返回线程的结果
 * acceptEither 将已经完成任务的执行结果作为方法入参，但是无返回值
 */
public class AcceptEither {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            SleepTools.second(2);
            return "hello world";
        }), (s)-> System.out.println("输出result:"+s));
        SleepTools.second(3);
    }
}
