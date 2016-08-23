package com.kang.thread.synchronizeds.method;

import com.kang.thread.synchronizeds.SynchronizedClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/15.
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;i<=5;i++){
            run();
        }
        //run();
    }

    private static void run() throws InterruptedException {
        SynchronizedClass synchronizedClass = new SynchronizedClass();
        List<Thread> threads = new ArrayList<Thread>();

        for(int i = 0;i<=10000;i++){
            Thread thread = new Thread(new MethodThread(synchronizedClass,i));
            threads.add(thread);
        }
        for(Thread thread : threads){
            thread.start();
        }

        Thread.sleep(5000);
        log.info("synchronizedClass is "+synchronizedClass.getCount());
    }
}
