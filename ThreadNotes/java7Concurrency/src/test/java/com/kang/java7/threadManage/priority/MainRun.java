package com.kang.java7.threadManage.priority;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/10.
 */
public class MainRun {

    public static void main(String[] args) throws InterruptedException, IOException {

        List<Thread> threads = new ArrayList<Thread>();
        List<Thread.State> status = new ArrayList<Thread.State>();

        for(int i = 0;i<10;i++){
            Thread thread  = new Thread(new Calculator(i));
            if(i%2 == 0){
                thread.setPriority(Thread.MAX_PRIORITY);
            }else{
                thread.setPriority(Thread.MIN_PRIORITY);
            }
            thread.setName("threadName"+i);
            status.add(thread.getState());
            threads.add(thread);
        }

        PrintWriter pw  = null;
        FileWriter file = null;
        try {
            file = new FileWriter(".\\log.txt");
            //pw = new PrintWriter(file);
            for(int i = 0 ;i<10;i++){
                //pw.println("Main : Status of thread "+i+":"+threads.get(i).getState());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Thread thread : threads ){
            thread.start();
        }

        while (true) {
            int length = threads.size();
            if(length == 0) break;
            for(int i = 9; i >=0; i-- ){
                if (threads.get(i).getState() != status.get(i)) {
                    writeThreadInfo(file, threads.get(i),status.get(i));
                    status.add(i,threads.get(i).getState());
                    threads.remove(threads.get(i));
                }
            }
       }

        file.flush();
        file.close();
    }

    private static void writeThreadInfo(FileWriter file, Thread thread, Thread.State state) {
        try {
            file.write("Main : Id "+thread.getId()+":"+thread.getName()+"\n");
            file.write("Main : Priority: "+thread.getPriority()+"\n");
            file.write("Main : Old State: "+state+"\n");
            file.write("Main : New State: "+thread.getState()+"\n");
            file.write("Main : ************************************\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
