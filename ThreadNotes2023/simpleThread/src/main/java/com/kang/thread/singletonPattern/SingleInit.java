package com.kang.thread.singletonPattern;

/**
 * 懒汉式-延迟初始化占位类模式
 */
public class SingleInit {
    private SingleInit(){}

    private static class InstanceHolder{
        private static SingleInit instance = new SingleInit();
    }

    public static SingleInit getInstance(){
        return InstanceHolder.instance;
    }

}
