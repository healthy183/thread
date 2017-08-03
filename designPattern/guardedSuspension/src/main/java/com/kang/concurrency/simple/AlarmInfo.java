package com.kang.concurrency.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
@ToString
@Getter
@AllArgsConstructor
public class AlarmInfo implements  java.io.Serializable {
    private Integer id;
    private String info;
    private AlarmType type;

    public AlarmInfo(String info){
        this.info = info;
    }

}
