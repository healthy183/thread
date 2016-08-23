package com.kang.java7.threadManage.priority;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/10.
 */
public class MainRunCope {

    public static void main(String[] args) throws InterruptedException, IOException {

        Thread threads[] = new Thread[10];
        Thread.State status[] = new Thread.State[10];

        for(int i = 0;i<10;i++){
            threads[i] = new Thread(new Thread(new Calculator(i)));
            if(i%2 == 0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else{
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("threadName"+i);
            status[i] =  threads[i].getState();
        }

        //PrintWriter pw  = null;
        FileWriter file = null;
        try {
             file = new FileWriter(".\\log.txt");
            //pw = new PrintWriter(file);
            for(int i = 0 ;i<10;i++){
                file.write("Main : Status of thread "+i+":"+threads[i].getState()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Thread thread : threads ){
            thread.start();
            thread.join();
        }

        boolean finish = false;
        //while (!finish) {
            for(int i = 0; i<10; i++ ){
                if (threads[i].getState() != status[i]) {
                    writeThreadInfo(file, threads[i],status[i]);
                    status[i] = threads[i].getState();
                }
            }
       // }

       /* finish =true;
        for(int i=0;i<10;i++){
            finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
        }*/
        file.flush();
        file.close();
    }

    private static void writeThreadInfo(FileWriter file, Thread thread, Thread.State state) throws IOException {
        file.write("Main : Id "+thread.getId()+":"+thread.getName()+"\n");
        file.write("Main : Priority: "+thread.getPriority()+"\n");
        file.write("Main : Old State: "+state+"\n");
        file.write("Main : New State: "+thread.getState()+"\n");
        file.write("Main : ************************************\n");
    }

}
