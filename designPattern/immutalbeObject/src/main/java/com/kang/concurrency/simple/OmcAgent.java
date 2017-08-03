package com.kang.concurrency.simple;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/19.
 * @Author Healthy
 * @Version
 */
public class OmcAgent extends Thread {
    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updateTableName = null;
        while(true){
            if(isTableModificationMsg){
                if("MmscInfo".equals(updateTableName)){
                    MmscRouter.setInstance(new MmscRouter());
                }
            }
        }
    }
}
