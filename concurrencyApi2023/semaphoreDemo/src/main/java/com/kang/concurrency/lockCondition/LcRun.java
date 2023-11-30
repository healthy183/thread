package com.kang.concurrency.lockCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/5.
 * @Author Healthy
 * @Version
 */
public class LcRun {

    public static void main(String[] args) {
        ListPool listPool = new ListPool();
        List<LcThread> list  = new ArrayList<LcThread>();
        for(int i = 0;i<=10;i++){
            LcThread lcThread = new LcThread(listPool);
            list.add(lcThread);
        }
        for(LcThread lcThread :  list){
            lcThread.start();
        }

    }
}
