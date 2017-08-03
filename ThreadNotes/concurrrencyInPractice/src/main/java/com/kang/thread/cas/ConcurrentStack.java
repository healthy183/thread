package com.kang.thread.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title 类名
 * @Description 无阻塞栈(先进后出)
 * @Date 2017/7/29.
 * @Author Healthy
 * @Version
 */
public class ConcurrentStack<E> {

    private static  class Node<E>{
        public final E item;
        public Node<E> next;

        public Node(E item){
            this.item = item;
        }
    }

    AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    public void push(E item){
            Node<E> newHead = new Node<E>(item);
            Node<E> oldHead;
            do{
                oldHead =  top.get();
                newHead.next = oldHead;
            }while(!top.compareAndSet(oldHead,newHead));
    }


    public E pop(){
        Node<E>  oldHead;
        Node<E>  newHead;

        do{
            oldHead = top.get();
            if(oldHead == null){
                return null;
            }
            newHead = oldHead.next;
        }while(!top.compareAndSet(oldHead,newHead));
        return  oldHead.item;
    }

}
