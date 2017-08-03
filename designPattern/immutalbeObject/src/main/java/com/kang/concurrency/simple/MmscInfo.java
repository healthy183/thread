package com.kang.concurrency.simple;

import lombok.Getter;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
@Getter
public class MmscInfo  implements java.io.Serializable {
    private final String deviceId;

    private final String url;

    private final int maxSize;

    public  MmscInfo( String deviceId,String url,int maxSize){
        this.deviceId = deviceId;
        this.url = url;
        this.maxSize = maxSize;
    }

    public MmscInfo(MmscInfo mmscInfo){
        this.deviceId = mmscInfo.deviceId;
        this.url = mmscInfo.url;
        this.maxSize = mmscInfo.maxSize;
    }
}
