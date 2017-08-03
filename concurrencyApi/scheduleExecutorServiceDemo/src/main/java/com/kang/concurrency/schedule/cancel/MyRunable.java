package com.kang.concurrency.schedule.cancel;

import com.google.common.base.Throwables;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Executor;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/1.
 * @Author Healthy
 * @Version
 */
@Data
@Slf4j
@ToString
public class MyRunable implements  Runnable {

    private String userName;
    public MyRunable(String userName){
        this.userName = userName;
    }

    @Override
    public void run() {
        try{
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    log.info("{} had interrupted!",userName);
                    throw new InterruptedException();
                }else{
                    log.info("[{}] is running!",userName);
                    Random random = new Random();
                    random.nextInt();
                    random.nextInt();
                    random.nextInt();
                }
            }
        }catch(Exception e){
            log.info("[{}] exception cause\n[{}]!",userName,
                    Throwables.getStackTraceAsString(e));
        }
    }
}
