package com.kang.concurrency.simple;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class SimpleRun {

    public static void main(String[] args) {
        AlarmAgent  alarmAgent = new AlarmAgent();
        alarmAgent.init();
        try {
            alarmAgent.sendAlarm(new AlarmInfo("iamtom"));
        } catch (Exception e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
    }
}
