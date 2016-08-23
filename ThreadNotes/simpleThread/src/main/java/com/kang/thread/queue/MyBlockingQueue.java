package com.kang.thread.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MyBlockingQueue {

    private List<Object> queue = new LinkedList<Object>();
    private int limit = 10;

    public MyBlockingQueue(int limit){
        this.limit= limit;
    }

    public synchronized  void enqueue(Object item) throws InterruptedException{
        while (this.queue.size() == this.limit){
            wait();
        }
        if (this.queue.size() == 0){
            notifyAll();
        }
        this.queue.add(item);
    }

    public synchronized  Object dequeue() throws InterruptedException{
        while (this.queue.size() == 0){
            wait();
        }
        if (this.queue.size() == this.limit){
            notifyAll();
        }
        return this.queue.remove(0);
    }

}
