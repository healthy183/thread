package com.kang.thread.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title 类名
 * @Description非阻塞队列(JDK有类似实现 ConcurrentLinkedQueue)
 * @Date 2017/7/29.
 * @Author Healthy
 * @Version
 */
public class LinkedQueue<E> {

    private static class Node<E>{
        final E item;
        final AtomicReference<Node<E>> next;

        public  Node(E item,Node<E> nextNode){
            this.item = item;
            this.next = new AtomicReference<Node<E>>(nextNode);
        }
    }

    private final Node<E> dummy = new Node<E>(null,null);
    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);

    public Boolean put(E item){
        Node<E> newNode = new Node<>(item,null);
        while(true){
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if(curTail == tail.get()){//最后一个元素没有变
                if(tailNext != null){//最后一个元素被其他线程操作
                    tail.compareAndSet(curTail,tailNext);//原子帮其他线程设队列尾为tailNext
                }else{//否则证明队列在单线线程操作，设置curTail.next为newNode
                    if(curTail.next.compareAndSet(null,newNode)){
                        tail.compareAndSet(curTail,newNode);//重置队列尾为newNode
                        return true;
                    }
                }
            }
        }
    }
}
