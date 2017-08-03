package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/25.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class AlarmMgr {

    private static final AlarmMgr INSTANCE = new AlarmMgr();
    private volatile boolean shutdownRequested = false;

    private final AlarmSendingThread alarmSendingThread;

    public AlarmMgr(){
        alarmSendingThread = new AlarmSendingThread();
    }

    public static AlarmMgr getInstance(){
        return INSTANCE;
    }

    public int sendAlarm(AlarmType alarmType,Integer id,String info){
        log.info("Trigger alarm [{}] id[{}],info[{}]", alarmType,id,info);
        int duplicatesumbmissionCount = 0;
        try{
            AlarmInfo alarmInfo = new AlarmInfo(id,info,alarmType);
            duplicatesumbmissionCount = alarmSendingThread.sendAlarm(alarmInfo);
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
        }
        return duplicatesumbmissionCount;
    }

    public void init(){
        alarmSendingThread.start();
    }

    public synchronized  void shutdown(){
        if(shutdownRequested){
            throw  new IllegalStateException("shutdown already requested");
        }
        alarmSendingThread.terminate();
        shutdownRequested = true;
    }
}
