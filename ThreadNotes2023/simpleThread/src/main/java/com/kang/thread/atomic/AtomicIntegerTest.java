package com.kang.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2016/6/17.
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger(1);
    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());//val +1 ,but return the old val;
        System.out.println(ai.get());//get new val
    }
}
