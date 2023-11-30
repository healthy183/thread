package com.kang.thread.startAndRun;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/15.
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) throws InterruptedException {

        RunnableThread runnableThread = new RunnableThread();
        Thread thead = new Thread(runnableThread);
        thead.start();//new a Thread whitch threadName is 0 to run;
        //thead.start();//IllegalThreadStateException,can not run again!
        //Thread.sleep(2000);
        thead.run();//just run  through current thread(main);
    }
}
