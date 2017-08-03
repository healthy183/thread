package com.kang.concurrency.simple;

import lombok.ToString;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/25.
 * @Author Healthy
 * @Version
 */
@ToString
public enum AlarmType {
    RESUME("resume"),
    FAULT("fault");


    private String typeName;
    AlarmType(String typeName) {
        this.typeName  = typeName;
    }
}
