package com.kang.concurrency.onAdvance;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
public class OnAdvanceThread extends  Thread {
    private OnAdvanceService onAdvanceService;
    public  OnAdvanceThread(OnAdvanceService onAdvanceService){
            this.onAdvanceService = onAdvanceService;
    }
    @Override
    public void run(){
        onAdvanceService.mainRun();
    }

}
