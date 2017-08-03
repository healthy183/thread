package com.kang.thread.atomics;

import com.kang.thread.common.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/18.
 * @Author Healthy
 * @Version
 */
@ThreadSafe
public class IncreaseAtomic {

    private AtomicInteger atomicTom = new AtomicInteger(0);
    private AtomicInteger atomicTim = new AtomicInteger(0);

    public synchronized AtomicInteger getAtomicTom() {
        return atomicTom;
    }

    public synchronized AtomicInteger getAtomicTim() {
        return atomicTim;
    }

    public void increase(){
        synchronized (this){
            atomicTom.incrementAndGet();
            atomicTim.incrementAndGet();
        }
    }

}
