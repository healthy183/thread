package com.kang.thread.safeMap;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/19.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class DelegatingVehicleTracker {

    private final ConcurrentHashMap<Integer,Points> locations;
    private final Map<Integer, Points> unmodifyHashMap;

    public DelegatingVehicleTracker(Map<Integer,Points> map){
        this.locations  = new ConcurrentHashMap<Integer, Points>(map);
        unmodifyHashMap = Collections.unmodifiableMap(map);
    }

    public Map<Integer, Points> getLocations() {
        //return unmodifyHashMap;
       return Collections.unmodifiableMap(new HashMap<Integer,Points>(locations));
    }

    public Points getLocation(Integer id){
        return locations.get(id);
    }

    public void setLocation(Integer id,Integer x,Integer y){
        if(locations.replace(id,new Points(x,y))== null){
            throw new IllegalArgumentException("invaild id"+id);
        }
    }

    public static void main(String[] args) {
        Map<Integer,Points> map = new HashMap<Integer,Points>();
        map.put(1,new Points(1,1));
        map.put(2,new Points(2,2));
        map.put(3,new Points(3,3));
        DelegatingVehicleTracker delegatingVehicleTracker = new DelegatingVehicleTracker(map);
        delegatingVehicleTracker.setLocation(1,4,4);
        Map<Integer, Points> unmodifyHashMap = delegatingVehicleTracker.getLocations();
        log.info(unmodifyHashMap.toString());

    }



}
