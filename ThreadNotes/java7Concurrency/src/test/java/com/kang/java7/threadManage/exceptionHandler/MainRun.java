package com.kang.java7.threadManage.exceptionHandler;

import org.junit.Test;

/**
 * Created by Administrator on 2016/7/13.
 */

//建立异常控制器,控制线程的运行期异常
public class MainRun {

    @Test
    public void testException(){
        Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
    }
