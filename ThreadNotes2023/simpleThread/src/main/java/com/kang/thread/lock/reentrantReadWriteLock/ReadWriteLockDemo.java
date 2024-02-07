package com.kang.thread.lock.reentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReadWriteLockDemo {
    private Integer cache;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private void _load() {
        // 模拟读写数据库的耗时
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getFromCache() {
        lock.readLock().lock();
        log.info(Thread.currentThread().getName() + ":开始读取缓存");
        Integer res = cache;
        try {
            Random random = new Random();
            int sleepCounts = random.nextInt(3000);
            log.info(Thread.currentThread().getName() + "将睡眠了："+sleepCounts+"ms");
            Thread.sleep(sleepCounts);
        } catch (InterruptedException e) {
            log.error(""+e);
        }
        log.info(Thread.currentThread().getName() + ":结束读取缓存");
        lock.readLock().unlock();
        if (cache == null) {
            return loadCache();
        }
        return cache;
    }

    private Integer loadCache() {
        lock.writeLock().lock();
        Integer val;
        if (cache == null) {
            log.info(Thread.currentThread().getName() + ":开始加载数据库");
            // get from database...
            _load();
            val = new Random(5).nextInt();
            cache = val;
            log.info(Thread.currentThread().getName() + ":结束加载数据库");
            lock.writeLock().unlock();
        } else {
            lock.readLock().lock();
            lock.writeLock().unlock();
            log.info(Thread.currentThread().getName() + ":开始读取缓存");
            val = cache;
            log.info(Thread.currentThread().getName() + ":结束读取缓存");
            lock.readLock().unlock();
        }
        return val;
    }

    private void setCache(Integer val) {
        lock.writeLock().lock();
        log.info(Thread.currentThread().getName() + ":开始写缓存");
        // write to database...
        _load();
        cache = val;
        log.info(Thread.currentThread().getName() + ":结束写缓存");
        lock.writeLock().unlock();
    }
    public static void concurrentRead(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ReadWriteLockDemo cache = new ReadWriteLockDemo();
        // 默认缓存已生效
        executorService.execute(() -> cache.getFromCache());
        executorService.execute(() -> cache.getFromCache());
        executorService.execute(() -> cache.getFromCache());

        executorService.shutdown();
    }

    public static void concurrentReadWrite(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ReadWriteLockDemo cache = new ReadWriteLockDemo();
        // 默认缓存已生效
        executorService.execute(() -> cache.getFromCache());
        executorService.execute(() -> cache.getFromCache());
        executorService.execute(() -> cache.getFromCache());
        executorService.execute(() -> cache.loadCache());
        executorService.execute(() -> cache.getFromCache());
        executorService.execute(() -> cache.getFromCache());

        executorService.shutdown();

    }

    public static void concurrentWrite(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ReadWriteLockDemo cache = new ReadWriteLockDemo();
        // 默认缓存已生效
        executorService.execute(() -> cache.loadCache());
        executorService.execute(() -> cache.loadCache());
        executorService.execute(() -> cache.loadCache());

        executorService.shutdown();
    }

    public static void main(String[] args) {
        // concurrentRead();
        concurrentReadWrite();
        //concurrentWrite();
    }
}