package com.kang.thread.notity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/29.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class Lists {

    @Test
    public void mainRun(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        String[] strArrays = new String[3];
        strArrays[0] = "0";
        strArrays[1] = "1";
        strArrays[2] = "2";
        System.out.println(strArrays.length);

        String[] args = arrayList.toArray(strArrays);
        //log.info(args.toString());
        for(String str :args){
            System.out.print(str+" ");
        }
        //LinkedList linkedList  = new LinkedList();
        //log.info(arrayList.toString());
    }

    @Test
    public void arraycopyTest(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");

        String[] strArrays = new String[3];
        strArrays[0] = "0";
        strArrays[1] = "1";
        strArrays[2] = "2";

        System.arraycopy(arrayList.toArray(), 0, strArrays, 0, arrayList.size());

        for(String str :strArrays){
            System.out.print(str+" ");
        }
    }

    @Test
    public void arraycopyTest2(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");

        String[] strArrays = new String[3];
        strArrays[0] = "0";
        strArrays[1] = "1";
        strArrays[2] = "2";
        //ar4  strArrays.length导致  ArrayIndexOutOfBoundsException
        System.arraycopy(arrayList.toArray(), 0, strArrays, 0, strArrays.length);
        for(String str :strArrays){
            System.out.print(str+" ");
        }
    }

    @Test
    public void arraycopyTest3(){
        String[] strArrays = new String[6];
        strArrays[0] = "0";
        strArrays[1] = "1";
        strArrays[2] = "2";
        strArrays[3] = "3";
        strArrays[4] = "4";
        strArrays[5] = "5";
        int fromIndex= 2;
        int toIndex = 3;
        //ar4  strArrays.length导致  ArrayIndexOutOfBoundsException
        System.arraycopy(strArrays, toIndex, strArrays, fromIndex,strArrays.length-toIndex);
        for(String str :strArrays){
            System.out.print(str+" ");
        }
    }

    @Test
    public void arraycopyTest4(){
        String[] strArrays = new String[6];
        strArrays[0] = "0";
        strArrays[1] = "1";
        strArrays[2] = "2";
        strArrays[3] = "3";
        strArrays[4] = "4";
        strArrays[5] = "5";
        System.arraycopy(strArrays, 2, strArrays, 1,4);
        for(String str :strArrays){
            System.out.print(str+" ");
        }
    }

    @Test
    public void indexs(){
        String[] strArrays = new String[3];
        strArrays[0] = "0";
        strArrays[1] = "1";
        strArrays[2] = "2";
        System.out.println(strArrays[3]);
    }

}
