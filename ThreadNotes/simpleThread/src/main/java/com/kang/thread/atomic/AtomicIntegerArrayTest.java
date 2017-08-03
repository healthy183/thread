package com.kang.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Administrator on 2016/6/17.
 */
public class AtomicIntegerArrayTest {

    private static int[] value = new int[] { 1, 2 };
    private static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {

        ai.getAndSet(0,3);//以原子方式将输入值替换数组中索引i的元素
        System.out.println(ai.get(0));
        System.out.println(value[0]);

        ai.addAndGet(0,3);//以原子方式将输入值与数组中索引i的元素相加。
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }

}
