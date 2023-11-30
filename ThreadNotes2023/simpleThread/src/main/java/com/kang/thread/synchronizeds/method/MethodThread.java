package com.kang.thread.synchronizeds.method;

import com.kang.thread.synchronizeds.SynchronizedClass;

/**
 * Created by Administrator on 2016/5/15.
 */
public class MethodThread implements  Runnable {
    private SynchronizedClass syn;
    private int value;
    public MethodThread(SynchronizedClass syn,int value){
        this.syn = syn;
        this.value = value;
    }

    @Override
    public void run() {
        syn.add(value);
    }
}
