package com.kang.concurrency.flow;
import com.kang.concurrency.tools.SleepTools;
import java.util.concurrent.CompletableFuture;

/**
 * 类说明：取最快运行后执行类
 * 两个任务有一个执行完成，不需要获取future的结果，也没有返回值。
 */
public class RunAfterEither {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            int second = 1 ;
            System.out.println("r1睡"+second+"开始");
            SleepTools.second(second);
            System.out.println("r1睡"+second+"s醒了");
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            int second = 2 ;
            System.out.println("r2睡"+second+"开始");
            SleepTools.second(second);
            System.out.println("r2睡"+second+"s醒了");
            return "s2";
        }), () -> System.out.println("有任务执行完了，到我hello world"));
        SleepTools.second(3);
    }
}
