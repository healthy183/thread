package com.kang.concurrency.simple;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/23.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRun {

    ExecutorService pool = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        MainRun mainRun = new MainRun();
        mainRun.sendMsg("msg");
        mainRun.sendMsg("msg1");
        mainRun.sendMsg("msg2");
    }

    public void sendMsg(String info){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                int id = ThreadSpecifisSecureRandom.threadSpecifisSecureRandom.nextInt(9999);
                mainDo(id,info);
            }
        };
        pool.submit(runnable);
    }

    private void mainDo(int id,String info){
            log.info("[{}]:[{}]",id,info);
    }

}
