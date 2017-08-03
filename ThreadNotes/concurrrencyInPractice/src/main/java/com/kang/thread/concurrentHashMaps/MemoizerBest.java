package com.kang.thread.concurrentHashMaps;

import com.kang.thread.common.ThreadSafe;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Title 类名
 * @Description 使用ConcurrentHashMap缓存键值结果,Future为或者结果对象
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
@ThreadSafe
public class MemoizerBest<A,V>  implements  Computable<A,V>{


    private final Map<A,Future<V>> map = new ConcurrentHashMap<A,Future<V>>();
    private final  Computable<A,V> computable;

    public MemoizerBest(Computable<A,V> computable){
        this.computable = computable;
    }

    /**
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public  V compute(final A arg) throws InterruptedException {
        while(true){
            //先判断,再执行
            Future<V> f = map.get(arg);
            if(f == null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                };
                FutureTask<V>  ft = new FutureTask<V>(eval);
                f = map.putIfAbsent(arg,f);//putIfAbsent防止重复添加任务
                if(f == null){
                    f = ft;
                    ft.run();
                }
            }
            try {
            return  f.get();
            } catch (CancellationException e) {
                    map.remove(arg,f);
            } catch (ExecutionException e) {
                launderThrowable(e);
            }
        }
    }


    public RuntimeException launderThrowable(Throwable t){
        if(t instanceof  RuntimeException){
            return (RuntimeException) t;
        }else if(t instanceof  Error){
            throw (Error)t;
        }
        throw new IllegalStateException("Not unchecked",t);
    }

}
