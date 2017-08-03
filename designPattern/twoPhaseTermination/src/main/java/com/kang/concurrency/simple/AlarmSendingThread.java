package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/25.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class AlarmSendingThread extends AbstractTerminatableThread {

    private final AlarmAgent alarmAgent = new AlarmAgent();
    private final BlockingQueue<AlarmInfo> alarmQueue ;
    private final ConcurrentMap<String,AtomicInteger> submittedAlarmRegistry;

    public AlarmSendingThread() {
        this.alarmQueue = new ArrayBlockingQueue<AlarmInfo>(100);
        this.submittedAlarmRegistry = new ConcurrentHashMap<String,AtomicInteger>();
        alarmAgent.init();
    }

    @Override
    public void doRun() throws Exception {
        AlarmInfo alarmInfo;
        alarmInfo = alarmQueue.take();
        terminationToken.reservation.decrementAndGet();
        try{
            alarmAgent.sendAlarm(alarmInfo);
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
        }

        if(AlarmType.RESUME == alarmInfo.getType()){
            String key = getMapKey(AlarmType.FAULT,alarmInfo.getId(),alarmInfo.getInfo());
            submittedAlarmRegistry.remove(key);
            key = getMapKey(AlarmType.RESUME,alarmInfo.getId(),alarmInfo.getInfo());
            submittedAlarmRegistry.remove(key);
        }
    }

    public int sendAlarm(final AlarmInfo alarmInfo){
        Integer id = alarmInfo.getId();
        String info = alarmInfo.getInfo();
        if(terminationToken.isToshutdown()){
            //打印作为记录
            log.info("reject alarm id[{}] info[{}]",id,info);
            return -1;
        }
        int duplicateSubmissionCount = 0;
        try{
            AtomicInteger prevSubmittedCounter;
            prevSubmittedCounter = submittedAlarmRegistry.putIfAbsent(
                    getMapKey(alarmInfo.getType(),alarmInfo.getId(), alarmInfo.getInfo()),new AtomicInteger(0));
            if(prevSubmittedCounter == null){
                terminationToken.reservation.incrementAndGet();
                alarmQueue.put(alarmInfo);
            }else{
                //故障未回复，无法重发，故增加计数
                duplicateSubmissionCount  = prevSubmittedCounter.incrementAndGet();
            }
        }catch(Exception e){
            log.info(Throwables.getStackTraceAsString(e));
        }

        return duplicateSubmissionCount;
    }

    @Override
    public void docleanup(Exception e) {
        if(null != e && !(e instanceof  InterruptedException)){
            log.info(Throwables.getStackTraceAsString(e));
        }
        alarmAgent.disconnect();
    }

    private String getMapKey(AlarmType type,Integer id,String info){
       return  type.toString()+":"+id +"@"+ info;
    }
}
