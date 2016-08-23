package com.kang.java7.threadManage.daemonThread;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/13.
 */
@ToString
@Data
public class Event implements  java.io.Serializable {
    private Date date;
    private String eventDetail;
}
