package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/12.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleRun {

    public static void main(String[] args)  {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (2,2,5, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
        CompletionService completionService =
                new ExecutorCompletionService<String>(threadPoolExecutor);

        SimpleCallable simpleCallable = new SimpleCallable(5000,"simpleCallable");
        SimpleCallable simpleCallable2 = new SimpleCallable(1000,"simpleCallable2");
        SimpleCallable simpleCallable3 = new SimpleCallable(3000,"simpleCallable3");

        completionService.submit(simpleCallable);
        completionService.submit(simpleCallable2);
        completionService.submit(simpleCallable3);

        try {
            for(int i =0;i<3;i++){
                //get result sort by sleeptime
                log.info((String) completionService.take().get());
            }
            Thread.sleep(1000);
            //block alway;
            //log.info((String) completionService.take().get(1,TimeUnit.SECONDS));
            log.info((String) completionService.take().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } /*catch (TimeoutException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }*/
        threadPoolExecutor.shutdown();
    }
}
