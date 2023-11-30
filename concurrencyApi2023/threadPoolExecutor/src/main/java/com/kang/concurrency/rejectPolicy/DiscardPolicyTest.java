package com.kang.concurrency.rejectPolicy;


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
public class DiscardPolicyTest {

    public static void main(String[] args) throws InterruptedException {
        simpleReject();
    }

    private static void simpleReject() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,1,1L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(2)
                ,new ThreadPoolExecutor.DiscardPolicy());
        PolicyRunnable policyRunnable = new PolicyRunnable("policyRunnable");
        PolicyRunnable policyRunnable2 = new PolicyRunnable("policyRunnabl2");
        PolicyRunnable policyRunnable3 = new PolicyRunnable("policyRunnabl3");
        PolicyRunnable policyRunnable4 = new PolicyRunnable("policyRunnabl4");
        PolicyRunnable policyRunnable5 = new PolicyRunnable("policyRunnable5");
        threadPoolExecutor.execute(policyRunnable);
        Thread.sleep(1000);
        threadPoolExecutor.execute(policyRunnable2);
        Thread.sleep(1000);
        threadPoolExecutor.execute(policyRunnable3);
        Thread.sleep(1000);
        threadPoolExecutor.execute(policyRunnable4);//give up the thread
        Thread.sleep(1000);
        threadPoolExecutor.execute(policyRunnable5);//give up the thread
        threadPoolExecutor.shutdown();
        
    }
}
