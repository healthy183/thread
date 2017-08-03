package com.kang.concurrency.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
@AllArgsConstructor
public abstract class GuardedAction<V> implements Callable<V> {
    @Getter
    private  final  Predicate predicate;
}
