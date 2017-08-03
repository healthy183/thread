package com.kang.thread.concurrentHashMaps;

import com.kang.thread.common.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title 类名
 * @Description map缓存键值结果
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
@ThreadSafe
public class Memoizer1<A,V>  implements  Computable<A,V>{

    private final Map<A,V> map = new HashMap<A,V>();
    private final  Computable<A,V> computable;

    public Memoizer1(Computable<A,V> computable){
        this.computable = computable;
    }

    /**
     *1,synchronized方法伸缩性极差
     *2,synchronized(map){}伸缩性也差
     * 改用concurrenttHashMap可以提高伸缩性
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = null;
        //先判断再执行
        if(map.get(arg) == null){
            result = computable.compute(arg);
            map.put(arg,result);
        }
        return result;
    }
}
