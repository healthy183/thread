package com.kang.concurrency.stageRun;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/7.
 * @Author Healthy
 * @Version
 */
public class StageThread extends  Thread {
    private StageRunService stageRunService;
    public StageThread(StageRunService stageRunService){
        this.stageRunService = stageRunService;
    }
    @Override
    public void run(){
        stageRunService.begin();
    }
}
