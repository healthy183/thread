package com.kang.concurrency.implicitParameterPassing;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/23.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class MainRun {

    public static void main(String[] args) {
        ClientThread clientThread;
        BusinessService businessService = new BusinessService();
        for(int i =0;i<5;i++){
            clientThread = new ClientThread(i+"",businessService);
            clientThread.start();
        }
    }
}

class ClientThread extends Thread{
    private  final String message;
    private final BusinessService businessService;
    private static final AtomicInteger SEQ = new  AtomicInteger(0);

    public ClientThread( String message,BusinessService businessService){
        this.message = message;
        this.businessService = businessService;
    }

    @Override
    public void run() {
        Context.INSTANCE.setTransactionId(SEQ.getAndIncrement());
        businessService.service(message);
    }
}

@NoArgsConstructor(access= AccessLevel.PRIVATE)
class Context{

    private static final ThreadLocal<Integer> TS_OBJECT_PROXY = new  ThreadLocal<>();
    public static final Context INSTANCE = new  Context();

    public Integer getTransactionId(){
        return TS_OBJECT_PROXY.get();
    };

    public void setTransactionId(Integer transactionId){
        TS_OBJECT_PROXY.set(transactionId);
    };

    public void  reset(){
        TS_OBJECT_PROXY.remove();
    }

}
@Slf4j
class BusinessService{
    public void service(String msg){
        Integer id = Context.INSTANCE.getTransactionId();
        log.info("[{}]:[{}]",id,msg);
    }
}
