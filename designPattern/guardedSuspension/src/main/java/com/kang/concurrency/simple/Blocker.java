package com.kang.concurrency.simple;

import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
public interface Blocker {

    /**
     * 发送通知线程，否则一定等待
     * @param guardedAction
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    /**
     * 服务器连上，通知线程执行
     * @param stateOperation
     * @throws Exception
     */
    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws Exception;

    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
