package com.kang.concurrency.remove;

import com.kang.concurrency.rejectPolicy.PolicyRunnable;
import com.kang.concurrency.shutdown.ShutdownRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/11.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class RemoveTest {


    public static void main(String[] args) throws InterruptedException {
        PolicyRunnable policyRunnable = new PolicyRunnable("policyRunnable");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,1,0L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(2));
        threadPoolExecutor.execute(policyRunnable);

        PolicyRunnable policyRunnable2 = new PolicyRunnable("policyRunnable2");
        threadPoolExecutor.execute(policyRunnable2);

        PolicyRunnable policyRunnable3 = new PolicyRunnable("policyRunnable3");
        threadPoolExecutor.execute(policyRunnable3);
        threadPoolExecutor.remove(policyRunnable3);

        PolicyRunnable policyRunnable4 = new PolicyRunnable("policyRunnable4");
        threadPoolExecutor.submit(policyRunnable4);//submit thread can not be removed!
        threadPoolExecutor.remove(policyRunnable4);

        threadPoolExecutor.shutdown();
    }

}
