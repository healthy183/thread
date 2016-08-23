package com.kang.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Administrator on 2016/6/17.
 */
class Tom {
    private String name;
    public volatile int old;

    public Tom(String name, int old) {
        this.name = name;
        this.old = old;
    }

    public String getName() {
        return name;
    }

    public int getOld() {
        return old;
    }
}
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<Tom> a = AtomicIntegerFieldUpdater
            .newUpdater(Tom.class, "old");//指定更新tom的old字段

    public static void main(String[] args) {
        Tom conan = new Tom("conan", 10);
        System.out.println(a.getAndIncrement(conan));
        System.out.println(a.get(conan));
    }
}
