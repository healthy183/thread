package com.kang.concurrency.simple;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
public class MmscRouter {

    private static volatile  MmscRouter  mmscRouter = new MmscRouter();
    private final Map<String,MmscInfo> routerMap;

    public MmscRouter(){
        this.routerMap = MmscRouter.searchMap();
    }

    private static Map<String,MmscInfo> searchMap(){
        Map<String,MmscInfo> routerMap = new HashMap<String,MmscInfo>();
        return routerMap;
    }

    public static MmscRouter getInstance(){
        return mmscRouter;
    }
    public static void setInstance( MmscRouter newRouter ){
         mmscRouter = newRouter;
    }

    public MmscInfo getInfo(String id){
        return routerMap.get(id);
    }

    public Map<String,MmscInfo> deepCope(Map<String,MmscInfo> newMap){
        Map<String,MmscInfo> result = new HashMap<String,MmscInfo>();
        for(String key : newMap.keySet()){
            result.put(key,new MmscInfo(newMap.get(key)));
        }
        return result;
    }

    public Map<String,MmscInfo> getRouterMap(){
        return Collections.unmodifiableMap(deepCope(routerMap));
    }

}
