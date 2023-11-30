package com.kang.thread.create;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/15.
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) throws InterruptedException {
        //threadRun();
        runnableRun();
    }
    public static void  threadRun() throws InterruptedException {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
        Thread.sleep(1110);
        extendsThread.run();
        extendsThread.run();
        log.info("threadRun end!");
    }


    public static void  runnableRun() throws InterruptedException {
        RunnableThread runnableThread = new RunnableThread();
        Thread thead = new Thread(runnableThread);
        thead.start();//new a Thread whitch threadName is 0 to run;
        /**
            thead.run() will not work after add this code,
            because the thread target will be clear before thread exits;
            more information see source thread.run() and  thread.exit();
         **/
        Thread.sleep(1110);
        thead.run();//just run  through current thread(main);
        thead.run();
        log.info("runnableRun end!");
    }
}
