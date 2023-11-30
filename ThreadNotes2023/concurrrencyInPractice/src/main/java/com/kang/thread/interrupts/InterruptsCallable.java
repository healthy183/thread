package com.kang.thread.interrupts;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class InterruptsCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
       /* try {
            //Thread.currentThread().interrupt();
            Thread.sleep(2000);
            return 1;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.info("interrupt!");
            //throw new InterruptedException();
        }
        return 0;*/
        try {
            while(!Thread.currentThread().isInterrupted()){
                Thread.sleep(2000);
            }
        }catch (InterruptedException e) {
                log.info(Throwables.getStackTraceAsString(e));
        }

        return 1;
    }

    public void cancle(){
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        InterruptsCallable interruptsCallable = new InterruptsCallable();
        Future future = executorService.submit(interruptsCallable);
        try {
            interruptsCallable.cancle(); //get的时候抛ExecutionException
            //future.cancel(true);//get的时候抛CancellationException
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }catch (CancellationException e){
            log.info(Throwables.getStackTraceAsString(e));
        }finally {
            future.cancel(true); //取消任务,以防异常时异步任务还继续执行
        }
    }

    public static RuntimeException launderThrowable(Throwable t){
        if(t instanceof  RuntimeException){
            return (RuntimeException) t;
        }else if(t instanceof  Error){
            throw (Error)t;
        }
        throw new IllegalStateException("Not unchecked",t);
    }

    @Deprecated
    public void invalid(){
        InterruptsCallable interruptsCallable = new InterruptsCallable();
        FutureTask<Integer> ft = new FutureTask<Integer>(interruptsCallable);
        ft.run();
        try {
            Integer result =  ft.get();
            log.info("ft.get() result:{}",result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            boolean cancleResult = ft.cancel(true);
            log.info("ft.cancel(true):{}",cancleResult);
        } catch (ExecutionException e) {
            launderThrowable(e);
        }
    }

}
