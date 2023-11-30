package com.kang.thread.lock.nestedMonitorLockout;

/**
 * Created by Administrator on 2016/6/5.
 */

class MonitorObject{}

public class Lock {

    protected MonitorObject monitorObject = new MonitorObject();
    protected boolean isLocked = false;

    public void lock() throws InterruptedException {
        synchronized (this){
            while (isLocked){
                synchronized (this.monitorObject){
                    this.monitorObject.wait();//这里会释放monitorObject的同步锁，但是synchronized (this)却没有释放
                }
            }
            isLocked = false;
        }
    }

    public void unlock(){
        synchronized (this){//任何线程试图进入改方法都将等待,因为lock()中synchronized (this)并没有释放锁,导致嵌套管理锁死
            this.isLocked = false;
            synchronized (this.monitorObject){
                this.monitorObject.notify();
            }
        }
    }


}
