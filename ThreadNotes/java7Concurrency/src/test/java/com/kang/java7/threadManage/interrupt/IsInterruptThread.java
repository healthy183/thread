package com.kang.java7.threadManage.interrupt;

/**
 * Created by Administrator on 2016/7/11.
 */
public class IsInterruptThread extends  Thread {

    @Override
    public void run(){
        long number = 1;
        while(true){
            if (isPrime(number)) {
                System.out.println("Number "+number+" is Prime");
            }
            if (isInterrupted()) {//中断,跳出run
                System.out.printf("The Prime Generator has been tnterrupted");
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number){
        for (long i=2; i<number; i++){
            if ((number % i)==0) {
                return false;
            }
        }
        return true;
    }

}
