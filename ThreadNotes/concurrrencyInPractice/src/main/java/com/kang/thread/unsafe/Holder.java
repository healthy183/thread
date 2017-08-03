package com.kang.thread.unsafe;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/18.
 * @Author Healthy
 * @Version
 */
public class Holder {

    private int n;//final时才确保正确发布
    //有可能jvm构造对象时先把n设置成默认0(但我测不出来）

    public Holder(int n){
        this.n = n;
    }

    public void assertSanity(){
        if(n != n){
            throw  new RuntimeException("n未必正确发布");
        }
    }

    public static void main(String[] args) {
        while(true){
            new Holder(1).assertSanity();
        }

    }

}
