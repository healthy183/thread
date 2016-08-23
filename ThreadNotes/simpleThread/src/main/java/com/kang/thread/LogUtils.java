package com.kang.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/16.
 */
@Slf4j
public class LogUtils {

    public static void showThreadDetail(){
        Thread thread = Thread.currentThread();
      log.info("thread detail:"+thread.getName()+"-"+thread.getId());
    }
}
