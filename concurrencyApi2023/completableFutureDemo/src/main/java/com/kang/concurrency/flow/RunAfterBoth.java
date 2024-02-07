package com.kang.concurrency.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：运行后执行类
 * 两任务组合-都要完成
 * runAfterBothAsync : 不获取结果并处理新任务
 */
public class RunAfterBoth {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            int second = 1 ;
            System.out.println("r1睡"+second+"开始");
            SleepTools.second(second);
            System.out.println("r1睡"+second+"s醒了");
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            int second = 2 ;
            System.out.println("r2睡"+second+"开始");
            SleepTools.second(second);
            System.out.println("r2睡"+second+"s醒了");
            return "s2";
        }), ()->{
            System.out.println("你们执行完了，到我hello world");
        });
        SleepTools.second(3);
    }
}
