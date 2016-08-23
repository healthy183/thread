package com.kang.thread.synchronizeds;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2016/5/15.
 */
@Getter
@Slf4j
public class SynchronizedClass {
    private int count;
    private int lumpCount;

    public synchronized   void add(int value){
        this.count = count + value;
    }

    public void add(int lumpCount,int count){
        this.count = this.count + count;
        synchronized(this){
            this.lumpCount = this.lumpCount + lumpCount;
        }
    }
}
