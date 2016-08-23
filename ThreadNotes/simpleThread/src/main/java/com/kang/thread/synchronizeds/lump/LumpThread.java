package com.kang.thread.synchronizeds.lump;

import com.kang.thread.synchronizeds.SynchronizedClass;

/**
 * Created by Administrator on 2016/5/15.
 */
public class LumpThread  implements  Runnable{

    private SynchronizedClass syn;
    private int count;
    private int lumpCount;
    public LumpThread(SynchronizedClass syn,int count,int lumpCount){
        this.syn = syn;
        this.count = count;
        this.lumpCount = lumpCount;
    }

    @Override
    public void run() {
        syn.add(lumpCount,count);
    }
}
