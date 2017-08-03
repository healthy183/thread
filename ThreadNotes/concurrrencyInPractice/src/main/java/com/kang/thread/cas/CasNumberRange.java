package com.kang.thread.cas;

import com.kang.thread.common.ThreadSafe;
import lombok.AllArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title 类名
 * @Description 非阻塞方法实现设值
 * @Date 2017/7/29.
 * @Author Healthy
 * @Version
 */
@ThreadSafe
public class CasNumberRange {

    @AllArgsConstructor
    private static class IntPair{
        final int lower;
        final int upper;
    }

    private final AtomicReference<IntPair> values =
            new  AtomicReference<IntPair>(new IntPair(0,0));

    public void setLower(int i){
        while(true){
            IntPair oldv = values.get();
            if(i > oldv.upper){
                throw new RuntimeException("参数异常");
            }
            IntPair newV = new IntPair(i,oldv.upper);
            if(values.compareAndSet(oldv,newV)){
                return;
            }
        }
    }

}
