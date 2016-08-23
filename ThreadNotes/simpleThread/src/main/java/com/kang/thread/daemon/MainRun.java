package com.kang.thread.daemon;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/15.
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) throws InterruptedException {

        RunnableThread runnableThread = new RunnableThread();
        Thread thead = new Thread(runnableThread);
        //main thread did not care any daemon thread,
        // so RunnableThread will be kill even if is running!
        thead.setDaemon(true);
        thead.start();//new a Thread whitch threadName is 0 to run;
        log.info("end");
    }
}
