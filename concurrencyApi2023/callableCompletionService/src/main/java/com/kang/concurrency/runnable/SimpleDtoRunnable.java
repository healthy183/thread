package com.kang.concurrency.runnable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/13.
 * @Author Healthy
 * @Version
 */
@Data
@AllArgsConstructor
@Slf4j
public class SimpleDtoRunnable implements  Runnable {

    private SimpleDto simpleDto;

    @Override
    public void run() {
        simpleDto.setName("simpleDtoName");
        simpleDto.setPwd("simpleDtoPwd");
        log.info("finish!f");
    }
}
