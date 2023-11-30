package com.kang.concurrency.giveup;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/1/8.
 * @Author Healthy
 * @Version
 */
public class SoonThread extends Thread {
    private SimplePrintTools simplePrintTools;
    public SoonThread(SimplePrintTools simplePrintTools){
        this.simplePrintTools = simplePrintTools;
    }
    @Override
    public void run(){
        simplePrintTools.soon();
    }
}
