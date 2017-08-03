package com.kang.concurrency.simple;

import jdk.nashorn.internal.ir.Terminal;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/25.
 * @Author Healthy
 * @Version
 */
public abstract class AbstractTerminatableThread extends  Thread implements Terminatable {

    protected final TerminationToken terminationToken;

    public AbstractTerminatableThread(){
        this(new TerminationToken());
    }

    public AbstractTerminatableThread(TerminationToken terminationToken){
        this.terminationToken = terminationToken;
        terminationToken.register(this);
    }


    /**
     * 实现执行停止线程所需逻辑
     */
    protected void doTerminate() {

    }

    @Override
    public void terminate() {
        terminationToken.setToshutdown();
        try{
            doTerminate();
        }finally {
            if(terminationToken.reservation.get() <= 0){
                super.interrupt();
            }
        }
    }

    public void terminate(Boolean waitUtilThreadTerminated) {
        terminate();
        if(waitUtilThreadTerminated){
            try{
                this.join();
            }catch (InterruptedException ex){
              Thread.currentThread().interrupt();
            }finally {

            }
        }
    }

    @Override
    public void run() {
        Exception exception = null;
        try{
            while(true){
                if(terminationToken.isToshutdown()
                        && terminationToken.reservation.get() <= 0){
                    break;
                }
                doRun();
            }
        }catch(Exception e){
            exception = e;
        }finally {
            try{
                docleanup(exception);
            }finally {
                terminationToken.notifyThreadTermination(this);
            }
        }
    }

    @Override
    public void interrupt() {
        terminate();
    }
}
