package com.kang.concurrency.multi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/6.
 * @Author Healthy
 * @Version
 */
public class MainRun {

    public static void main(String[] args) {
        MultiService multiService  = new MultiService();
        List<ThreadC> cList = new ArrayList<ThreadC>();
        List<ThreadP> pList = new ArrayList<ThreadP>();
        for(int i = 0;i<60;i++){
            ThreadC threadC = new ThreadC(multiService);
            cList.add(threadC);
            ThreadP threadP = new ThreadP(multiService);
            pList.add(threadP);
        }
        for(int i = 0;i<60;i++){
            cList.get(i).start();
            pList.get(i).start();
        }
    }
}
