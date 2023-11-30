package com.kang.thread.phantomRead;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/18.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            log.info(""+number);//有可能输出0，读线程读到了新ready，但读不到number
        }
    }

    public static void main(String[] args) {
        //while(true){ }
            ReaderThread readerThread  = new ReaderThread();
            readerThread.start();
            number = 42;
            ready = true;

    }
}
