package com.kang.thread.interrput;

import lombok.extern.slf4j.Slf4j;

/**
 *类说明：如何安全中断线程
 */
@Slf4j
public class EndThread {
	
	private static class UseThread extends Thread{

		private boolean cancel;

		public UseThread(String name) {
			super(name);
		}

		public void setCancel(boolean cancel) {
			this.cancel = cancel;
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			log.info(threadName+" interrrupt flag ="+isInterrupted());
			//while(!isInterrupted()){  // 判断线程interrupted状态(方式一)
				//Thread.sleep();
			//while(!Thread.interrupted()){  // 判断线程interrupted状态(方式二)
			while(true){ // 即使线程被interrupt()，也不会停止运行
				log.info(threadName+" is running");
				log.info(threadName+"inner interrrupt flag =" +isInterrupted());
			}
			//log.info(threadName+" interrrupt flag ="+isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		UseThread endThread = new UseThread("endThread");
		endThread.start();
		Thread.sleep(20);
		endThread.interrupt();//中断线程，其实设置线程的中断标识位=true

	}

}
