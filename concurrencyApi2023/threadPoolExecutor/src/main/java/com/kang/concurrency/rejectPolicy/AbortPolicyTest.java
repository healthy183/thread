package com.kang.concurrency.rejectPolicy;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/10.
 * @Author Healthy
 * @Version
 */
public class AbortPolicyTest {

    public static void main(String[] args) {
        simpleReject();
    }

    private static void simpleReject() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,1,1L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(2)
                ,new ThreadPoolExecutor.AbortPolicy());
        PolicyRunnable policyRunnable = new PolicyRunnable("policyRunnable");
        PolicyRunnable policyRunnable2 = new PolicyRunnable("policyRunnable2");
        PolicyRunnable policyRunnable3 = new PolicyRunnable("policyRunnable3");
        PolicyRunnable policyRunnable4 = new PolicyRunnable("policyRunnable4");
        threadPoolExecutor.execute(policyRunnable);
        threadPoolExecutor.execute(policyRunnable2);
        threadPoolExecutor.execute(policyRunnable3);
        threadPoolExecutor.execute(policyRunnable4);//reject and throws exception
        threadPoolExecutor.shutdown();//invalid code

    }
}
