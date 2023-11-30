package com.kang.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2016/6/17.
 */
 class User {
    private String name;
    private int old;

    public User(String name, int old) {
        this.name = name;
        this.old = old;
    }

    public String getName() {
        return name;
    }

    public int getOld() {
        return old;
    }
}
public class AtomicReferenceTest {

    public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("old", 15);
        atomicUserRef.set(user);
        User updateUser = new User("new", 17);
        atomicUserRef.compareAndSet(user, updateUser);//替换
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getOld());

    }
}
