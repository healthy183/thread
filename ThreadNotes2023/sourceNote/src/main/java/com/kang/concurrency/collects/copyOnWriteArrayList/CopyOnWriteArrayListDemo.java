package com.kang.concurrency.collects.copyOnWriteArrayList;


public class CopyOnWriteArrayListDemo {


    public static void simple(){
        CopyOnWriteArrayListSource<Integer> copyOnWriteArrayList = new CopyOnWriteArrayListSource();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);
        boolean remove = copyOnWriteArrayList.remove(new Integer(3));
        System.out.println(remove);
        Integer removeVal = copyOnWriteArrayList.remove(1);
        System.out.println(removeVal);

    }

    public static void main(String[] args) {
        simple();
    }



}
