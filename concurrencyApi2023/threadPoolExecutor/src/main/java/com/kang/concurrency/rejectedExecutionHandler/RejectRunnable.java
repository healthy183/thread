package com.kang.concurrency.rejectedExecutionHandler;

import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
@Slf4j
@ToString
@Data
@AllArgsConstructor
public class RejectRunnable implements  Runnable{

    private  String name;
    @Override
    public void run() {
        log.info("[{}]start!",getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }
        log.info("end!");
    }
}
