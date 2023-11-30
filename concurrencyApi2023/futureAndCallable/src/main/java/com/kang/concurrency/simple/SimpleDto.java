package com.kang.concurrency.simple;

import lombok.Data;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/2/13.
 * @Author Healthy
 * @Version
 */
@Data
public class SimpleDto implements  java.io.Serializable{

    private String name;
    private String pwd;
}
