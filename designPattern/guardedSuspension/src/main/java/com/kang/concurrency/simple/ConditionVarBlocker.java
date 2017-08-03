package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class ConditionVarBlocker implements  Blocker{

    private final Lock lock;
    private final Condition condition;

    public ConditionVarBlocker(){
        this.lock =  new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public ConditionVarBlocker(Lock lock){
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    @Override
    public <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception {
        lock.lockInterruptibly();
        V result = null;
        try{
            final Predicate guard = guardedAction.getPredicate();
            while(!guard.evaluate()){
                condition.await();
            }
            result = guardedAction.call();
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
        }finally {
            lock.unlock();
        }
        return result;
    }

    @Override
    public void signalAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();
        try{
            if(stateOperation.call()){
                condition.signal();
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void signal() throws Exception {
        lock.lockInterruptibly();
        try{
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void broadcastAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();
        try{
           if(stateOperation.call()){
               condition.signalAll();
           }
        }finally {
            lock.unlock();
        }
    }
}
