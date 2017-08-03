package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import com.google.common.primitives.Booleans;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class AlarmAgent {
    private volatile boolean connectedToServer = false;
    private final Predicate agentConnected = new  Predicate(){
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };
    private Blocker blocker = new ConditionVarBlocker();
    private final Timer  heartBeatTimer = new Timer(true);

    public void sendAlarm(final AlarmInfo alarmInfo) throws Exception{
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected){
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarmInfo);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    public void doSendAlarm(AlarmInfo alarmInfo){
        log.info("alarmInfo is [{}]",alarmInfo);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }

    public  void  init(){
        Thread thread = new Thread(new ConnectingTask());
        thread.start();
        heartBeatTimer.schedule(new HeartbeatTask(),60000,2000);
    }

    public void disconnect(){
        connectedToServer = false;
    }

    protected void onConnected(){
        try{
            blocker.signalAfter(new Callable<Boolean>(){
                @Override
                public Boolean call() throws Exception {
                    connectedToServer = true;
                    return Boolean.TRUE;
                }
            });
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
        }
    }

    protected  void onDisconnected(){
        connectedToServer = false;
    }


    private class ConnectingTask implements  Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info(Throwables.getStackTraceAsString(e));
            }
            onConnected();
        }

    }

    private class HeartbeatTask extends TimerTask{
        @Override
        public void run() {
            if(!testConnection()){
                onDisconnected();
                recconnect();
            }
        }

        private Boolean  testConnection(){
         return true;
        }

        private  void recconnect(){
            ConnectingTask connectingTask = new ConnectingTask();
            connectingTask.run();
        }
    }
}
